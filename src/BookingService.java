import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {
    private List<Train> trainList = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();

    public BookingService() {
        trainList.add(new Train(101,"Maharani Express" ,"surat","Vadodara",100));
        trainList.add(new Train(104,"Mumbai Express" ,"Mumbai","Somnath",20));
        trainList.add(new Train(108,"Bullet Train" ,"Ludhiana","Goa",90));
        trainList.add(new Train(109,"Pune Express" ,"Canada","Delhi",82));
        trainList.add(new Train(199,"Chennai Way" ,"Hyderabad","Mumbai",1000));
    }

    public List<Train> searchTrain( String source , String destination ) {
        List<Train> res = new ArrayList<>();

        for (Train train : trainList ) {
            if(train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)) {
            res.add(train);
            }
        }
        return res;
    }

    public Ticket bookTicket(User user, int trainId , int seatCount) {

        for (Train train :  trainList) {
            if(train.getTrainId() == trainId) {
                if(train.bookSeats(seatCount)) {
                    Ticket ticket = new Ticket(user,train,seatCount);
                    ticketList.add(ticket);
                    return ticket;
                } else {
                    System.out.println("no enough ticket available");
                    return null;
                }
            }
        }
        System.out.println("Train ID not founded!");
        return null;
    }

    public List<Ticket> getTicketByUser(User user) {
        List<Ticket> res = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            if(ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                res.add(ticket);
            }
        }
        return res;
    }

    public boolean cancelTicket(int ticketId , User user) {
        Iterator<Ticket> iterator = ticketList.listIterator();

        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();

            if(ticket.getTicketId() == ticketId &&
               ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                Train train = ticket.getTrain();
                train.cancelSeat(ticket.getSeatBooked());
                iterator.remove();
                System.out.println("Ticket "+ticketId+ "Cancelled Successfully");
                return true;
            }
        }
        System.out.println("Ticket not found or does not belong to current user");
        return false;
    }

    public void listAllTrains() {
        System.out.println("\nList of all Trains : ");
        for ( Train train : trainList) {
            System.out.println(train);
        }
    }
}
