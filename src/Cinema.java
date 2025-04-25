import java.time.LocalDateTime;
import java.util.*;

class Cinema {
    private String name;
    private String address;
    private List<Hall> halls = new ArrayList<>();
    private List<Screening> screenings = new ArrayList<>();

    public Cinema(String name, String address) {
        this.name = name;
        this.address = address;
        initializeHalls();
    }

    private void initializeHalls() {
        halls.add(new Hall("Sala 1", 100));
        halls.add(new Hall("Sala 2", 80));
        halls.add(new Hall("VIP", 25));
        halls.add(new Hall("3D", 120));
    }

    public void addScreening(Screening screening) {
        screenings.add(screening);
    }

    public void printProgramme() {
        System.out.println("Repertuar " + name + " (" + address + "):");
        for (int i = 0; i < screenings.size(); i++) {
            Screening screening = screenings.get(i);

            String formattedDate = screening.getFormattedDateTime();
            System.out.println((i + 1) + ". " + screening.getMovieTitle() + " - " + formattedDate + " - " + screening.getHall().getName());
        }
    }


    public Screening[] getScreenings() {
        return screenings.toArray(new Screening[0]);
    }

    public Screening findMovie(String title) {
        for (Screening screening : screenings) {
            if (screening.getMovieTitle().toLowerCase().contains(title.toLowerCase())) {
                return screening;
            }
        }
        return null;
    }

    public Hall getHall(String name) {
        for (Hall hall : halls) {
            if (hall.getName().equalsIgnoreCase(name)) {
                return hall;
            }
        }
        return null;
    }

    public void printProgrammeForNextWeek() {
        System.out.println("Repertuar " + name + " (" + address + ") na najbliższy tydzień:");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextWeek = now.plusWeeks(1);

        for (Screening screening : screenings) {
            if (screening.getDateTime().isAfter(now) && screening.getDateTime().isBefore(nextWeek)) {
                System.out.println(screening.getMovieTitle() + " - " + screening.getFormattedDateTime() + " - Sala: " + screening.getHall().getName());
            }
        }
    }


    public String getName() {
        return name;
    }
}