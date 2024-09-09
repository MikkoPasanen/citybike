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
import { useState } from "react";

const Navbar = () => {
    const [selected, setSelected] = useState<string>("stations");
    return (
        <div className="bg-neutral-900 flex items-center p-3 justify-between block border-b-2 border-yellow-400">
            <div className="flex items-center ml-3">
                <h1 className="text-2xl font-bold m-1 mr-5">Citybikes</h1>
                <Link
                    to="/stations"
                    onClick={() => setSelected("stations")}
                    className={`p-1 mr-2 ${selected == "stations" ? "bg-yellow-400 border-2 border-yellow-400" : "border-2 border-yellow-400 text-white"} text-black font-semibold br-50 rounded-lg`}
                >
                    Stations
                </Link>
                <Link
                    to="/journeys"
                    onClick={() => setSelected("journeys")}
                    className={`p-1 mr-2 ${selected == "journeys" ? "bg-yellow-400 border-2 border-yellow-400" : "border-2 border-yellow-400 text-white"} text-black font-semibold br-50 rounded-lg`}
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
