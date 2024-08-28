import "./App.css";
import axios from "axios";

function App() {
    const fetchStation = async () => {
        const response = await axios.get("http://localhost:8080/stations/all");

        if (response.status === 200) {
            console.log(response.data);
        }
    };

    return (
        <>
            <div>
                <button onClick={fetchStation}>Fetch Station</button>
            </div>
        </>
    );
}

export default App;
