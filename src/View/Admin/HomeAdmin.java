package View.Admin;

import Model.Class.Admin;
import Repository.RepoFactory;
import Repository.UserRepo;
import Service.AdminService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAdmin extends JFrame implements ActionListener {

    private final AdminService adminService;
    private final Admin admin;

    public HomeAdmin(Admin admin , RepoFactory repoFactory){
        this.admin = admin;
        this.adminService = new AdminService(repoFactory);

        init_view();
    }

    public HomeAdmin(Admin admin, AdminService adminService){
        this.admin = admin;
        this.adminService = adminService;

        init_view();
    }

    private void init_view(){
        setVisible(true);
        setSize(1000, 1000);
    }


    private void init_CollectPayment(){
        CollectPayment view = new CollectPayment(admin, adminService);
        dispose();
    }

    private void init_UserRegistration(){
        UserRegistration view = new UserRegistration(admin, adminService);
        dispose();
    }

    private void init_WalkInAppointment(){
        WalkInAppointment view = new WalkInAppointment(admin, adminService);
        dispose();
    }

    private void init_DailyAppointment(){
        DailyAppointment view = new DailyAppointment(admin, adminService);
        dispose();
    }

    private void init_PatientMedicalRecord(){
        PatientMedicalRecord view = new PatientMedicalRecord(admin, adminService);
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
