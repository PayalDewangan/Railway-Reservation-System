
public class Passenger {

    private String passengerId;
    private String passengerName;
    private int age;

    // Constructor
    public Passenger(String passengerId, String passengerName, int age) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.age = age;
    }

    // Getters
    public String getPassengerId() {
        return passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getAge() {
        return age;
    }

    // Display passenger details
    public void displayPassenger() {
        System.out.println("Passenger ID: " + passengerId);
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Age: " + age);
    }
}

