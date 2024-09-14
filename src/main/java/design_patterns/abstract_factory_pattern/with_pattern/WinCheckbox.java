package design_patterns.abstract_factory_pattern.with_pattern;

public class WinCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a checkbox in a Windows style.");
    }
}
