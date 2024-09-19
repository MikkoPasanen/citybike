import { useState } from "react";

// Components
import SingleJourney from "./Single-journey";

// Types & data
import { Journey } from "../data/types";
import { JourneysTable } from "../data/Journeys-table";

const Journeys = () => {
    const [openedJourney, setOpenedJourney] = useState<Journey | null>(null);
    const [openDialog, setOpenDialog] = useState(false);

    const closeDialog = () => {
        setOpenDialog(false);
        setOpenedJourney(null);
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
                <h1 className="text-2xl pt-2 font-semibold">Journeys</h1>
                <div className="container mx-auto py-10">
                <JourneysTable
                    setOpenedJourney={setOpenedJourney}
                    setOpenDialog={setOpenDialog}
                />
                </div>
            </div>
            <SingleJourney
                isOpen={openDialog}
                journey={openedJourney}
                closeDialog={closeDialog}
            />
        </div>
    );
};

export default Journeys;
