import {User} from "./user";
import {Seat} from "./seat";

export interface Auditorium {
    id: number;
    namePerformance: string;
    seats: Seat[];
    numberOfSeats: number;

}
