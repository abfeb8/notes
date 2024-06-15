package solid.liskov_substitution.incomplient;

/**
 * Ostrich subclass overriding the fly() method which is not possible
 */
public class Ostrich extends Bird {

    @Override
    public void fly(){
        throw new UnsupportedOperationException();
    }

}
