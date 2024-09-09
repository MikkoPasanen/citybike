import { useState, useEffect } from "react";
import axios from "axios";

// Components
import SingleStation from "./Single-station";

// Types & data
import { Station } from "../data/types";
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

const Stations = () => {
    const [data, setData] = useState<Station[]>([]);
    const [openedStation, setOpenedStation] = useState<Station | null>(null);
    const [openDialog, setOpenDialog] = useState(false);

    const columns: ColumnDef<Station>[] = [
        {
            accessorKey: "stationName",
            header: () => <div className="text-center">Station Name</div>,
        },
        {
            accessorKey: "stationAddress",
            header: () => <div className="text-center">Station Address</div>,
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

    async function fetchStations() {
        const response = await axios.get("http://localhost:8080/stations/all", {
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
            const data = await fetchStations();
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
                <h1 className="text-2xl pt-2 font-semibold">Stations</h1>
                <div className="container mx-auto py-10">
                    <DataTable columns={columns} data={data} />
                </div>
                <Pagination>
                    <PaginationContent>
                        <PaginationItem>
                            <PaginationPrevious to="#" />
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationLink to="/journeys">1</PaginationLink>
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationLink to="/journeys">2</PaginationLink>
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationLink to="/journeys">3</PaginationLink>
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
            <SingleStation
                isOpen={openDialog}
                station={openedStation}
                closeDialog={closeDialog}
            />
        </div>
    );
};

export default Stations;
