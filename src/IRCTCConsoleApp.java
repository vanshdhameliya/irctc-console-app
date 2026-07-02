import java.util.List;
import java.util.Scanner;

public class IRCTCConsoleApp {

    private final Scanner sc = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        new IRCTCConsoleApp().start();
    }

    public void start() {
        while (true) {
            System.out.println("\n------Welcome to IRCTC App------ ");

            if(!userService.isLoggedIn()) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your Choice : ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> exitApp();
                    default -> System.out.println("Invalid Choice.");

                }
            } else {
                showUserMenu();
            }
        }
    }

    public void register() {
        System.out.print("Enter Username: ");
        String username = sc.next();
        System.out.print("enter Password: ");
        String password = sc.next();
        System.out.print("Enter Full Name: ");
        sc.nextLine();
        String fullName = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.next();

        userService.registerUser(username,password,fullName,contact);
    }

    public void login() {
        System.out.print("Enter Username: ");
        String username = sc.next();
        System.out.print("enter Password: ");
        String password = sc.next();

        userService.loginUser(username,password);
    }

    private void showUserMenu() {
        while (userService.isLoggedIn()) {
            System.out.println("\n----- User Menu -----");
            System.out.println("1. Search Train: ");
            System.out.println("2. Book Ticket: ");
            System.out.println("3. View My Ticket: ");
            System.out.println("4. Cancel Ticket: ");
            System.out.println("5. View All Trains: ");
            System.out.println("6. Logged Out: ");
            System.out.print("\nEnter Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> searchTrain();
                case 2 -> bookTicket();
                case 3 -> viewMyTicket();
                case 4 -> cancelTicket();
                case 5 -> bookingService.listAllTrains();
                case 6 -> userService.logoutUser();
                default -> System.out.println("Invalid Choice");
            }
        }
    }

    private void searchTrain() {
        System.out.print("Enter Source Station: ");
        String source = sc.next();
        System.out.print("Enter Destination Station: ");
        String destination = sc.next();

        List<Train> trains = bookingService.searchTrain(source,destination);
        if (trains.isEmpty()) {
            System.out.print("\nNo Trains Founded between "+source+ " and "+destination);
            return;
        }
        System.out.println("Train Found:");
        for (Train t : trains) {
            System.out.println(t);
        }

        System.out.print("\nDo you want to book ticket ? (yes / no): ");
        String choice = sc.next();

        if(choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter Train ID to book: ");
            int trainID = sc.nextInt();
            System.out.println("Enter Number of Seats To Book: ");
            int seats = sc.nextInt();

            Ticket ticket = bookingService.bookTicket(userService.getCurrUser(),trainID,seats);
            if( ticket!=null ) {
                System.out.println("Booking Successful");
                System.out.println(ticket);
            }
            else {
                System.out.println("Returning to user menu");
            }
        }
    }

    private void bookTicket() {
        System.out.print("Enter Source Station: ");
        String source = sc.next();
        System.out.print("Enter Destination Station: ");
        String destination = sc.next();

        List<Train> trains = bookingService.searchTrain(source,destination);
        if (trains.isEmpty()) {
            System.out.println("No Trains Available between for booking");
            return;
        }
        System.out.println("Available Trains: ");
        for (Train t : trains) {
            System.out.println(t);
        }

        System.out.print("Enter Train ID to book: ");
        int trainID = sc.nextInt();
        System.out.print("Enter Number of Seats To Book: ");
        int seats = sc.nextInt();

        Ticket ticket = bookingService.bookTicket(userService.getCurrUser(),trainID,seats);
        if( ticket!=null ) {
            System.out.println("Booking Successful");
            System.out.println(ticket);
        }
        else {
            System.out.println("Returning to user menu");
        }
    }

    public void viewMyTicket() {
        List<Ticket> ticketByUser = bookingService.getTicketByUser(userService.getCurrUser());

        if(ticketByUser.isEmpty()) {
            System.out.println("no ticket booked yet");
        } else {
            System.out.println("your Tickets: ");
            for (Ticket ticket : ticketByUser) {
                System.out.println(ticket);
            }
        }
    }

    private void cancelTicket() {
        System.out.print("Enter Train ID to book: ");
        int trainID = sc.nextInt();
        bookingService.cancelTicket(trainID,userService.getCurrUser());
    }

    private void exitApp() {
        System.out.println("Thank you for using IRCTC App..");
        System.exit(0);
    }

}
