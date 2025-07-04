package BookMyShow;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Show {
    int id;
    Screen screen;
    int startTime;
    Movies movies;
    List<String> bookSeats;

    public Show(int id, Screen screen, int startTime, Movies movies) {
        this.id = id;
        this.screen = screen;
        this.startTime = startTime;
        this.movies = movies;
        this.bookSeats = new ArrayList<>();
    }


    public void addBookedSeat(String seat) {
        bookSeats.add(seat);
    }
}
