"use client";

import { ColumnDef } from "@tanstack/react-table";
import Z from "zod";
import { Button } from "../ui/button";

export const StationSchema = Z.object({
    id: Z.number().int().positive(),
    stationName: Z.string(),
    stationAddress: Z.string(),
    coordinateX: Z.string(),
    coordinateY: Z.string(),
});

export type Station = Z.infer<typeof StationSchema>;

// eslint-disable-next-line react-refresh/only-export-components
export const columns: ColumnDef<Station>[] = [
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
                    onClick={() => console.log(row.original)}
                    className="bg-yellow-400 text-black font-semibold"
                >
                    More info
                </Button>
            </div>
        ),
    },
];
