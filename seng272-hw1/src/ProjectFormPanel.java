import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectFormPanel extends JPanel {

    private JTextField txtProjectName = new JTextField(50);
    private JTextField txtTeamLeader = new JTextField(50);
    private JComboBox<String> comboTeamSize = new JComboBox<>(new String[]{"Choose", "1-3", "4-6", "7-10", "10+"});
    private JComboBox<String> comboProjectType = new JComboBox<>(new String[]{"Choose", "Web", "Mobile", "Desktop", "API"});
    private JTextField txtStartDate = new JTextField(20);
    private JButton btnSave = new JButton("Save");
    private JButton btnClear = new JButton("Clear");


    public ProjectFormPanel(){
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        add(new JLabel("Project Name:")); add(txtProjectName);
        add(new JLabel("Team Leader:")); add(txtTeamLeader);
        add(new JLabel("Team Size:")); add(comboTeamSize);
        add(new JLabel("Project Type:")); add(comboProjectType);
        add(new JLabel("Start Date (DD/MM/YYYY):")); add(txtStartDate);
        add(btnSave);
        add(btnClear);

        btnSave.addActionListener(e -> saveProject());
        btnClear.addActionListener(e -> clearForm());

    }


    private void saveProject() {

        String projectName = txtProjectName.getText().trim();
        String teamLeader  = txtTeamLeader.getText().trim();
        String teamSize    = (String) comboTeamSize.getSelectedItem();
        String projectType = (String) comboProjectType.getSelectedItem();
        String startDate   = txtStartDate.getText().trim();

        if (projectName.isEmpty() || teamLeader.isEmpty() || startDate.isEmpty()
                || comboTeamSize.getSelectedIndex() == 0
                || comboProjectType.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return; // dosyaya yazma
        }


        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String recordTime = LocalDateTime.now().format(fmt);

      
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("projects.txt", true))) {

            writer.write("=== Project Entry ===\n");
            writer.write("Project Name : " + projectName + "\n");
            writer.write("Team Leader : " + teamLeader + "\n");
            writer.write("Team Size : " + teamSize + "\n");
            writer.write("Project Type : " + projectType + "\n");
            writer.write("Start Date : " + startDate + "\n");
            writer.write("Record Time : " + recordTime + "\n");
            writer.write("====================\n\n");


            JOptionPane.showMessageDialog(
                    this,
                    "Saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "File error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void clearForm() {
        txtProjectName.setText("");
        txtTeamLeader.setText("");
        txtStartDate.setText("");

        comboTeamSize.setSelectedIndex(0);
        comboProjectType.setSelectedIndex(0);
    }


}
