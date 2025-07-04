package BookMyShow;

import BookMyShow.Enums.City;
import BookMyShow.Enums.SeatCatogary;

import java.util.*;

public class BookMyShow {
    MovieController movieController;
    TheatreController theatreController;
    private final Map<UUID, Booking> bookings;
    BookMyShow() {
        movieController = new MovieController();
        theatreController = new TheatreController();
        bookings =  new HashMap<>();
    }

    public static void main(String[] args) throws Exception {
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialise();
        User mohit = new User(1,"Mohit");
        User rohit = new User(1,"Rohit");
        System.out.println("User Created");
        List<Seat>  selectedSeats = Arrays.asList();
        bookMyShow.createBooking(mohit, selectedSeats, City.Bangalore, "Avengers");
        //bookMyShow.createBooking(rohit, selectedSeats, City.Bangalore, "Avengers");
    }

    public void initialise(){
        System.out.println("Initialising BookMyShow");
        createMovies();
        System.out.println("Movies created");
        createTheatres();
        System.out.println("Theatres created");
    }

    private void createBooking(User user,List<Seat> selectedSeats, City userCity, String movieTitle) throws Exception {
        Movies selectedMovie = movieController.getMovies("Avengers");
        List<Movies> moviesListInSelectedCity = movieController.getMovies(userCity);
        boolean foundMovies = false;
        for (Movies movie : moviesListInSelectedCity)
            if(movie.equals(selectedMovie))
                foundMovies = true;//break

        if(!foundMovies)
            throw new Exception("Movie not found");
        System.out.println("Movie Title: " + movieTitle + " found");
        Map<Theatre,List<Show>> theatreWithShows = theatreController.getShowListInTheatorForMovies(userCity, selectedMovie);
        System.out.println("Select show from below");
        System.out.println(theatreWithShows);

        Theatre selectedTheator =  theatreController.getTheatreByName(userCity,"Inox");
        for(Theatre theatre : theatreWithShows.keySet()){
            System.out.println("Theatre Name: " + theatre.getName());
            for(Show show : theatre.getShows(selectedMovie)){
                System.out.println(show.id + " " + show.getStartTime()+ " " + show.getMovies().getName());
            }
        }
        System.out.println("Theatre: " + selectedTheator);
        Show selectedShow = theatreWithShows.get(selectedTheator).get(0);
        List<Seat> selectedSeat = Arrays.asList(selectedShow.getScreen().getSeat("1-2"),
                selectedShow.getScreen().getSeat("1-3"));

        for (Seat seat: selectedSeat) {
            if(selectedShow.getBookSeats().contains(seat.getId())) {
                throw new Exception("Seat not available");
            }
        }
        System.out.println("Available Seat: "+ selectedSeat);

        for (Seat seat: selectedSeat) {
            selectedShow.addBookedSeat(seat.getId());
        }
        double totalPrice = calculatePrice(selectedSeat);
        System.out.println("Total price: " + totalPrice);
        Booking booking = new Booking(selectedShow,selectedSeats,user);
        bookings.put(booking.getId(), booking);
        System.out.println("booking details:" + booking);
        System.out.println("Booking done");

    }

    public double calculatePrice(List<Seat> selectedSeats) {
        return selectedSeats.stream().mapToDouble(Seat::getPrice).sum();
    }


    private void createMovies() {
        Movies avengers = new Movies(1,"Avengers",120);
        Movies bahubali = new Movies(2,"Bahubali",150);

        movieController.addMovies(City.Bangalore,avengers);
        movieController.addMovies(City.NewDelhi,avengers);
        movieController.addMovies(City.Bangalore,bahubali);
    }

    private void createTheatres() {
        Movies avengersMovies = movieController.getMovies("Avengers");
        Movies bahubaliMovies = movieController.getMovies("Bahubali");

        Theatre inox = new Theatre(1,"Inox");
        Screen screen1 = new Screen(1, createSeats(5,5));
        Screen screen2 = new Screen(2, createSeats(4,4));
        Show shows = new Show(1,screen1,12, avengersMovies);
        Show shows2 = new Show(2,screen1,15, avengersMovies);
        Show shows3 = new Show(3,screen1,18, avengersMovies);
        Show shows4 = new Show(4,screen2,11, bahubaliMovies);

        inox.addShow(avengersMovies,shows);
        inox.addShow(avengersMovies,shows2);
        inox.addShow(avengersMovies,shows3);
        inox.addShow(bahubaliMovies,shows4);

        inox.addScreen(screen1);
        inox.addScreen(screen2);

        Theatre pvr = new Theatre(2,"PVR");
        Screen screen3 = new Screen(1, createSeats(5,5));
        Screen screen4 = new Screen(2, createSeats(4,4));
        Show shows5 = new Show(1,screen3,12, avengersMovies);
        Show shows6 = new Show(2,screen3,15, avengersMovies);
        Show shows7 = new Show(3,screen3,18, avengersMovies);
        Show shows8 = new Show(4,screen4,11, bahubaliMovies);
        pvr.addScreen(screen3);
        pvr.addScreen(screen4);
        pvr.addShow(avengersMovies,shows5);
        pvr.addShow(avengersMovies,shows6);
        pvr.addShow(avengersMovies,shows7);
        pvr.addShow(bahubaliMovies,shows8);

        theatreController.addTheatre(City.Bangalore,inox);
        theatreController.addTheatre(City.NewDelhi,inox);

    }

    private Map<String,Seat> createSeats(int row, int col) {
        Map<String,Seat> seats = new HashMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                String id = i +"-"+ j;
                SeatCatogary seatCatogary = i>2? SeatCatogary.GOLD: SeatCatogary.SILVER;
                int price = seatCatogary == SeatCatogary.GOLD ? 200: 100;
                Seat seat = new Seat(id,row,col,price,seatCatogary);
                seats.put(id,seat);
            }
        }
        return seats;
    }
}
