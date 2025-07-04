package BookMyShow;

import BookMyShow.Enums.SeatCatogary;
import lombok.Data;

@Data
public class Seat {
    String id;
    int row;
    int col;
    int price;
    SeatCatogary seatCatogary;
    Seat(String id, int row, int col, int price, SeatCatogary seatCatogary) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.price = price;
        this.seatCatogary = seatCatogary;
    }
}
