package gui.components;

import javax.swing.*;

/**
 * Basic button with centered text.
 */
public class ButtonSimple extends JButton {
    /**
     * Constructor for basic button
     * @param text the text to display on the button
     * @param actionCommand the name of the action command of the button
     */
    public ButtonSimple(String text, String actionCommand) {
        super();
        setText(text);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setVerticalTextPosition(AbstractButton.CENTER);
        setActionCommand(actionCommand);
    }
    /**
     * Constructor for basic button
     * @param text the text to display on the button
     * @param actionCommand the name of the action command of the button
     * @param tooltip the tooltip text to display when button is hovered
     */
    public ButtonSimple(String text, String actionCommand, String tooltip) {
        super();
        setText(text);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setVerticalTextPosition(AbstractButton.CENTER);
        setActionCommand(actionCommand);
        setToolTipText(tooltip);
    }
}
