import view.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {    // Starts the GUI application safely using Swing thread system.
            new MainFrame();   // Creates and displays the main application window.
        });
    }
}