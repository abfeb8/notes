package design_patterns.abstract_factory_pattern.without_pattern;

public class Main {
    public static void main() {
        // Direct instantiation
        WinButton winButton = new WinButton();
        winButton.paint();

        MacButton macButton = new MacButton();
        macButton.paint();
    }
}

// Concrete classes without abstraction
class WinButton {
    public void paint() {
        System.out.println("Rendering a button in a Windows style.");
    }
}

class MacButton {
    public void paint() {
        System.out.println("Rendering a button in a Mac style.");
    }
}
