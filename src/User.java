public class User {
    private String userID;
    private String name;
    private String role;

    public User(String userID, String name, String role) {
        this.userID = userID;
        this.name = name;
        this.role = role;
    }

    public String getUserID() { return userID; }
    public String getName() { return name; }
    public String getRole() { return role; }
}