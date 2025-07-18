public class Table {
    String tableID;
    int capacity;
    boolean isAvailable;

    public Table(String tableID, int capacity, boolean isAvailable) {
        this.tableID = tableID;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
    }
}
