import { Injectable } from '@angular/core';
import {catchError, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Seat} from "../model/seat";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class SeatServiceService {
  private apiURL = 'http://localhost:9092/api';

  constructor(private http: HttpClient) { }

  getSeatsData(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiURL}/viewAllSeats`).pipe(
      catchError((error) => {
        console.error('Error:', error);
        throw error;
      })
    );
  }
    getSeatsByUser(userId: number) {
        const options = { params: { userId } };        return this.http.get<any>(`${this.apiURL}/findSeatByUser`,options).pipe(
            catchError((error) => {
                console.error('Error:', error);
                throw error;
            })
        );
    }




    updateSeat(seat: Seat) {
        return this.http.put(`${this.apiURL}/updateSeat`, seat).pipe(
            catchError((error) => {
                console.error('Error:', error);
                throw error;
            })
        );

    }



}
