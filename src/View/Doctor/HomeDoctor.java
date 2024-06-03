package View.Doctor;

import Model.Class.Doctor;
import Repository.RepoFactory;
import Service.DoctorService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeDoctor extends JFrame implements ActionListener {

    private final Doctor doctor;
    private final DoctorService doctorService;

    public HomeDoctor(Doctor doctor, RepoFactory repoFactory ){
        this.doctor = doctor;
        this.doctorService = new DoctorService(repoFactory);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
