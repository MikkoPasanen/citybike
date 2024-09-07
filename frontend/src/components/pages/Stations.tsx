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
        </div>
    );
};

export default Stations;
