import { useState } from "react";
import "./App.css";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import axios from "axios";
import icon from "leaflet/dist/images/marker-icon.png";
import L from "leaflet";

const defaultIcon = new L.Icon({
    iconUrl: icon,
    iconSize: [31, 41],
    iconAnchor: [15, 41],
    popupAnchor: [1, -41],
});

L.Marker.prototype.options.icon = defaultIcon;

function App() {
    const [id, setId] = useState<number>(0);
    const [name, setName] = useState<string>("");
    const [address, setAddress] = useState<string>("");
    const [coordX, setCoordX] = useState<string>("");
    const [coordY, setCoordY] = useState<string>("");

    const fetchStation = async () => {
        const response = await axios.get(
            "http://localhost:8080/stations/singular/" + id
        );

        if (response.status === 200) {
            console.log(response.data);
            setName(response.data.stationName);
            setAddress(response.data.stationAddress);
            setCoordX(response.data.coordinateX);
            setCoordY(response.data.coordinateY);
        }
    };

    return (
        <>
            <div>
                <input
                    type="number"
                    placeholder="Station ID"
                    onChange={(e) => setId(Number(e.target.value))}
                />
                <button onClick={fetchStation}>Fetch Station</button>
            </div>
            {coordX && coordY ? (
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
            ) : (
                ""
            )}
        </>
    );
}

export default App;
