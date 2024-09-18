import { useState, useEffect, useMemo } from "react";
import axios from "axios";

// Components
import SingleJourney from "./Single-journey";

// Types & data
import { Journey } from "../data/types";
import { DataTable } from "@/components/data/data-table";
import { ColumnDef } from "@tanstack/react-table";

// Shadcn
import { Button } from "../ui/button";
import { formatTimestamp } from "@/lib/timeformat";

const Journeys = () => {
    const [data, setData] = useState<Journey[]>([]);
    const [openedJourney, setOpenedJourney] = useState<Journey | null>(null);
    const [openDialog, setOpenDialog] = useState(false);

    const columns: ColumnDef<unknown, unknown>[] = useMemo(
        () => [
            {
                accessorKey: "departureStation",
                header: () => <div className="text-center">Start Station</div>,
            },
            {
                accessorKey: "returnStation",
                header: () => <div className="text-center">Return Station</div>,
            },
            {
                accessorKey: "departureTime",
                header: () => <div className="text-center">Departure</div>,
            },
            {
                accessorKey: "returnTime",
                header: () => <div className="text-center">Return</div>,
            },
            {
                id: "actions",
                cell: ({ row }) => (
                    <div className="flex justify-center">
                        <Button
                            onClick={() => {
                                setOpenedJourney(row.original as Journey);
                                setOpenDialog(true);
                            }}
                            className="bg-yellow-400 text-black font-semibold"
                        >
                            More info
                        </Button>
                    </div>
                ),
            },
        ],
        []
    );

    // Memoize data to format timestamps only once
    const memoizedData = useMemo(
        () =>
            data.map((journey) => {
                journey.departureTime = formatTimestamp(journey.departureTime);
                journey.returnTime = formatTimestamp(journey.returnTime);
                return journey;
            }),
        [data]
    );

    const closeDialog = () => {
        setOpenDialog(false);
        setOpenedJourney(null);
    };

    async function fetchJourneys() {
        const response = await axios.get("http://localhost:8080/journeys/all", {
            params: {
            page: 1,
            size: 50,
            },
        });
        if (response.status === 200) {
            return response.data;
        }
    }

    useEffect(() => {
        async function fetchData() {
            const data = await fetchJourneys();
            setData(data);
        }

        fetchData();
    }, []);

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
                    <DataTable columns={columns} data={memoizedData} />
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
