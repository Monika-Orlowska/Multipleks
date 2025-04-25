import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class BookingSystem {
    private Scanner scanner = new Scanner(System.in);
    private List<Cinema> cinemas;
    private Customer currentCustomer = null;

    public BookingSystem(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public void run() {
        System.out.println("Rezerwuj bilety jako: 1. Użytkownik  2. Gość");
        int role = scanner.nextInt();
        scanner.nextLine();

        if (role == 1) {
            handleUser();
        } else {
            handleGuest();
        }
    }

    private void handleUser() {
        System.out.print("Podaj imię: ");
        String name = scanner.nextLine();
        Customer customer = new Customer(name);

        while (true) {
            System.out.println("\n1. Do kina  2. Na film  3. Moje rezerwacje  4. Repertuar na najbliższy tydzień");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 3) {
                customer.printReservations();
                continue;
            }

            if (choice == 4) {
                showProgrammeForNextWeek();
                continue;
            }

            Screening screening = (choice == 1) ? chooseByCinema() : chooseByMovie();
            if (screening == null) continue;
            makeReservation(screening, customer);

            System.out.println("Chcesz zarezerwować kolejny bilet, czy wyświetlić swoje bilety? (t/w)");
            String decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("w")) {
                customer.printReservations();
            } else if (!decision.equalsIgnoreCase("t")) {
                break;
            }
        }

        customer.printReservations();
    }

    private void handleGuest() {
        while (true) {
            System.out.println("\n1. Do kina  2. Na film  3. Sprawdź repertuar na najbliższy tydzień");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 3) {
                showProgrammeForNextWeek();
            } else {
                Screening screening = (choice == 1) ? chooseByCinema() : chooseByMovie();
                if (screening != null) {
                    makeReservation(screening, null);
                    break;
                }
            }
        }
    }




    private Screening chooseByCinema() {
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).getName());
        }
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        Cinema selected = cinemas.get(index);
        selected.printProgramme();

        System.out.println("Wybierz numer seansu:");
        int sIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        return selected.getScreenings()[sIndex];
    }

    private Screening chooseByMovie() {
        System.out.println("Wybierz numer filmu:");
        Set<String> movieSet = new HashSet<>();
        for (Cinema cinema : cinemas) {
            for (Screening s : cinema.getScreenings()) {
                movieSet.add(s.getMovieTitle());
            }
        }

        List<String> movieList = new ArrayList<>(movieSet);
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println((i + 1) + ". " + movieList.get(i));
        }

        int movieIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        String selectedTitle = movieList.get(movieIndex);


        List<Screening> matchingScreenings = new ArrayList<>();
        for (Cinema cinema : cinemas) {
            for (Screening s : cinema.getScreenings()) {
                if (s.getMovieTitle().equalsIgnoreCase(selectedTitle)) {
                    matchingScreenings.add(s);
                }
            }
        }

        if (matchingScreenings.isEmpty()) return null;

        System.out.println("Wybierz kino:");
        for (int i = 0; i < matchingScreenings.size(); i++) {
            Screening s = matchingScreenings.get(i);
            System.out.println((i + 1) + ". " + s.getMovieTitle() + " - " + s.getFormattedDateTime() + " - " + s.getHall().getName() + " | " + getCinemaNameForScreening(s));
        }

        int choice = scanner.nextInt() - 1;
        scanner.nextLine();
        return matchingScreenings.get(choice);
    }

    private String getCinemaNameForScreening(Screening screening) {
        for (Cinema cinema : cinemas) {
            if (Arrays.asList(cinema.getScreenings()).contains(screening)) {
                return cinema.getName();
            }
        }
        return "Nieznane kino";
    }

    private void showProgrammeForNextWeek() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekLater = now.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("Repertuar na najbliższy tydzień (od " + now.format(formatter) + " do " + weekLater.format(formatter) + "):");
        for (Cinema cinema : cinemas) {
            System.out.println("\nKino: " + cinema.getName());
            boolean hasScreenings = false;
            for (Screening screening : cinema.getScreenings()) {
                if (!screening.getDateTime().isBefore(now) && screening.getDateTime().isBefore(weekLater)) {
                    System.out.println(screening.getMovieTitle() + " o " + screening.getFormattedDateTime() + " w " + screening.getHall().getName());
                    hasScreenings = true;
                }
            }
            if (!hasScreenings) {
                System.out.println("Brak seansów na najbliższy tydzień.");
            }
        }
    }
    private void makeReservation(Screening screening, Customer customer) {
        System.out.print("Podaj ilość biletów: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<String> selectedSeats = new ArrayList<>();
        System.out.println("1. Losowy wybór miejsc  2. Wybierz miejsca samodzielnie");
        int mode = scanner.nextInt();
        scanner.nextLine();

        List<String> available = screening.getHall().getAvailableSeats(screening.getReservedSeats());
        if (available.size() < count) {
            System.out.println("Nie ma wystarczającej liczby wolnych miejsc.");
            return;
        }

        if (mode == 1) {
            selectedSeats.addAll(available.subList(0, count));
        } else {
            while (selectedSeats.size() < count) {
                System.out.print("Podaj miejsce (np. A1): ");
                String seat = scanner.nextLine().toUpperCase();

                if (!screening.getHall().isSeatAvailable(seat, screening.getReservedSeats())) {
                    System.out.println("Miejsce " + seat + " jest już zajęte lub nie istnieje. Wybierz inne.");
                } else if (selectedSeats.contains(seat)) {
                    System.out.println("To miejsce zostało już przez Ciebie wybrane. Podaj inne.");
                } else {
                    selectedSeats.add(seat);
                }
            }
        }

        boolean reserved = screening.reservePlaces(selectedSeats.toArray(new String[0]));
        if (reserved) {
            String cinemaName = null;
            for (Cinema cinema : cinemas) {
                if (Arrays.asList(cinema.getScreenings()).contains(screening)) {
                    cinemaName = cinema.getName();
                    break;
                }
            }

            System.out.println("Zarezerwowano: " + selectedSeats +
                    " | Kino: " + cinemaName +
                    " | Film: " + screening.getMovieTitle() +
                    ", Sala: " + screening.getHall().getName() +
                    ", Data: " + screening.getFormattedDateTime());

            if (customer != null) {
                customer.addReservation(screening.getMovieTitle() +
                        " w " + screening.getHall().getName() +
                        " na " + screening.getFormattedDateTime() +
                        ", miejsca: " + selectedSeats);
            }
        } else {
            System.out.println("Rezerwacja nie powiodła się. Spróbuj ponownie.");
        }
    }
}
