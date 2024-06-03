package View;

import Model.Class.Patient;
import Model.Class.User;
import Repository.RepoFactory;
import Service.RegisterService;
import View.Patient.HomePatient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class Register extends JFrame implements ActionListener {

    private final RepoFactory repoFactory;
    private final RegisterService registerService;


    // GUI Component
    private JPanel registerPanel;
    private JLabel registerLabel;
    private JTextField emailInput;
    private JTextField passwordInput;
    private JTextField nameInput;
    private JTextField ageInput;
    private JTextField genderInput;
    private JTextField heightInput;
    private JTextField weightInput;
    private JButton registerButton;

    public Register(RepoFactory repoFactory){
        this.repoFactory = repoFactory;
        registerService = new RegisterService(repoFactory);

        init_view();
    }


    private void init_view(){
        registerPanel = new JPanel();
        registerLabel  = new JLabel("Register Patient");
        emailInput = new JTextField("Email", 20);
        passwordInput = new JTextField("Password", 20);
        nameInput = new JTextField("Name", 20);
        ageInput = new JTextField("age", 20);
        genderInput = new JTextField("gender", 20);
        heightInput = new JTextField("height", 20);
        weightInput = new JTextField("weight", 20);
        registerButton = new JButton("Register");

        registerPanel.add(registerLabel);
        registerPanel.add(emailInput);
        registerPanel.add(passwordInput);
        registerPanel.add(nameInput);
        registerPanel.add(ageInput);
        registerPanel.add(genderInput);
        registerPanel.add(heightInput);
        registerPanel.add(weightInput);
        registerPanel.add(registerButton);
        registerButton.addActionListener(this);

        add(registerPanel);
        setVisible(true);
        setSize(1000, 1000);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailInput.getText();
        String password = passwordInput.getText();
        String name = nameInput.getText();
        int age = Integer.parseInt(ageInput.getText());
        String gender = genderInput.getText();
        double height = Double.parseDouble(heightInput.getText());
        double weight = Double.parseDouble(weightInput.getText());


        User newPatient = new User(name, password, email, "patient");
        Optional<Patient> patient =  registerService.register(newPatient, gender, age, height, weight);

        if(patient.isEmpty())
            JOptionPane.showMessageDialog(this, "Failed to register patient");
        else
            new HomePatient(patient.get(), repoFactory);
    }
}
