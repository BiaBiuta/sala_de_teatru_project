import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserServerService {
  private userDataSubject = new BehaviorSubject<any>(null);
  userData$:Observable<User> = this.userDataSubject.asObservable();

  setUserData(userData: User): void {
    this.userDataSubject.next(userData);
  }

  sendUserData(data: any): void {
    this.userDataSubject.next(data);
  }

  getUserData(): BehaviorSubject<any> {
    return this.userDataSubject;
  }
}
