package BookMyShow;

import lombok.Data;

import java.util.Map;

@Data
public class Screen {
    int id;
    Map<String, Seat> seat;
    Screen(int id, Map<String,Seat> seat) {
        this.id = id;
        this.seat = seat;
    }

    public Seat getSeat(String seatId){
        return seat.get(seatId);
    }
}
