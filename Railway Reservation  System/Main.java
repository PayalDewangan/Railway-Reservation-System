
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookingSystem system = new BookingSystem();
        RailwayGraph railwayGraph = new RailwayGraph();

        // Load saved trains
        ArrayList<Train> savedTrains = FileManager.loadTrains();

        if (savedTrains.isEmpty()) {

            system.addTrain(new Train(
                "T101", "Express", "Raipur",
                "Durg", 250, "09:30", 2
            ));

            system.addTrain(new Train(
                "T102", "Superfast", "Durg",
                "Nagpur", 350, "12:00", 3
            ));

            system.addTrain(new Train(
                "T103", "Passenger", "Raipur",
                "Bilaspur", 150, "15:00", 2
            ));

        } else {

            for (Train train : savedTrains) {
                system.addTrain(train);
            }
        }

        // Restore previous bookings
        system.loadBookings();

        // Create railway graph
        railwayGraph.addRoute("Raipur", "Durg");
        railwayGraph.addRoute("Durg", "Nagpur");
        railwayGraph.addRoute("Raipur", "Bilaspur");
        railwayGraph.addRoute("Bilaspur", "Nagpur");

        int choice = 0;

        do {

            System.out.println("\n==================================");
            System.out.println("     RAILWAY RESERVATION SYSTEM");
            System.out.println("==================================");
            System.out.println("1. View Available Trains");
            System.out.println("2. Search Railway Route");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Search Ticket");
            System.out.println("6. Sort Trains by Fare");
            System.out.println("7. View All Bookings");
            System.out.println("8. Exit");
            System.out.println("==================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    system.viewTrains();
                    break;

                case 2:
                    System.out.print("Enter source station: ");
                    String source = sc.nextLine();

                    System.out.print("Enter destination station: ");
                    String destination = sc.nextLine();

                    railwayGraph.searchRoute(source, destination);
                    break;

                case 3:
                    System.out.print("Enter Passenger ID: ");
                    String passengerId = sc.nextLine();

                    System.out.print("Enter Passenger Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Passenger Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Train ID: ");
                    String trainId = sc.nextLine();

                    Passenger passenger =
                        new Passenger(passengerId, name, age);

                    system.bookTicket(passenger, trainId);
                    break;

                case 4:
                    System.out.print("Enter Ticket ID: ");
                    String cancelId = sc.nextLine();

                    system.cancelTicket(cancelId);
                    break;

                case 5:
                    System.out.print("Enter Ticket ID: ");
                    String searchId = sc.nextLine();

                    system.searchTicket(searchId);
                    break;

                case 6:
                    system.sortTrainsByFare();
                    break;

                case 7:
                    system.viewAllBookings();
                    break;

                case 8:
                    FileManager.saveData(
                        system.getTrains(),
                        system.getTickets()
                    );

                    System.out.println(
                        "Thank you for using Railway Reservation System!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 8);

        sc.close();
    }
}
