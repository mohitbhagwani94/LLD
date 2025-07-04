package BookMyShow;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Theatre {
    int id;
    String name;
    List<Screen> screenList;
    Map<Movies,List<Show>> moviesToShows;

    Theatre(int id, String name) {
        this.id = id;
        this.name = name;
        screenList = new ArrayList<>();
        moviesToShows = new HashMap<>();
    }

    public void addScreen(Screen screen){
        screenList.add(screen);
    }

    public void addShow(Movies movie, Show show){
        moviesToShows.computeIfAbsent(movie, k -> new ArrayList<>()).add(show);
    }

    public List<Show> getShows(Movies movie){
        return moviesToShows.get(movie);
    }

}
