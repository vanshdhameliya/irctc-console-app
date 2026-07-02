public class Train {

    private int trainId;
    private String name;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;

    public Train(int trainId, String name, String source, String destination, int totalSeats) {
        this.trainId = trainId;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getTrainId() { return trainId; }
    public String getName() { return name; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public int getTotalSeats() { return totalSeats; }
    public int getAvailableSeats() { return availableSeats; }


    public boolean bookSeats(int count) {
        if(count <= availableSeats) {
            availableSeats -= count;
            return true;
        }
        return false;
    }

    public void cancelSeat(int count) {
        availableSeats += count;
    }

    @Override
    public String toString() {
        return trainId + " | " +name+ " | "
               +source+ " <---> " +destination+ "| Seats Available : "+availableSeats;
    }
}
