package design_patterns.abstract_factory_pattern.with_pattern;

// Concrete product implementations for Mac
public class MacCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a checkbox in a Mac style.");
    }
}
