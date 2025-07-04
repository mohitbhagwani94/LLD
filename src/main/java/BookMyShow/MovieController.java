package BookMyShow;

import BookMyShow.Enums.City;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MovieController {
    Map<City, List<Movies>> cityVsMovies;
    Map<String, Movies> allMovies;

    public MovieController() {
        this.cityVsMovies = new HashMap<>();
        this.allMovies = new HashMap<>();
    }

    public void addMovies(City city, Movies movie){
        cityVsMovies.computeIfAbsent(city,k -> new ArrayList<>()).add(movie);
        allMovies.put(movie.getName(), movie);
    }
    public void removeMovies(City city, Movies movie){
        cityVsMovies.get(city).remove(movie);
    }
    public List<Movies> getMovies(City city){
        return cityVsMovies.getOrDefault(city,new ArrayList<>());
    }

    public Movies getMovies(String name){
        return allMovies.get(name);
    }

}
