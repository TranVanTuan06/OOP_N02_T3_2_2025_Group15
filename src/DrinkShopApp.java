import java.util.ArrayList;
import java.util.Scanner;

public class DrinkShopApp {
    ArrayList<Customer> listCustomers = new ArrayList<>();
    ArrayList<Table> listTables = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Customer> addCustomer(Customer c) {
        listCustomers.add(c);
        return listCustomers;
    }
    public ArrayList<Customer> deleteCustomer(String customerID) {
        for (int i = 0; i < listCustomers.size(); i++) {
            if (listCustomers.get(i).customerID.equals(customerID)) {
                listCustomers.remove(i);
                break;
            }
        }
        return listCustomers;
    }
    public void readCustomers() {
        for (Customer c : listCustomers) {
            System.out.println("Customer ID: " + c.customerID);
            System.out.println("Name: " + c.name);
            System.out.println("Phone: " + c.phone);
            System.out.println();
        }
    }
    public ArrayList<Customer> editCustomer(String customerID) {
        for (int i = 0; i < listCustomers.size(); i++) {
            if (listCustomers.get(i).customerID.equals(customerID)) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new phone: ");
                String newPhone = scanner.nextLine();

                listCustomers.get(i).name = newName;
                listCustomers.get(i).phone = newPhone;
                break;
            }
        }
        return listCustomers;
    }
    public ArrayList<Table> addTable(Table t) {
        listTables.add(t);
        return listTables;
    }
    public ArrayList<Table> deleteTable(String tableID) {
        for (int i = 0; i < listTables.size(); i++) {
            if (listTables.get(i).tableID.equals(tableID)) {
                listTables.remove(i);
                break;
            }
        }
        return listTables;
    }
    public void readTables() {
        for (Table t : listTables) {
            System.out.println("Table ID: " + t.tableID);
            System.out.println("Capacity: " + t.capacity);
            System.out.println("Status: " + (t.isAvailable ? "Available" : "Occupied"));
            System.out.println();
        }
    }
    public ArrayList<Table> editTable(String tableID) {
        for (int i = 0; i < listTables.size(); i++) {
            if (listTables.get(i).tableID.equals(tableID)) {
                System.out.print("Enter new capacity: ");
                int newCapacity = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Is available (true/false): ");
                boolean newStatus = scanner.nextBoolean();
                scanner.nextLine();

                listTables.get(i).capacity = newCapacity;
                listTables.get(i).isAvailable = newStatus;
                break;
            }
        }
        return listTables;
    }
    public void closeScanner() {
        scanner.close();
    }
}
