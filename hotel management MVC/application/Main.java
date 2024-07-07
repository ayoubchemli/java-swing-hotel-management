package application;

import javax.swing.*;

import model.HotelDatabase;
import view.application.MainView;


public class Main {
    public static void main(String[] args) {
        HotelDatabase.populateRoomsList();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                MainView mainView = new MainView();
                mainView.setVisible(true);

            }
        });
    }
}