import {AfterViewInit, Component, ElementRef, EventEmitter, OnInit, Output} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ServicesService} from "../services/services.service";
import {User} from "../model/user";
import {UserServerService} from "../services/user-server.service";
import {SeatServiceService} from "../services/seat-service.service";
import {ViewTheatreComponent} from "../view-theatre/view-theatre.component";

import {error} from "@angular/compiler-cli/src/transformers/util";


@Component({
  selector: 'app-interface',
  templateUrl: './interface.component.html',
  styleUrl: './interface.component.scss'
})
export class InterfaceComponent implements OnInit,AfterViewInit{
  signUpButtonClicked: boolean  = false;
  loginForm!: FormGroup;
  signUpForm!: FormGroup;
  private _user:User = {} as User;
  get user(): User {
    return this._user;
  }

  constructor(private elementRef: ElementRef,private formBuilder: FormBuilder,private ts: ServicesService,private userService: UserServerService,private seatService:SeatServiceService) { }
  ngAfterViewInit(): void {
    const loginButton = document.getElementById("loginBtn");
    const registerButton = document.getElementById("registerBtn");
    const loginForm = document.getElementById("login");
    const registerForm = document.getElementById("register");

    if (loginButton && registerButton && loginForm && registerForm) {
      loginButton.addEventListener('click', () => {
        loginForm.style.left = "4px";
        registerForm.style.right = "-520px";
      });

      registerButton.addEventListener('click', () => {
        loginForm.style.left = "-510px";
        registerForm.style.right = "5px";
      });
    }
  }

  ngOnInit(): void {// Obțineți referințe la butoane folosind ElementRef
    this.loginForm = this.formBuilder.group({
      emailOrUsername: [''],
      password: [''],
    });
    this.signUpForm = this.formBuilder.group(
      {
      firstName: [''],
      lastName: [''],
      username: [''],
      email: ['',[Validators.required, this.emailValidator]], password: ['']
    });
  }
  @Output() signIn: EventEmitter<void> = new EventEmitter<void>();
  @Output() signUp: EventEmitter<void> = new EventEmitter<void>();

  emailValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/i;

    if (control.value && !emailPattern.test(control.value)) {
      console.log('invalid email');
      return {'invalidEmail': true};
    }
    return null;
  }

  // Metoda apelată când butonul de autentificare este apăsat

  onSignInClick(): void {
    console.log('Form Values:', this.loginForm.value);
    const emailOrUsername = this.loginForm.get('emailOrUsername')?.value;
    const password = this.loginForm.get('password')?.value;
    console.log(emailOrUsername, password);
    console.log('Request Payload:', { emailOrUsername, password });
    if (/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/i.test(emailOrUsername)) {
      console.log('Email sign-in:', emailOrUsername);
      this.ts.loginEmail(emailOrUsername, password).subscribe({
        next: (data) => {
          this._user=data;
          if(data==null){
            console.log("User not found");
            return;
          }
          console.log(data);
          console.log('success');
          // this.seatService.getSeatsData();
          this.onSignInSuccess(data);
          this.signIn .emit();
        },
        error: (error) => {
          console.log(error);
        }
      });
    } else if (/^[a-zA-Z0-9]+$/.test(emailOrUsername)) {
      console.log('Username sign-in:', emailOrUsername);
      // @ts-ignore
     this.ts.loginUsername(emailOrUsername, password).subscribe({
        next: (data) => {
          console.log(data);
          console.log('success');
          if(data==null){
            console.log("User not found");
            return;
          }
          console.log("am intrat in getSeatsData");
          this.onSignInSuccess(data);
          this.signIn.emit();
        },
        error: (error) => {
          console.log(error);
        }
      });

    }
      this.clearForm();
  }
  onSignInSuccess(userData:User): void {
        this.userService.setUserData(userData);
        //this.seatService.getSeatsData();
  }

  // Metoda apelată când butonul de deconectare este apăsat
  register(){
    // Implementați logica de deconectare aici
    console.log('Deconectare...');
  }
  clearForm(): void {
    this.loginForm.reset();
    this.signUpForm.reset();
  }
}
