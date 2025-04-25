import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Cinema cinema1 = new Cinema("Super Tarasy", "ul. Akademicka 5");
        cinema1.addScreening(new Screening("James Bond", LocalDateTime.of(2025, 4, 28, 15, 30), cinema1.getHall("VIP")));
        cinema1.addScreening(new Screening("Avatar 2", LocalDateTime.of(2025, 5, 1, 20, 0), cinema1.getHall("3D")));
        cinema1.addScreening(new Screening("Jumanji", LocalDateTime.of(2025, 5, 8, 18, 15), cinema1.getHall("VIP")));
        cinema1.addScreening(new Screening("Paw Patrol", LocalDateTime.of(2025, 5, 16, 16, 0), cinema1.getHall("Sala 1")));
        cinema1.addScreening(new Screening("Paw Patrol", LocalDateTime.of(2025, 5, 12, 11, 30), cinema1.getHall("Sala 2")));
        cinema1.addScreening(new Screening("Matrix", LocalDateTime.of(2025, 5, 7, 18, 45), cinema1.getHall("Sala 1")));


        Cinema cinema2 = new Cinema("Mega Cinema", "ul. Kwiatowa 2");
        cinema2.addScreening(new Screening("Matrix", LocalDateTime.of(2025, 4, 7, 18, 45), cinema2.getHall("Sala 1")));
        cinema2.addScreening(new Screening("Avatar 2", LocalDateTime.of(2025, 4, 9, 16, 30), cinema2.getHall("Sala 2")));
        cinema2.addScreening(new Screening("Matrix", LocalDateTime.of(2025, 4, 6, 10, 15), cinema2.getHall("Sala 1")));
        cinema2.addScreening(new Screening("Avatar 2", LocalDateTime.of(2025, 4, 8, 14, 0), cinema2.getHall("Sala 2")));
        cinema2.addScreening(new Screening("James Bond", LocalDateTime.of(2025, 5, 8, 15, 30), cinema2.getHall("VIP")));


        Cinema cinema3 = new Cinema("City Cinema", "ul. Nowa 10");
        cinema3.addScreening(new Screening("Jurassic Park", LocalDateTime.of(2025, 4, 12, 18, 0), cinema3.getHall("Sala 1")));
        cinema3.addScreening(new Screening("Titanic", LocalDateTime.of(2025, 5, 3, 14, 0), cinema3.getHall("VIP")));
        cinema3.addScreening(new Screening("The Lion King", LocalDateTime.of(2025, 5, 4, 11, 30), cinema3.getHall("Sala 2")));
        cinema3.addScreening(new Screening("Spider-Man", LocalDateTime.of(2025, 5, 5, 16, 45), cinema3.getHall("3D")));
        cinema3.addScreening(new Screening("Frozen 2", LocalDateTime.of(2025, 5, 16, 20, 30), cinema3.getHall("Sala 1")));
        cinema3.addScreening(new Screening("James Bond", LocalDateTime.of(2025, 5, 5, 21, 30), cinema3.getHall("VIP")));


        Cinema cinema4 = new Cinema("Galaxy Cinema", "ul. Gwiaździsta 18");
        cinema4.addScreening(new Screening("Matrix", LocalDateTime.of(2025, 4, 27, 17, 30), cinema4.getHall("Sala 1")));
        cinema4.addScreening(new Screening("Avatar 2", LocalDateTime.of(2025, 5, 8, 19, 0), cinema4.getHall("Sala 2")));
        cinema4.addScreening(new Screening("Jumanji", LocalDateTime.of(2025, 5, 9, 18, 0), cinema4.getHall("Sala 1")));
        cinema4.addScreening(new Screening("James Bond", LocalDateTime.of(2025, 5, 9, 16, 0), cinema4.getHall("3D")));
        cinema4.addScreening(new Screening("Avatar 2", LocalDateTime.of(2025, 5, 1, 12, 0), cinema4.getHall("VIP")));
        cinema4.addScreening(new Screening("The Lion King", LocalDateTime.of(2025, 5, 4, 11, 30), cinema4.getHall("Sala 2")));



        List<Cinema> cinemas = Arrays.asList(cinema1, cinema2, cinema3, cinema4);
        BookingSystem bookingSystem = new BookingSystem(cinemas);
        bookingSystem.run();
    }
}
