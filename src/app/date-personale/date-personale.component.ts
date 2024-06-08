import {Component, ElementRef, EventEmitter, OnInit, Output} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup} from "@angular/forms";
import {User} from "../model/user";
import {ServicesService} from "../services/services.service";
import {UserServerService} from "../services/user-server.service";
import {response} from "express";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-date-personale',

  templateUrl: './date-personale.component.html',
  styleUrl: './date-personale.component.scss'
})
export class DatePersonaleComponent implements OnInit {
    datePersonale!:FormGroup;
    private _user:User | null={} as User;
    get user(): User | null {
        return this._user;
    }

  set user(value: User | null) {
    this._user = value;
  }

  constructor(private elementRef: ElementRef,private formBuilder: FormBuilder,private ts: ServicesService,private userService: UserServerService) {
        this.ngOnInit();
    }

    ngOnInit(): void {
        console.log("DatePersonaleComponent initialized");
        this.datePersonale = this.formBuilder.group({
            Name: [''],
            email: [''],
            cnp: [''],
            codIban: [''],
            adresa: ['']
        });
    }
  @Output() pressButton: EventEmitter<void> = new EventEmitter<void>();
  emailValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/i;

    if (control.value && !emailPattern.test(control.value)) {
      console.log('invalid email');
      return {'invalidEmail': true};
    }
    return null;
  }
  submitForm(): void {
    console.log('submitForm');
    this._user= this.ts.getUser();
    console.log(this._user);
    const firstName= this.datePersonale.get('Name')?.value;
    console.log(firstName);
    const email= this.datePersonale.get('email')?.value;
    console.log(email);
    const cnp= this.datePersonale.get('cnp')?.value;
    const codIban= this.datePersonale.get('codIban')?.value;
    const adresa= this.datePersonale.get('adresa')?.value;
    console.log(adresa);
    if(this._user) {
      this._user.firstName = firstName;
      this._user.email = email;
      this._user.cnp = cnp;
      this._user.codIban = codIban;
      this._user.address = adresa;

      this.ts.register(this._user).subscribe({next: (response) => {

              console.log('User registered successfully', response);

              this.userService.setUserData(response);
          },
          error: (error) => {
              console.error('Error registering user', error);
          }
      }
    );
    }
    this.pressButton.emit();
  }
}
