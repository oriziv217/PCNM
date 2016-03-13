package PCNMClient.PCNMClientView;

/**
 * Most common screen functionality
 * @author ori ziv
 */
public interface WindowMustHave {

    /**
     * Display Dialog box with messages to the user
     * @param text
     * @param type
     */
    public void showDialog(String text, DialogType type);
}
