import java.util.*;

class Hall {
    private String name;
    private int capacity;
    private Set<String> seats = new HashSet<>();

    public Hall(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        initializeSeats();
    }

    private void initializeSeats() {
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (char row : rows) {
            for (int seat = 1; seat <= capacity / rows.length; seat++) {
                seats.add(row + String.valueOf(seat));
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getAvailableSeats(Set<String> reservedSeats) {
        List<String> available = new ArrayList<>(seats);
        available.removeAll(reservedSeats);
        return available;
    }

    public boolean isSeatAvailable(String seat, Set<String> reservedSeats) {
        return seats.contains(seat) && !reservedSeats.contains(seat);
    }
}