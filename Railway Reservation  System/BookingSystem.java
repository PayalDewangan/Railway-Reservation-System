
import java.util.*;

public class BookingSystem {

    private ArrayList<Train> trains;
    private HashMap<String, Ticket> tickets;
    private HashMap<String, Queue<Ticket>> waitingLists;

    private int ticketCounter = 101;

    public BookingSystem() {
        trains = new ArrayList<>();
        tickets = new HashMap<>();
        waitingLists = new HashMap<>();
    }

    // Add train
    public void addTrain(Train train) {
        trains.add(train);
        waitingLists.put(train.getTrainId(), new LinkedList<>());
    }

    // Generate ticket ID
    private String generateTicketId() {
        return "TKT" + ticketCounter++;
    }

    // Restore previous bookings
    public void loadBookings() {

        ArrayList<String[]> savedTickets =
            FileManager.loadTickets();

        for (String[] data : savedTickets) {

            try {
                String ticketId = data[1];
                String passengerId = data[2];
                String passengerName = data[3];
                int age = Integer.parseInt(data[4]);
                String trainId = data[5];
                String status = data[6];

                Train train = findTrain(trainId);

                if (train == null) continue;

                Passenger passenger =
                    new Passenger(passengerId, passengerName, age);

                Ticket ticket =
                    new Ticket(ticketId, passenger, train, status);

                tickets.put(ticketId, ticket);

                int number = Integer.parseInt(
                    ticketId.replace("TKT", "")
                );

                ticketCounter = Math.max(ticketCounter, number + 1);

                if (status.equals("WAITING")) {
                    waitingLists.get(trainId).offer(ticket);
                }

            } catch (Exception e) {
                System.out.println("Invalid ticket record skipped.");
            }
        }

        System.out.println("Previous bookings restored.");
    }

    // Find train
    private Train findTrain(String trainId) {

        for (Train train : trains) {
            if (train.getTrainId().equalsIgnoreCase(trainId)) {
                return train;
            }
        }

        return null;
    }

    // Save all data
    private void saveAll() {
        FileManager.saveData(trains, tickets);
    }

    // View trains
    public void viewTrains() {

        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        for (Train train : trains) {
            train.displayTrain();
        }
    }

    // Book ticket
    public void bookTicket(Passenger passenger, String trainId) {

        Train train = findTrain(trainId);

        if (train == null) {
            System.out.println("Train not found.");
            return;
        }

        String ticketId = generateTicketId();

        if (train.bookSeat()) {

            Ticket ticket = new Ticket(
                ticketId, passenger, train, "CONFIRMED"
            );

            tickets.put(ticketId, ticket);

            System.out.println("Ticket booked: " + ticketId);

        } else {

            Ticket ticket = new Ticket(
                ticketId, passenger, train, "WAITING"
            );

            tickets.put(ticketId, ticket);

            waitingLists.get(trainId).offer(ticket);

            System.out.println("Train full. Added to waiting list.");
        }

        saveAll();
    }

    // Cancel ticket
    public void cancelTicket(String ticketId) {

        Ticket ticket = tickets.get(ticketId);

        if (ticket == null ||
            ticket.getStatus().equals("CANCELLED")) {

            System.out.println("Ticket not found.");
            return;
        }

        Train train = ticket.getTrain();

        if (ticket.getStatus().equals("WAITING")) {

            waitingLists.get(train.getTrainId()).remove(ticket);
            ticket.setStatus("CANCELLED");

            System.out.println("Waiting ticket cancelled.");

        } else {

            ticket.setStatus("CANCELLED");
            train.cancelSeat();

            Queue<Ticket> queue =
                waitingLists.get(train.getTrainId());

            if (!queue.isEmpty()) {

                Ticket nextTicket = queue.poll();

                train.bookSeat();
                nextTicket.setStatus("CONFIRMED");

                System.out.println(
                    "Seat allocated to " +
                    nextTicket.getPassenger().getPassengerName()
                );

            } else {
                System.out.println("Ticket cancelled.");
            }
        }

        saveAll();
    }

    // Search ticket
    public void searchTicket(String ticketId) {

        Ticket ticket = tickets.get(ticketId);

        if (ticket == null) {
            System.out.println("Ticket " + ticketId + " not found.");
        } else {
            ticket.displayTicket();
        }
    }

    // View all bookings
    public void viewAllBookings() {

        if (tickets.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Ticket ticket : tickets.values()) {
            ticket.displayTicket();
        }
    }

    // Sort trains by fare
    public void sortTrainsByFare() {

        Collections.sort(trains,
            Comparator.comparingDouble(Train::getFare));

        System.out.println("\nTrains sorted by fare:");
        viewTrains();
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public HashMap<String, Ticket> getTickets() {
        return tickets;
    }
}


