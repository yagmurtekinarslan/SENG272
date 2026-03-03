import javax.swing.*;

void main() {

    JFrame frame = new JFrame("Software Project Registration Form");
    frame.setSize(500, 450);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    ProjectFormPanel panel = new ProjectFormPanel();
    frame.add(panel);

    frame.setVisible(true);

}
