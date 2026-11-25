
import java.io.*;
import java.util.*;

public class FileManager {

    private static final String FILE_NAME = "railway.txt";

    // Save trains and tickets
    public static void saveData(
            ArrayList<Train> trains,
            HashMap<String, Ticket> tickets) {

        try (PrintWriter writer =
                new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Train train : trains) {
                writer.println(
                    "TRAIN|" +
                    train.getTrainId() + "|" +
                    train.getTrainName() + "|" +
                    train.getSource() + "|" +
                    train.getDestination() + "|" +
                    train.getFare() + "|" +
                    train.getDepartureTime() + "|" +
                    train.getTotalSeats() + "|" +
                    train.getBookedSeats()
                );
            }

            for (Ticket ticket : tickets.values()) {

                Passenger p = ticket.getPassenger();

                writer.println(
                    "TICKET|" +
                    ticket.getTicketId() + "|" +
                    p.getPassengerId() + "|" +
                    p.getPassengerName() + "|" +
                    p.getAge() + "|" +
                    ticket.getTrain().getTrainId() + "|" +
                    ticket.getStatus()
                );
            }

            System.out.println("Records saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving records: " +
                e.getMessage());
        }
    }

    // Load trains
    public static ArrayList<Train> loadTrains() {

        ArrayList<Train> trains = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return trains;
        }

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                try {

                    Train train = null;
                    int bookedSeats = 0;

                    if (data.length == 9 &&
                        data[0].equals("TRAIN")) {

                        train = new Train(
                            data[1], data[2], data[3], data[4],
                            Double.parseDouble(data[5]),
                            data[6],
                            Integer.parseInt(data[7])
                        );

                        bookedSeats = Integer.parseInt(data[8]);

                    } else if (data.length == 8) {

                        train = new Train(
                            data[0], data[1], data[2], data[3],
                            Double.parseDouble(data[4]),
                            data[5],
                            Integer.parseInt(data[6])
                        );

                        bookedSeats = Integer.parseInt(data[7]);
                    }

                    if (train != null &&
                        bookedSeats >= 0 &&
                        bookedSeats <= train.getTotalSeats()) {

                        for (int i = 0; i < bookedSeats; i++) {
                            train.bookSeat();
                        }

                        trains.add(train);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid train record.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading trains: " +
                e.getMessage());
        }

        return trains;
    }

    // Load tickets
    public static ArrayList<String[]> loadTickets() {

        ArrayList<String[]> tickets = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return tickets;
        }

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length == 7 &&
                    data[0].equals("TICKET")) {

                    tickets.add(data);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading tickets: " +
                e.getMessage());
        }

        return tickets;
    }
}
