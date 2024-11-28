package com.vijay;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App1 extends Frame implements ActionListener {
    TextField nameField, emailField,idField;
    Button submitButton;
    Button update;
    
    public App1() {
        setLayout(new FlowLayout());

        Label nameLabel = new Label("Name:");
        nameField = new TextField(20);
        add(nameLabel);
        add(nameField);

        Label emailLabel = new Label("Email:");
        emailField = new TextField(20);
        add(emailLabel);
        add(emailField);
        
        Label id = new Label("id");
        idField = new TextField(20);
        add(id);
        add(idField);

        submitButton = new Button("Submit");
        submitButton.addActionListener(this);
        add(submitButton);
        
        update = new Button("Update");
        update.addActionListener(this);
        add(update);
        

        setTitle("User Form");
        setSize(300, 150);
        setVisible(true);
        
        addWindowListener(new WindowAdapter()
        {
                public void windowClosing(WindowEvent e)
                {
                        System.out.println("WindowAdapter - windowClosing Called ...");
                        System.exit(0);
                        
                }        
         });


    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            saveUser(name, email);
            JOptionPane.showMessageDialog(this, "User saved successfully!");
        }
        else if(e.getSource()== update) {
        	String name= nameField.getText();
            String email = emailField.getText();
            int id = Integer.parseInt(idField.getText());
            update(id,name , email);
            
        }
    }

    private void saveUser(String name, String email) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            session.save(user);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
    
    private void update(int id ,String name , String email) {
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setName(email);
            session.update(user);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

    public static void main(String[] args) {
        new App1();
    }
}
