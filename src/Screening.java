import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


class Screening {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String movieTitle;
    private LocalDateTime dateTime;
    private Hall hall;
    private Set<String> reservedSeats = new HashSet<>();

    public Screening(String movieTitle, LocalDateTime dateTime, Hall hall) {
        this.movieTitle = movieTitle;
        this.dateTime = dateTime;
        this.hall = hall;
    }

    public boolean reservePlaces(String[] seats) {
        for (String seat : seats) {
            if (reservedSeats.contains(seat)) {
                return false;
            }
        }
        reservedSeats.addAll(Arrays.asList(seats));
        return true;
    }

    public Set<String> getReservedSeats() {
        return reservedSeats;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Hall getHall() {
        return hall;
    }

    public String getFormattedDateTime() {
        return dateTime.format(FORMATTER);
    }
}