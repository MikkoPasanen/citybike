import { useState, useEffect } from "react";
import axios from "axios";

// Components
import SingleStation from "./Single-station";

// Types & data
import { Journey } from "../data/types";
import { DataTable } from "@/components/data/data-table";
import { ColumnDef } from "@tanstack/react-table";

// Shadcn
import { Button } from "../ui/button";
import {
    Pagination,
    PaginationContent,
    PaginationEllipsis,
    PaginationItem,
    PaginationLink,
    PaginationNext,
    PaginationPrevious,
} from "@/components/ui/pagination";

const Journeys = () => {
    const [data, setData] = useState<Journey[]>([]);
    const [openedStation, setOpenedStation] = useState<Journey | null>(null);
    const [openDialog, setOpenDialog] = useState(false);
    const [activeStep, setActiveStep] = useState<string>("first");

    const columns: ColumnDef<Journey>[] = [
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
            accessorKey: "distance",
            header: () => <div className="text-center">Distance</div>,
        },
        {
            accessorKey: "duration",
            header: () => <div className="text-center">Duration</div>,
        },
        {
            id: "actions",
            cell: ({ row }) => (
                <div className="flex justify-center">
                    <Button
                        onClick={() => {
                            setOpenedStation(row.original);
                            setOpenDialog(true);
                        }}
                        className="bg-yellow-400 text-black font-semibold"
                    >
                        More info
                    </Button>
                </div>
            ),
        },
    ];

    const closeDialog = () => {
        setOpenDialog(false);
        setOpenedStation(null);
    };

    async function fetchJourneys() {
        const response = await axios.get("http://localhost:8080/journeys/all", {
            params: {
                page: 1,
                size: 5,
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
                    <DataTable columns={columns} data={data} />
                </div>
                <Pagination>
                    <PaginationContent>
                        <PaginationItem>
                            <PaginationPrevious to="#" />
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationLink
                                to="#"
                                isActive={activeStep === "first"}
                                onClick={() => setActiveStep("first")}
                                className={
                                    activeStep === "first"
                                        ? "bg-yellow-400 text-black hover:bg-yellow-600 hover:text-black"
                                        : ""
                                }
                            >
                                1
                            </PaginationLink>
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationLink
                                to="#"
                                isActive={activeStep === "second"}
                                onClick={() => setActiveStep("second")}
                                className={
                                    activeStep === "second"
                                        ? "bg-yellow-400 text-black hover:bg-yellow-600 hover:text-black"
                                        : ""
                                }
                            >
                                2
                            </PaginationLink>
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationLink
                                to="#"
                                isActive={activeStep === "third"}
                                onClick={() => setActiveStep("third")}
                                className={
                                    activeStep === "third"
                                        ? "bg-yellow-400 text-black hover:bg-yellow-600 hover:text-black"
                                        : ""
                                }
                            >
                                3
                            </PaginationLink>
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationEllipsis />
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationNext to="#" />
                        </PaginationItem>
                    </PaginationContent>
                </Pagination>
            </div>
            {/* <SingleStation
                isOpen={openDialog}
                ={openedStation}
                closeDialog={closeDialog}
            /> */}
        </div>
    );
};

export default Journeys;
