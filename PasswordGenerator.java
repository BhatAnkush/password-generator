import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class PasswordGenerator extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final String UPPER_CASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBER_CHARACTERS = "0123456789";
    private static final String SYMBOL_CHARACTERS = "!@#$%^&*()_+-=[]{}\\|;':\",./<>?";

    private JTextField passwordField;
    private JPasswordField passwordLengthField;
    private JCheckBox upperCaseCheckBox;
    private JCheckBox lowerCaseCheckBox;
    private JCheckBox numberCheckBox;
    private JCheckBox symbolCheckBox;

    public PasswordGenerator() {
        setTitle("Password Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Password:"));
        passwordField = new JTextField(20);
        passwordField.setEditable(false);
        passwordPanel.add(passwordField);
        mainPanel.add(passwordPanel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        passwordLengthField = new JPasswordField(3);
        passwordLengthField.setText("12");
        optionsPanel.add(new JLabel("Password length:"));
        optionsPanel.add(passwordLengthField);
        upperCaseCheckBox = new JCheckBox("Include uppercase letters");
        upperCaseCheckBox.setSelected(true);
        optionsPanel.add(upperCaseCheckBox);
        lowerCaseCheckBox = new JCheckBox("Include lowercase letters");
        lowerCaseCheckBox.setSelected(true);
        optionsPanel.add(lowerCaseCheckBox);
        numberCheckBox = new JCheckBox("Include numbers");
        numberCheckBox.setSelected(true);
        optionsPanel.add(numberCheckBox);
        symbolCheckBox = new JCheckBox("Include symbols");
        symbolCheckBox.setSelected(true);
        optionsPanel.add(symbolCheckBox);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(this);
        buttonPanel.add(generateButton);
        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(this);
        buttonPanel.add(copyButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String generatePassword(int length, boolean useUpperCase, boolean useLowerCase, boolean useNumbers,
            boolean useSymbols) {
        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();

        String charSet = "";
        if (useUpperCase) {
            charSet += UPPER_CASE_CHARACTERS;
        }
        if (useLowerCase) {
            charSet += LOWER_CASE_CHARACTERS;
        }
        if (useNumbers) {
            charSet += NUMBER_CHARACTERS;
        }
        if (useSymbols) {
            charSet += SYMBOL_CHARACTERS;
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charSet.length());
            char randomChar = charSet.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Generate")) {
            int passwordLength = Integer.parseInt(new String(passwordLengthField.getPassword()));

            boolean useUpperCase = upperCaseCheckBox.isSelected();
            boolean useLowerCase = lowerCaseCheckBox.isSelected();
            boolean useNumbers = numberCheckBox.isSelected();
            boolean useSymbols = symbolCheckBox.isSelected();
            String password = generatePassword(passwordLength, useUpperCase, useLowerCase, useNumbers, useSymbols);
            passwordField.setText(password);
        } else if (e.getActionCommand().equals("Copy")) {
            StringSelection selection = new StringSelection(passwordField.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }

    public static void main(String[] args) {
        new PasswordGenerator();
    }
}
