import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
} from "@/components/ui/dialog";
import { Station } from "../data/types";

const SingleStation = ({
    isOpen,
    station,
    closeDialog,
}: {
    isOpen: boolean;
    station: Station | null;
    closeDialog: () => void;
}) => {
    return (
        <Dialog open={isOpen} onOpenChange={closeDialog}>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>
                        {station?.stationName + ", " + station?.stationAddress}
                    </DialogTitle>
                    <DialogDescription>
                        Kartasta näet aseman sijainnin.
                    </DialogDescription>
                </DialogHeader>
                <div className="flex items-center space-x-2">
                    <div className="grid flex-1 gap-2">
                        <h2>kartta tähä</h2>
                    </div>
                </div>
            </DialogContent>
        </Dialog>
    );
};

export default SingleStation;
