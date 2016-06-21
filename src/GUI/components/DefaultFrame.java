package gui.components;

import javax.swing.*;
import javax.swing.WindowConstants;

/**
 * Template for default frame.
 */
public class DefaultFrame extends JFrame {
    private final String TITLE = "PsychoProductions Admin Client";
    private final int defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE;

    /**
     * Basic constructor with default close operation.
     * @param frameWidth width in pixels
     * @param frameHeight height in pixels
     */
    public DefaultFrame(int frameWidth, int frameHeight) {
        super();
        setTitle(TITLE);
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(defaultCloseOperation);
    }

    /**
     * Basic constructor with default close operation and alternate title.
     * @param frameWidth width in pixels
     * @param frameHeight height in pixels
     * @param frameTitle the title to be displayed on the frame
     */
    public DefaultFrame(int frameWidth, int frameHeight, String frameTitle) {
        super();
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(defaultCloseOperation);
    }

    /**
     * Constructor allowing to choose the value of setDefaultCloseOperation
     * Reference: https://docs.oracle.com/javase/7/docs/api/javax/swing/WindowConstants.html
     * @param frameWidth width in pixels
     * @param frameHeight height in pixels
     * @param closeOperation Must be one of:
     *                       WindowConstants.DO_NOTHING_ON_CLOSE,
     *                       WindowConstants.HIDE_ON_CLOSE,
     *                       WindowConstants.DISPOSE_ON_CLOSE,
     *                       WindowConstants.EXIT_ON_CLOSE
     */
    public DefaultFrame(int frameWidth, int frameHeight, int closeOperation) {
        super();
        setTitle(TITLE);
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(closeOperation);
    }

    /**
     * Constructor allowing to choose the value of setDefaultCloseOperation and alternate title.
     * Reference: https://docs.oracle.com/javase/7/docs/api/javax/swing/WindowConstants.html
     * @param frameWidth width in pixels
     * @param frameHeight height in pixels
     * @param frameTitle the title to be displayed on the frame
     * @param closeOperation Must be one of:
     *                       WindowConstants.DO_NOTHING_ON_CLOSE,
     *                       WindowConstants.HIDE_ON_CLOSE,
     *                       WindowConstants.DISPOSE_ON_CLOSE,
     *                       WindowConstants.EXIT_ON_CLOSE
     */
    public DefaultFrame(int frameWidth, int frameHeight, String frameTitle, int closeOperation) {
        super();
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(closeOperation);
    }
}
