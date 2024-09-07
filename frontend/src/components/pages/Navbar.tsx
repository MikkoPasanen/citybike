import { Link } from "react-router-dom";
import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuLabel,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";

const Navbar = () => {
    return (
        <div className="bg-neutral-900 flex items-center p-3 justify-between block border-b-2 border-yellow-400">
            <div className="flex items-center ml-3">
                <h1 className="text-2xl font-bold m-1 mr-5">Citybikes</h1>
                <Link
                    to="/stations"
                    className="p-1 mr-2 bg-yellow-400 text-black font-semibold br-50 rounded-lg"
                >
                    Stations
                </Link>
                <Link
                    to="/journeys"
                    className="p-1 mr-2 bg-yellow-400 text-black font-semibold br-50 rounded-lg"
                >
                    Journeys
                </Link>
            </div>
            <div className="mr-5">
                <DropdownMenu>
                    <DropdownMenuTrigger>
                        <Avatar className="border-2 border-yellow-400">
                            <AvatarFallback>MP</AvatarFallback>
                        </Avatar>
                    </DropdownMenuTrigger>
                    <DropdownMenuContent>
                        <DropdownMenuLabel>
                            Signed in as: <b>MP</b>
                        </DropdownMenuLabel>
                        <DropdownMenuSeparator />
                        <DropdownMenuItem className="font-semibold text-red-500">
                            Log out
                        </DropdownMenuItem>
                    </DropdownMenuContent>
                </DropdownMenu>
            </div>
        </div>
    );
};

export default Navbar;
