// import { useState } from "react";
// import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
// import "leaflet/dist/leaflet.css";
import Navbar from "@/components/pages/Navbar";
import Stations from "@/components/pages/Stations";
import {
    Route,
    BrowserRouter as Router,
    Routes,
    Navigate,
} from "react-router-dom";
import Journeys from "./components/pages/Journeys";
// import axios from "axios";
// import icon from "leaflet/dist/images/marker-icon.png";
// import L from "leaflet";
// import { Input } from "@/components/ui/input";
// import { Button } from "@/components/ui/button";

// const defaultIcon = new L.Icon({
//     iconUrl: icon,
//     iconSize: [31, 41],
//     iconAnchor: [15, 41],
//     popupAnchor: [1, -41],
// });

// L.Marker.prototype.options.icon = defaultIcon;

const App = () => {
    return (
        <Router>
            <Navbar />
            <Routes>
                {/* Redirect root ("/") to "/stations" */}
                <Route path="/" element={<Navigate to="/stations" />} />

                <Route path="/stations" element={<Stations />} />
                <Route path="/journeys" element={<Journeys />} />
            </Routes>
        </Router>
    );
};

export default App;
