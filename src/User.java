public class User {

    private String username;
    private String password;
    private String fullName;
    private String contact;

    public User(String username, String password, String fullName, String contact) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.contact = contact;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public String getContact() { return contact; }

    @Override
    public String toString() {
        return fullName+ " ("+username+")";
    }
}
