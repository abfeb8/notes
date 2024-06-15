package solid.single_responsibility.incomplient;

/**
 * this class is having two functionality
 * 1. update email for a user
 * 2. check if user has access to change email
 */
public class UserSettings {

    public void changeEmail(Object user) {
        if (checkAccess(user)) {
            // Update email in user settings
        }
    }

    private boolean checkAccess(Object user) {
        // Verify if the user has access
        return true;
    }
}
