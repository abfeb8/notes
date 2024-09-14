package design_patterns.abstract_factory_pattern.with_pattern;

// Client code
public class Main {

    private static Application configureApplication() {

        Application app; //business application
        GUIFactory factory; //factory to provide UI elements for the app

        String osName = System.getProperty("os.name").toLowerCase(); //operating system where app will run
        //get concrete factory based on the OS
        if (osName.contains("win")) {
            factory = new WinGUIFactory();
        } else {
            factory = new MacGUIFactory();
        }
        //provide GUIFactory to app.
        app = new Application(factory);

        return app;
    }

    public static void main() {
        Application app = configureApplication();
        app.paint();
    }
}
