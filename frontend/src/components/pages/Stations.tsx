import { useState, useEffect } from "react";
import {
    Pagination,
    PaginationContent,
    PaginationEllipsis,
    PaginationItem,
    PaginationLink,
    PaginationNext,
    PaginationPrevious,
} from "@/components/ui/pagination";

import { DataTable } from "@/components/data/data-table";
import axios from "axios";

import { ColumnDef } from "@tanstack/react-table";
import { Button } from "../ui/button";
import SingleStation from "./Single-station";

type Station = {
    id: number;
    stationName: string;
    stationAddress: string;
    coordinateX: string;
    coordinateY: string;
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

const Stations = () => {
    const [data, setData] = useState<Station[]>([]);
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
                        onClick={() => setOpenDialog(true)}
                        className="bg-yellow-400 text-black font-semibold"
                    >
                        More info
                    </Button>
                </div>
            ),
        },
    ];

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
                            <PaginationEllipsis />
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationNext to="#" />
                        </PaginationItem>
                    </PaginationContent>
                </Pagination>
            </div>
            <SingleStation></SingleStation>
        </div>
    );
};

export default Stations;
