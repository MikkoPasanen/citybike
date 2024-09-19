import { useState } from "react";

// Components
import SingleStation from "./Single-station";

// Types & data
import { Station } from "../data/types";
import { StationsTable } from "../data/Stations-table";

const Stations = () => {
    const [openedStation, setOpenedStation] = useState<Station | null>(null);
    const [openDialog, setOpenDialog] = useState(false);

    const closeDialog = () => {
        setOpenDialog(false);
        setOpenedStation(null);
    };

    return (
        <div className="flex overflow-hidden">
            <div
                style={{ height: "88vh" }}
                className="border-r-2 border-yellow-400 w-1/4 bg-neutral-900 flex flex-col items-center"
            >
                tavaraa yms kuten sorttaus jne
            </div>
            <div className="text-center flex-grow">
                <h1 className="text-2xl pt-2 font-semibold">Stations</h1>
                <div className="container mx-auto py-10">
                    <StationsTable
                        setOpenedStation={setOpenedStation}
                        setOpenDialog={setOpenDialog}
                    />
                </div>
            </div>
            <SingleStation
                isOpen={openDialog}
                station={openedStation}
                closeDialog={closeDialog}
            />
        </div>
    );
};

export default Stations;
