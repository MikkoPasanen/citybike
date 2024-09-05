import { useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import axios from "axios";
import icon from "leaflet/dist/images/marker-icon.png";
import L from "leaflet";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

const defaultIcon = new L.Icon({
    iconUrl: icon,
    iconSize: [31, 41],
    iconAnchor: [15, 41],
    popupAnchor: [1, -41],
});

L.Marker.prototype.options.icon = defaultIcon;

function App() {
    const [id, setId] = useState<number | null>(null);
    const [name, setName] = useState<string>("");
    const [address, setAddress] = useState<string>("");
    const [coordX, setCoordX] = useState<string>("");
    const [coordY, setCoordY] = useState<string>("");
    const [errStatus, setErrStatus] = useState<number | null>(null);
    const [errMsg, setErrMsg] = useState<string>("");

    const fetchStation = async () => {
        try {
            const response = await axios.get(
                "http://localhost:8080/stations/singular/" + id
            );

            setName(response.data.stationName);
            setAddress(response.data.stationAddress);
            setCoordX(response.data.coordinateX);
            setCoordY(response.data.coordinateY);
            setErrStatus(null);
            setErrMsg("");
        } catch (error) {
            if (axios.isAxiosError(error)) {
                console.log(error);
                console.log(error.response);
            }
        }
    };

    return (
        <div className="flex items-center justify-center flex-col">
            <h1 className="text-3xl font-bold mb-4">Bike station Finder</h1>
            <div className="flex flex-col items-center justify-center space-x-4">
                <Input
                    className="w-30 text-center mb-2"
                    placeholder="Station ID"
                    value={id !== null ? id.toString() : ""}
                    onChange={(e) => setId(Number(e.target.value))}
                />
                <Button onClick={fetchStation} className="mb-4">
                    Fetch
                </Button>
            </div>
            {coordX && coordY && !errMsg ? (
                <MapContainer
                    center={[Number(coordY), Number(coordX)]}
                    zoom={14}
                    style={{ height: "60vh", width: "100vh" }}
                >
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />
                    <Marker position={[Number(coordY), Number(coordX)]}>
                        <Popup>
                            {name} <br /> {address}
                        </Popup>
                    </Marker>
                </MapContainer>
            ) : errMsg ? (
                <>
                    <p>{errMsg}</p>
                    <p>{errStatus}</p>
                </>
            ) : null}
        </div>
    );
}

export default App;
