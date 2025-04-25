import java.util.*;

class Customer {
    private String name;
    private List<String> reservations = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addReservation(String info) {
        reservations.add(info);
    }

    public void printReservations() {
        System.out.println("\nTwoje rezerwacje:");
        for (String r : reservations) {
            System.out.println("- " + r);
        }
    }
}
