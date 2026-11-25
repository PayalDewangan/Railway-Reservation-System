
public class Train {

    private String trainId;
    private String trainName;
    private String source;
    private String destination;
    private double fare;
    private String departureTime;
    private int totalSeats;
    private int bookedSeats;

    // Constructor
    public Train(String trainId, String trainName,
                 String source, String destination,
                 double fare, String departureTime,
                 int totalSeats) {

        this.trainId = trainId;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }

    // Getters
    public String getTrainId() {
        return trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public double getFare() {
        return fare;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    // Check available seats
    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

    // Book one seat
    public boolean bookSeat() {
        if (bookedSeats < totalSeats) {
            bookedSeats++;
            return true;
        }
        return false;
    }

    // Cancel one confirmed seat
    public void cancelSeat() {
        if (bookedSeats > 0) {
            bookedSeats--;
        }
    }

    // Display train details
    public void displayTrain() {
        System.out.println("--------------------------------");
        System.out.println("Train ID: " + trainId);
        System.out.println("Train Name: " + trainName);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Fare: Rs. " + fare);
        System.out.println("Departure: " + departureTime);
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Available Seats: " + getAvailableSeats());
        System.out.println("--------------------------------");
    }
}

