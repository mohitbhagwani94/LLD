package BookMyShow;

import lombok.Data;

@Data
public class Movies {
    int id;
    String name;
    int duration;

    Movies(int id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

}
