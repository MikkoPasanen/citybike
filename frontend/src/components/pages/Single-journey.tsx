// Shadcn
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
} from "@/components/ui/dialog";

// Types & data
import { Journey } from "../data/types";

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

const SingleJourney = ({
    isOpen,
    journey,
    closeDialog,
}: {
    isOpen: boolean;
    journey: Journey | null;
    closeDialog: () => void;
}) => {
    return (
        <Dialog open={isOpen} onOpenChange={closeDialog}>
            <DialogContent className="border-2 border-yellow-400 w-full max-w-3xl">
                <DialogHeader>
                    <DialogTitle className="text-center">
                        {journey?.departureStation +
                            " - " +
                            journey?.returnStation}
                    </DialogTitle>
                    <DialogDescription className="text-center">
                        You can see the departure and return stations on the map below
                        <br />
                        <br />
                        <div className="flex justify-center">
                            <p className="mr-5">
                                {"Duration: " + journey?.duration + " mins"}
                            </p>
                            <p>
                                {"Distance: " + journey?.distance + " meters"}
                            </p>
                        </div>
                    </DialogDescription>
                </DialogHeader>
                <MapContainer
                    center={
                        journey
                            ? [
                                  Number(journey.departureStationCoordY),
                                  Number(journey.departureStationCoordX),
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
                            journey
                                ? [
                                      Number(journey.departureStationCoordY),
                                      Number(journey.departureStationCoordX),
                                  ]
                                : [0, 0]
                        }
                    >
                        <Popup>
                            <b>Departure station</b> <br />
                            {journey?.departureStation}
                        </Popup>
                    </Marker>
                    <Marker
                        position={
                            journey
                                ? [
                                      Number(journey.returnStationCoordY),
                                      Number(journey.returnStationCoordX),
                                  ]
                                : [0, 0]
                        }
                    >
                        <Popup>
                            <b>Return station</b> <br />
                            {journey?.returnStation}
                        </Popup>
                    </Marker>
                </MapContainer>
            </DialogContent>
        </Dialog>
    );
};

export default SingleJourney;
