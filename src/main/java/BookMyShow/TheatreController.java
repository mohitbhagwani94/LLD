package BookMyShow;

import BookMyShow.Enums.City;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class TheatreController {
    Map<City, List<Theatre>> cityVsTheator;

    public TheatreController() {
        cityVsTheator = new HashMap<>();
    }

    public void addTheatre(City city, Theatre theatre){
        cityVsTheator.computeIfAbsent(city,k -> new ArrayList<>()).add(theatre);
    }

    public Map<Theatre,List<Show>> getShowListInTheatorForMovies(City userCity, Movies movie) {
        Map<Theatre,List<Show>> theatreToShows = new HashMap<>();
        for(Theatre theatre : cityVsTheator.get(userCity)) {
            List<Show> showList = theatre.getShows(movie);
            if (showList.size() > 0)
                theatreToShows.put(theatre, showList);
        }
        return theatreToShows;
    }

    public Theatre getTheatreByName(City userCity, String selectedTheator) throws Exception {
        for(Theatre theatre : cityVsTheator.get(userCity)) {
            if(selectedTheator.equals(theatre.getName()))
                return theatre;
        }
        throw new Exception("Theator not found");
    }
}
