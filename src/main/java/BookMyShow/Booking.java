package BookMyShow;

import BookMyShow.Enums.BookindStatus;
import lombok.Data;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Data
public class Booking {
    UUID id;
    Show show;
    List<Seat> seats;
    User    user;
    double totalPrice;
    BookindStatus bookindStatus;

    public Booking(Show show, List<Seat> seats, User user) {
        this.id = UUID.randomUUID();
        this.show = show;
        this.seats = seats;
        this.user = user;
    }

    public double calculateTotalPrice(List<Seat> seats) {
         totalPrice = seats.stream().mapToDouble(Seat::getPrice).sum();
         return totalPrice;
    }
}
