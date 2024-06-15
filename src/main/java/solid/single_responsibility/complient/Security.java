package solid.single_responsibility.complient;

/**
 * class is only responsible to check if user has proper authorisation
 */
public class Security {

    public boolean checkAccess(Object user) {
        // Verify if the user has access
        return true;
    }

}
