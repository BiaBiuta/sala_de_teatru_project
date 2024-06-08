import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import {catchError, Observable, tap, throwError} from "rxjs";
import {User} from "../model/user";
@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  private apiURL = 'http://localhost:9092/api';
    private user: User | null = null; // Variabilă pentru a stoca user-ul

  constructor(private http:HttpClient) { }
  register(user:User): Observable<any> {
    console.log(user);

    return this.http.put<any>(`${this.apiURL}/updateUser`,user).pipe(
      catchError((error) => {
        console.error('Error:', error);
        throw error;
      })
    );
  }
  loginEmail(email: string, password: string): Observable<any>{
        const body = { email, password };
        const options = { params: { email, password } };
        return this.http.get<any>(`${this.apiURL}/findUserByEmailAndPassword`, options).pipe(
            catchError((error) => {
                console.error('Error:', error);
                throw error;
            })
        );
  }
  loginUsername(username: string, password: string): Observable<User>{
    const body = { username, password };
    const options = { params: { username, password } };

      return this.http.get<User>(`${this.apiURL}/findUserByPasswordAndUsername`, options).pipe(
          tap((user: User) => this.user = user), // Setează user-ul când Observable emite valoarea
          catchError((error) => {
              console.error('Error:', error);
              return throwError(error);
          })
      );
  }
    getUser(): User | null {
        return this.user; // Funcție pentru a obține user-ul curent
    }

    find(username: string, password: string) {
        const body = { username, password };
        const options = { params: { username, password } };

        return this.http.get<User>(`${this.apiURL}/findUserByPasswordAndUsername`, options).pipe(
            tap((user: User) => this.user = user), // Setează user-ul când Observable emite valoarea
            catchError((error) => {
                console.error('Error:', error);
                return throwError(error);
            })
        );

    }
}
