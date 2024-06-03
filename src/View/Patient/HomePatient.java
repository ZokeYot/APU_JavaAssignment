package View.Patient;

import Model.Class.Patient;
import Repository.RepoFactory;
import Service.PatientService;

import javax.swing.*;

public class HomePatient extends JFrame {

    private final Patient patient;
    private final PatientService patientService;

    public HomePatient(Patient patient, RepoFactory repoFactory ){
        this.patient = patient;
        this.patientService = new PatientService(repoFactory);
        System.out.println(patient.toString());
    }




}
