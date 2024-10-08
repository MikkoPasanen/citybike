"use client";

import { useState, useEffect } from "react";
import axios from "axios";
import {
    ColumnDef,
    flexRender,
    getCoreRowModel,
    useReactTable,
    getPaginationRowModel,
} from "@tanstack/react-table";

import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/ui/table";

import { Button } from "@/components/ui/button";
import { formatTimestamp } from "@/lib/timeformat";

import {
    Pagination,
    PaginationContent,
    PaginationItem,
    PaginationNext,
    PaginationPrevious,
} from "@/components/ui/pagination";

import { Journey } from "./types";

export const JourneysTable = ({
    setOpenDialog,
    setOpenedJourney,
}: {
    setOpenDialog: (value: boolean) => void;
    setOpenedJourney: (value: Journey) => void;
}) => {
    // States & variables
    const [data, setData] = useState<Journey[]>([]);
    const [page, setPage] = useState(0);
    const [amountOfPages, setAmountOfPages] = useState(0);
    const DATA_SIZE = 5;

    // Columns
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
    ];

    // Define the table
    const table = useReactTable({
        data,
        columns,
        getCoreRowModel: getCoreRowModel(),
        getPaginationRowModel: getPaginationRowModel(),
        initialState: { pagination: { pageSize: 5 } },
    });

    // Fetch data on mount
    useEffect(() => {
        // Fetch data
        async function fetchJourneys() {
            const response = await axios.get(
                "http://localhost:8080/journeys/all",
                {
                    params: {
                        page: page,
                        size: DATA_SIZE,
                    },
                }
            );
            if (response.status === 200) {
                return response.data;
            }
        }

        async function fetchData() {
            const data = await fetchJourneys();
            setData(
                data.journeys.map((journey: Journey) => {
                    journey.departureTime = formatTimestamp(
                        journey.departureTime
                    );
                    journey.returnTime = formatTimestamp(journey.returnTime);
                    return journey;
                })
            );
            setAmountOfPages(Math.ceil(data.amountOfJourneys / DATA_SIZE));
        }

        fetchData();
    }, [page]);

    return (
        <>
            <div className="flex justify-between items-center w-3/4 mx-auto mb-2">
                <h1 className="text-md pt-2 font-semibold">
                    Helsinki citybike stations
                </h1>
                <h1 className="text-md pt-2 font-semibold">
                    Page {page + 1} / {amountOfPages}
                </h1>
            </div>
            <div className="flex items-center justify-center flex-col">
                <div className="rounded-md border-2 border-yellow-400 w-3/4">
                    <Table>
                        <TableHeader>
                            {table.getHeaderGroups().map((headerGroup) => (
                                <TableRow key={headerGroup.id}>
                                    {headerGroup.headers.map((header) => {
                                        return (
                                            <TableHead key={header.id}>
                                                {header.isPlaceholder
                                                    ? null
                                                    : flexRender(
                                                          header.column
                                                              .columnDef.header,
                                                          header.getContext()
                                                      )}
                                            </TableHead>
                                        );
                                    })}
                                </TableRow>
                            ))}
                        </TableHeader>
                        <TableBody>
                            {table.getRowModel().rows?.length ? (
                                table.getRowModel().rows.map((row) => (
                                    <TableRow key={row.id}>
                                        {row.getVisibleCells().map((cell) => (
                                            <TableCell key={cell.id}>
                                                {flexRender(
                                                    cell.column.columnDef.cell,
                                                    cell.getContext()
                                                )}
                                            </TableCell>
                                        ))}
                                    </TableRow>
                                ))
                            ) : (
                                <TableRow>
                                    <TableCell
                                        colSpan={columns.length}
                                        className="h-24 text-center"
                                    >
                                        No results.
                                    </TableCell>
                                </TableRow>
                            )}
                        </TableBody>
                    </Table>
                </div>
                <div className="mt-6">
                    <Pagination>
                        <PaginationContent>
                            <PaginationItem>
                                <PaginationPrevious
                                    to="#"
                                    onClick={() => {
                                        if (page != 0)
                                            setPage((prev) => prev - 1);
                                    }}
                                    className={
                                        page === 0
                                            ? "pointer-events-none opacity-50"
                                            : ""
                                    }
                                />
                            </PaginationItem>
                            <PaginationItem>
                                <PaginationNext
                                    to="#"
                                    onClick={() => {
                                        setPage((prev) => prev + 1);
                                    }}
                                    className={
                                        page + 1 === amountOfPages
                                            ? "pointer-events-none opacity-50"
                                            : ""
                                    }
                                />
                            </PaginationItem>
                        </PaginationContent>
                    </Pagination>
                </div>
            </div>
        </>
    );
};
