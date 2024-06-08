
import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {SeatServiceService} from "../services/seat-service.service";
import {Seat} from "../model/seat";
import {User} from "../model/user";
import {InterfaceComponent} from "../interface/interface.component";
import {ServicesService} from "../services/services.service";

@Component({
  selector: 'app-view-theatre',
  templateUrl: './view-theatre.component.html',
  styleUrl: './view-theatre.component.scss'
})
export class ViewTheatreComponent implements OnInit {
    seatsData: Seat[] = [];
    @ViewChild('seatsContainer') seatsContainer!: ElementRef;
    totalTickets: number = 0;
    totalAmount: number = 0;
    user :User | null= {} as User;
    userCanceled: User | null= {} as User;
    myseats: Seat[] = [];

    constructor(private seatService: SeatServiceService,private userServerService: ServicesService) {
        this.ngOnInit();
    }

    ngOnInit(): void {
        console.log("ViewTheatreComponent initialized");
        //this.createSeats();
        // this.seatService.getSeatsData().subscribe(data => {
        //   this.seatsData.add(data)
        //   console.log(data);
        // } );
        this.loadSeatsData();

    }

    loadSeatsData(): void {
      // this.user= data;
      // console.log(this.user);
        this.seatService.getSeatsData().subscribe(data => {
            this.seatsData = data;
            this.createSeats();
        });
    }
    createSeats(): void {
        const seatsContainer = this.seatsContainer.nativeElement;

        if (seatsContainer) {
            seatsContainer.innerHTML = '';  // Clear existing seats if any

            this.seatsData.forEach((seat, index) => {
                const seatNumber = seat.id; // Numărul locului
                const seatState = seat.stateOfSeats; // Starea scaunului (Reserved sau Available)

                const seatBox = document.createElement('div');
                seatBox.classList.add("seat");
                seatBox.classList.add('label');
                seatBox.textContent = `${seatNumber}`;

                if (seatState === 'RESERVED') {
                    seatBox.classList.add('reserved'); // Adaugăm clasa 'reserved' pentru scaunele rezervate
                }
                if(seatState === 'IN_PROCESS'){
                    seatBox.classList.add('selected');
                }

                seatBox.addEventListener('click', () => this.toggleSeat(seat, seatBox));

                seatsContainer.appendChild(seatBox);
            });
        }
    }
  ckeckSeats() {
      console.log("Butonul Check a fost apăsat!");
    this.user= this.userServerService.getUser();
    console.log(this.user);
    if(this.user) {
      this.seatService.getSeatsByUser(this.user.id).subscribe(data => {
        this.myseats = data;
        console.log(this.myseats);
          let seatNumbersString = 'Scaunele tale: ';
          this.myseats.forEach(seat => {
              seatNumbersString += seat.id + ', ';
          });
          // Elimină virgula și spațiul în plus de la sfârșitul șirului
          seatNumbersString = seatNumbersString.slice(0, -2);
          // Afișează un dialog de alertă cu numerele scaunelor
          window.alert(seatNumbersString);
      });
    }
  }
    cancelSeats() {
        console.log("Butonul Cancel a fost apăsat!");
        this.user= this.userServerService.getUser();
        console.log(this.user)
        if(this.myseats.length>0) {
        if(this.user) {
            const seatsInProcess = this.seatsData.filter(seat => seat.stateOfSeats === "CANCELED");
            seatsInProcess.forEach(seat => {
                seat.stateOfSeats = "AVAILABLE";
                this.userServerService.find("admin","admin").subscribe(data => {
                    this.userCanceled = data;
                    console.log(this.userCanceled);

                });
                if (this.userCanceled) {
                    console.log(seat.user);
                    this.user=seat.user;
                    seat.user = this.userCanceled;
                }
                console.log(this.user);
                console.log(seat);
                this.seatService.updateSeat(seat).subscribe(() => {
                    // După actualizarea scaunului pe server, actualizăm UI-ul

                });
              this.createSeats();
            });
            console.log(this.user);
        }
        }
    }




    toggleSeat(seat: Seat, seatDiv: HTMLDivElement): void {
        if (seat.stateOfSeats=="AVAILABLE") {
            seat.stateOfSeats = "IN_PROCESS";
            this.seatService.updateSeat(seat).subscribe();
            if (seat.stateOfSeats === "IN_PROCESS") {
                seatDiv.classList.add('selected');
            } else {
                seatDiv.classList.remove('selected');
            }
            this.updateTotals();
        }
        else if (seat.stateOfSeats=="IN_PROCESS") {
            seat.stateOfSeats = "AVAILABLE";
          if (seat.stateOfSeats === "AVAILABLE") {
            seatDiv.classList.add('deselected');
          }
            this.seatService.updateSeat(seat).subscribe(() => {

                this.createSeats();
            });
        }
        else if (seat.stateOfSeats=="RESERVED") {
            seat.stateOfSeats = "CANCELED";
            if(seat.stateOfSeats === "CANCELED"){
                seatDiv.classList.add('selectedCanceled');
            }
            this.seatService.updateSeat(seat).subscribe();
        }
    }


    updateTotals(): void {
        const selectedSeats = this.seatsData.filter(seat => seat.stateOfSeats === "IN_PROCESS");

        this.totalTickets = selectedSeats.length;
        this.totalAmount = this.totalTickets * 200; // Presupunem că fiecare bilet costă 200
        document.querySelector(".amount")!.textContent = this.totalAmount.toString();
        document.querySelector(".count")!.textContent = this.totalTickets.toString();
    }
    @Output() datePersonalGol: EventEmitter<void> = new EventEmitter<void>();
    onBuyClick() {
        console.log("Butonul Buy a fost apăsat!");
        const selectedSeats = this.seatsData.filter(seat => seat.stateOfSeats === "IN_PROCESS");
        this.user= this.userServerService.getUser();
        console.log(this.user);
        if(this.user!=null){
            console.log(selectedSeats);
            if(null== this.user.cnp) {
                console.log("CNP-ul este gol");
                this.datePersonalGol.emit();
            }
            selectedSeats.forEach(seat => {
              console.log("am intrat in forEach")
                seat.stateOfSeats = "RESERVED";
                console.log(this.user);
                if (this.user) {
                    seat.user = this.user;
                    console.log(seat.user);
                }
                console.log(seat);
                 this.seatService.updateSeat(seat).subscribe(() => {
                    // După actualizarea scaunului pe server, actualizăm UI-ul
                    this.createSeats();
                });
            });
            this.updateTotals();
        }

    }



}
