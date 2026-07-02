public class Ticket {

    private int ticketId;
    private User user;
    private Train train;
    private int seatBooked;
    private static int counter = 1001;

    public Ticket( User user, Train train, int seatBooked) {
        this.ticketId = counter++;
        this.user = user;
        this.train = train;
        this.seatBooked = seatBooked;
    }

    public int getTicketId() { return ticketId; }
    public User getUser() { return user; }
    public Train getTrain() { return train; }
    public int getSeatBooked() { return seatBooked; }
    public static int getCounter() { return counter; }

    @Override
    public String toString() {
        return "Ticket Id: "+ticketId+ " | Train: " +train.getName()+
                " | Route: "+train.getSource()+ " -> "+train.getDestination()+
                "| Seats: "+seatBooked+ " | Booked by : "+user.getFullName();

    }
}
