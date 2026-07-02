import java.util.HashMap;
import java.util.Map;

public class UserService {

    // username --> User object mapping
    // Example: "Vansh123" -> User instance
    private Map<String, User> userMap = new HashMap<>();

    // Keeps track of the currently logged-in user (null if none)
    private User currentUser = null;

    /**
     * Method to register a new user.
     */
    public boolean registerUser(String username, String password, String fullName, String contact) {

        if (userMap.containsKey(username)) {
            System.out.println("Username already taken, Please choose another");
            return false;
        }

        User user = new User(username, password, fullName, contact);
        userMap.put(username, user);
        System.out.println("Register Successful");

        return true;
    }

    /**
     * Method to log in an existing user.
     */
    public boolean loginUser(String username, String password) {

        if (!userMap.containsKey(username)) {
            System.out.println("No User Found with this username");
            return false;
        }

        User user = userMap.get(username);

        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect Password"); // Wrong password case
            return false;
        }
        currentUser = user;
        System.out.println("Welcome : " + currentUser.getFullName());

        return true;
    }

    /**
     * Method to log out the currently logged-in user.
     */
    public void logoutUser() {

        if (currentUser != null) {

            System.out.println("Logged Out " + currentUser.getFullName());
        }
        currentUser = null;
    }

    /**
     * Getter method to return the currently logged-in user.
     */
    public User getCurrUser() {
        return currentUser;
    }

    /**
     * Check whether a user is logged in or not.
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

}
