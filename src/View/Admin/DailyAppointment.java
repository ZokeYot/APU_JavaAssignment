package View.Admin;

import Model.Class.Admin;
import Service.AdminService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DailyAppointment extends JFrame implements ActionListener {

    private final AdminService adminService;
    private final Admin admin;

    public DailyAppointment(Admin admin, AdminService adminService){
        this.adminService =  adminService;
        this.admin = admin;
        init_view();
    }

    private void init_view(){

    }

    private void back_HomeAdmin(){
        HomeAdmin view = new HomeAdmin(admin, adminService);
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
