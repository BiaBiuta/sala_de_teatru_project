package com.example.iss;

import com.example.iss.model.Auditorium;
import com.example.iss.model.Seat;
import com.example.iss.model.State;
import com.example.iss.model.User;
import com.example.iss.service.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service =service;
    }
    static Service getTasksService(){
        ApplicationContext context=new ClassPathXmlApplicationContext("App.xml");
        Service service=context.getBean(Service.class);
        return service;
    }
    @GetMapping("/viewAllSeats")
    public ResponseEntity<?> viewAllSeats() {
        try {
            Iterable<Seat> seats = service.viewAllSeats();
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul obținerii listei de locuri.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/viewAllSeatsFilter")
    public ResponseEntity<?> viewAllSeatsFilter(@RequestParam(value = "status") String status) {
        try {
            Iterable<Seat> seats = service.viewAllSeatsFilter(status);
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Statusul furnizat este invalid.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul obținerii listei de locuri filtrate.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cancelSeat/{id}")
    public ResponseEntity<?> cancelSeat(@PathVariable Integer id) {
        try {
            Seat seat = service.cancelSeat(id);
            return new ResponseEntity<>(seat, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul anulării locului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/reservedSeat")
    public ResponseEntity<?> reservedSeat(@RequestParam(value = "id") Integer id) {
        try {
            Seat seat = service.reservedSeat(id);
            return new ResponseEntity<>(seat, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul rezervării locului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addSeat")
    public ResponseEntity<?> addSeat(@RequestBody Seat seat) {
        try {
            Seat addedSeat = service.addSeat(seat);
            return new ResponseEntity<>(addedSeat, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul adăugării locului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateSeat")
    public ResponseEntity<?> updateSeat(@RequestBody Seat seat) {
        try {
            Seat updatedSeat = service.updateSeat(seat);
            return new ResponseEntity<>(updatedSeat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul actualizării locului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/updateAllSeats")
    public ResponseEntity<?> updateAllSeats(@RequestParam(value = "status") String status) {
        try {
            Iterable<Seat> seats = service.viewAllSeats();
            for (Seat seat : seats) {
                seat.setStateOfSeats(State.valueOf(status));
                service.updateSeat(seat);
            }
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Statusul furnizat este invalid.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul actualizării listei de locuri.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteSeat/{id}")
    public ResponseEntity<?> deleteSeat(@PathVariable Integer id) {
        try {
            Seat deletedSeat = service.deleteSeat(id);
            return new ResponseEntity<>(deletedSeat, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul ștergerii locului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findSeatById/{id}")
    public ResponseEntity<?> findSeatById(@PathVariable Integer id) {
        try {
            Seat seat = service.findSeatById(id);
            return new ResponseEntity<>(seat, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul căutării locului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findSeatByNumber")
    public ResponseEntity<?> findSeatByNumber(@RequestParam(value = "number") Integer number) {
        try {
            Seat seat = service.findSeatByNumber(number);
            return new ResponseEntity<>(seat, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul căutării locului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findSeatByRow")
    public ResponseEntity<?> findSeatByRow(@RequestParam(value = "row") Integer row) {
        try {
            Iterable<Seat> seats = service.findSeatByRow(row);
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul căutării locurilor după rând.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findSeatByColumn")
    public ResponseEntity<?> findSeatByColumn(@RequestParam(value = "column") Integer column) {
        try {
            Iterable<Seat> seats = service.findSeatByColumn(column);
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul căutării locurilor după coloană.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findSeatByRowAndColumn")
    public ResponseEntity<?> findSeatByRowAndColumn(@RequestParam(value = "column") Integer column, @RequestParam(value = "row") Integer row) {
        try {
            Seat seat = service.findSeatByRowAndColumn(column, row);
            return new ResponseEntity<>(seat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul căutării locului după rând și coloană.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findSeatByUser")
        public ResponseEntity<?> findSeatByUser(@RequestParam(value = "userId") Integer user){
            try {
                Iterable<Seat> seat = service.findSeatByUser(user);
                return new ResponseEntity<>(seat, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("A apărut o eroare în timpul căutării locului după user.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    @GetMapping("/viewAllSeatsByPrice")
    public ResponseEntity<?> viewAllSeatsByPrice(@RequestParam(value = "price") Integer price) {
        try {
            Iterable<Seat> seats = service.viewAllSeatsByPrice(price);
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul obținerii listei de locuri filtrate după preț.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/viewAllSeatsByTypeSeat")
    public ResponseEntity<?> viewAllSeatsByTypeSeat(@RequestParam(value = "typeSeat") String typeSeat) {
        try {
            Iterable<Seat> seats = service.viewAllSeatsByTypeSeat(typeSeat);
            return new ResponseEntity<>(seats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul obținerii listei de locuri filtrate după tipul de loc.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/buySeat/{id}")
    public ResponseEntity<?> buySeat(@PathVariable Integer id) {
        try {
            Seat seat = service.buySeat(id);
            return new ResponseEntity<>(seat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("A apărut o eroare în timpul achiziționării locului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/allUsers")
    public List<User> allUsers(){
        return service.findAllUsers();
    }
    @PostMapping("/createUser")
    public User createUser( @RequestBody User user){
        return service.createUser(user);
    }
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }
    @DeleteMapping("/deleteUser/{id}")
    public User deleteUser( @PathVariable Integer id){
        return service.deleteUser(id);
    }
    @GetMapping("/findUserById/{id}")
    public User findUserById(@PathVariable Integer id){
        return service.findUserById(id);
    }
    @GetMapping("/findUserByUsername")
    public User findUserByUsername(@RequestParam(value="username")String username){
        return service.findUserByUsername(username);
    }
    @GetMapping("/findUserByEmail")
    public User findUserByEmail(@RequestParam(value="email")String email){
        return service.findUserByEmail(email);
    }
    @GetMapping("/findUserByPasswordAndUsername")

    public User findUserByPasswordAndUsername(@RequestParam(value="password")String password, @RequestParam(value="username")String username){
        return service.findUserByPasswordAndUsername(password,username);
    }
    @GetMapping("/findUserByEmailAndPassword")
    public User findUserByEmailAndPassword(@RequestParam(value="email")String email,@RequestParam(value="password") String password){
        return service.findUserByEmailAndPassword(email,password);
    }
    @PostMapping("/addAuditorium")
    public Auditorium addAuditorium( @RequestBody Auditorium auditorium){
        return service.addAuditorium(auditorium);
    }
    @PutMapping("/updateAuditorium")
    public Auditorium updateAuditorium(@RequestBody Auditorium auditorium){
        return service.updateAuditorium(auditorium);
    }
    @DeleteMapping("/deleteAuditorium/{id}")
    public Auditorium deleteAuditorium( @PathVariable Integer id){
        return service.deleteAuditorium(id);
    }
    @GetMapping("/findAuditoriumById/{id}")
    public Auditorium findAuditoriumById(@PathVariable Integer id){
        return service.findAuditoriumById(id);
    }

    @GetMapping("/findAuditoriumByName")
    public Auditorium findAuditoriumByName(@RequestParam(value="name")String name){
        return service.findAuditoriumByName(name);
    }
    @GetMapping("/findAllAuditoriums")
    public List<Auditorium> findAllAuditoriums(){
        return (List<Auditorium>) service.findAllAuditoriums();
    }




}
