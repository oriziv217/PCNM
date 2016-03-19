package PCNMClient.PCNMClientView;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Most common screen functionality
 * @author ori ziv
 */
public class WindowMustHave {

    /**
     * Display Dialog box with messages to the user
     * @param panel
     * @param text
     * @param type
     */
    public static void showDialog(JPanel panel, String text, DialogType type) {
        switch (type) {
            case ERROR:
                JOptionPane.showMessageDialog(panel, text, "PCNM Error", JOptionPane.ERROR_MESSAGE);
                break;
            case WARNING:
                JOptionPane.showMessageDialog(panel, text, "PCNM Error", JOptionPane.WARNING_MESSAGE);
                break;
            case INFO:
                JOptionPane.showMessageDialog(panel, text, "PCNM Error", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(panel, text, "PCNM Error", JOptionPane.NO_OPTION);
        }
    }
}
