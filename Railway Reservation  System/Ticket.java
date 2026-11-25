
public class Ticket {

    private String ticketId;
    private Passenger passenger;
    private Train train;
    private String status;

    // Constructor
    public Ticket(String ticketId, Passenger passenger,
                  Train train, String status) {

        this.ticketId = ticketId;
        this.passenger = passenger;
        this.train = train;
        this.status = status;
    }

    // Getters
    public String getTicketId() {
        return ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Train getTrain() {
        return train;
    }

    public String getStatus() {
        return status;
    }

    // Update ticket status
    public void setStatus(String status) {
        this.status = status;
    }

    // Display ticket details
    public void displayTicket() {

        System.out.println("\n========== TICKET DETAILS ==========");
        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Passenger: " + passenger.getPassengerName());
        System.out.println("Train ID: " + train.getTrainId());
        System.out.println("Train Name: " + train.getTrainName());
        System.out.println("Source: " + train.getSource());
        System.out.println("Destination: " + train.getDestination());
        System.out.println("Fare: Rs. " + train.getFare());
        System.out.println("Status: " + status);
        System.out.println("====================================");
    }
}
