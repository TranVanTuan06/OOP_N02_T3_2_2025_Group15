public class Table {
    private String tableID;
    private int capacity;
    private boolean hasCustomer;

    public Table(String tableID, int capacity, boolean hasCustomer) {
        this.tableID = tableID;
        this.capacity = capacity;
        this.hasCustomer = hasCustomer;
    }

    public String getTableID() { return tableID; }
    public int getCapacity() { return capacity; }
    public boolean hasCustomer() { return hasCustomer; }
    public void setCapacity(int newCap) { this.capacity = newCap; }
}