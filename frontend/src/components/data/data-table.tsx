// "use client";

// import React from "react";
// import {
//     ColumnDef,
//     flexRender,
//     getCoreRowModel,
//     useReactTable,
//     getPaginationRowModel,
// } from "@tanstack/react-table";

// import {
//     Table,
//     TableBody,
//     TableCell,
//     TableHead,
//     TableHeader,
//     TableRow,
// } from "@/components/ui/table";

// import {
//     Pagination,
//     PaginationContent,
//     PaginationItem,
//     PaginationNext,
//     PaginationPrevious,
// } from "@/components/ui/pagination";

// interface DataTableProps<TData, TValue> {
//     columns: ColumnDef<TData, TValue>[];
//     data: TData[];
// }

// // Using React.memo to prevent unnecessary re-renders by memoizing the component
// // and only re-rendering when the props change (data table doesn't need to re-render when we open a dialog)
// export const DataTable = React.memo(function DataTable<TData, TValue>({
//     columns,
//     data,
// }: DataTableProps<TData, TValue>) {

//     const table = useReactTable({
//         data,
//         columns,
//         getCoreRowModel: getCoreRowModel(),
//         getPaginationRowModel: getPaginationRowModel(),
//         initialState: { pagination: { pageSize: 7 } },
//     });

//     return (
//         <div className="flex items-center justify-center flex-col">
//             <div className="rounded-md border-2 border-yellow-400 w-3/4">
//                 <Table>
//                     <TableHeader>
//                         {table.getHeaderGroups().map((headerGroup) => (
//                             <TableRow key={headerGroup.id}>
//                                 {headerGroup.headers.map((header) => {
//                                     return (
//                                         <TableHead key={header.id}>
//                                             {header.isPlaceholder
//                                                 ? null
//                                                 : flexRender(
//                                                       header.column.columnDef
//                                                           .header,
//                                                       header.getContext()
//                                                   )}
//                                         </TableHead>
//                                     );
//                                 })}
//                             </TableRow>
//                         ))}
//                     </TableHeader>
//                     <TableBody>
//                         {table.getRowModel().rows?.length ? (
//                             table.getRowModel().rows.map((row) => (
//                                 <TableRow key={row.id}>
//                                     {row.getVisibleCells().map((cell) => (
//                                         <TableCell key={cell.id}>
//                                             {flexRender(
//                                                 cell.column.columnDef.cell,
//                                                 cell.getContext()
//                                             )}
//                                         </TableCell>
//                                     ))}
//                                 </TableRow>
//                             ))
//                         ) : (
//                             <TableRow>
//                                 <TableCell
//                                     colSpan={columns.length}
//                                     className="h-24 text-center"
//                                 >
//                                     No results.
//                                 </TableCell>
//                             </TableRow>
//                         )}
//                     </TableBody>
//                 </Table>
//             </div>
//             <div className="mt-6">
//                 <h1 className="mb-1">
//                     Page {table.getState().pagination.pageIndex + 1} /{" "}
//                     {table.getPageCount()}
//                 </h1>
//                 <Pagination>
//                     <PaginationContent>
//                         <PaginationItem>
//                             <PaginationPrevious
//                                 to="#"
//                                 onClick={() => table.previousPage()}
//                                 className={
//                                     table.getCanPreviousPage()
//                                         ? ""
//                                         : "pointer-events-none opacity-50"
//                                 }
//                             />
//                         </PaginationItem>
//                         <PaginationItem>
//                             <PaginationNext
//                                 to="#"
//                                 onClick={() => table.nextPage()}
//                                 className={
//                                     table.getCanNextPage()
//                                         ? ""
//                                         : "pointer-events-none opacity-50"
//                                 }
//                             />
//                         </PaginationItem>
//                     </PaginationContent>
//                 </Pagination>
//             </div>
//         </div>
//     );
// });
