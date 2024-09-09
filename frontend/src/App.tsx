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
