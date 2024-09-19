import Navbar from "@/components/pages/Navbar";
import Stations from "@/components/pages/Stations";
import Journeys from "./components/pages/Journeys";
import {
    Route,
    BrowserRouter as Router,
    Routes,
    Navigate,
} from "react-router-dom";

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
