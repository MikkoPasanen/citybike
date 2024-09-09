// Shadcn
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
} from "@/components/ui/dialog";

// Types & data
import { Station } from "../data/types";

// Leaflet
import icon from "leaflet/dist/images/marker-icon.png";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";

const defaultIcon = new L.Icon({
    iconUrl: icon,
    iconSize: [31, 41],
    iconAnchor: [15, 41],
    popupAnchor: [1, -41],
});

L.Marker.prototype.options.icon = defaultIcon;

const SingleStation = ({
    isOpen,
    station,
    closeDialog,
}: {
    isOpen: boolean;
    station: Station | null;
    closeDialog: () => void;
}) => {
    return (
        <Dialog open={isOpen} onOpenChange={closeDialog}>
            <DialogContent className="border-2 border-yellow-400 w-full max-w-3xl">
                <DialogHeader>
                    <DialogTitle className="text-center">
                        {station?.stationName + ", " + station?.stationAddress}
                    </DialogTitle>
                    <DialogDescription className="text-center">
                        Kartasta näet aseman sijainnin.
                        <br />
                        {station?.coordinateX + ", " + station?.coordinateY}
                    </DialogDescription>
                </DialogHeader>
                <MapContainer
                    center={
                        station
                            ? [
                                  Number(station.coordinateY),
                                  Number(station.coordinateX),
                              ]
                            : [0, 0]
                    }
                    zoom={15}
                    scrollWheelZoom={true}
                    className="h-72 rounded"
                >
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                        url="https://tiles.stadiamaps.com/tiles/outdoors/{z}/{x}/{y}{r}.png"
                        maxZoom={20}
                        minZoom={6}
                    />
                    <Marker
                        position={
                            station
                                ? [
                                      Number(station.coordinateY),
                                      Number(station.coordinateX),
                                  ]
                                : [0, 0]
                        }
                    >
                        <Popup>
                            {station?.stationName} <br />{" "}
                            {station?.stationAddress}
                        </Popup>
                    </Marker>
                </MapContainer>
            </DialogContent>
        </Dialog>
    );
};

export default SingleStation;
