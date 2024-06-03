package View.Admin;

import Model.Class.Admin;
import Model.Class.User;
import Service.AdminService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistration extends JFrame implements ActionListener {

    private final AdminService adminService;
    private final Admin admin;


    public UserRegistration(Admin admin, AdminService adminService){
        this.admin = admin;
        this.adminService = adminService;

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
