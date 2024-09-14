package design_patterns.abstract_factory_pattern.with_pattern;

// Concrete product implementations for Windows
public class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a button in a Windows style.");
    }
}
