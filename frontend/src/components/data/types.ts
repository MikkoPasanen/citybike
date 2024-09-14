export type Station = {
    id: number;
    stationName: string;
    stationAddress: string;
    coordinateX: string;
    coordinateY: string;
};

export type Journey = {
    id: number;
    departureTime: string;
    returnTime: string;
    departureStation: number;
    returnStation: number;
    distance: number;
    duration: number;
};
