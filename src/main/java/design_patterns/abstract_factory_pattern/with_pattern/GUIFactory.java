package design_patterns.abstract_factory_pattern.with_pattern;

// Abstract factory interface
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
