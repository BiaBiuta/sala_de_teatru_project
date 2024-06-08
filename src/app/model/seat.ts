import {User} from "./user";
import {Auditorium} from "./auditorium";

export interface Seat {
    id: number;
    first: number;
    second: number;
    auditorium: Auditorium;
    number: number;
    price: number;
    stateOfSeats: string;
    type: string;
    user: User;
}
