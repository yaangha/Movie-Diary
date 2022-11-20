package edu.java.project.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.project.controller.MovieDaoImpl;
import edu.java.project.model.Movie;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.JButton;

public class MainGallary extends JFrame {

    private MovieDaoImpl daoMovie;
    
    private JPanel contentPane;
    private JPanel panel;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
    private JButton btnNewButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainGallary frame = new MainGallary();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainGallary() {
        
        this.daoMovie = MovieDaoImpl.getInstance();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 969, 530);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        panel = new JPanel();
        panel.setBounds(41, 54, 900, 400);
        contentPane.add(panel);
        panel.setLayout(null);
        
        makeMovieGallary();
        
    }

    private void makeMovieGallary() {
        /*
        List<Movie> list = daoMovie.select("y210he");
        for (int i = 0; i < list.size(); i++) {
            JLabel lblImage = new JLabel(chaneImageSize(list.get(i).getImageName()));
            panel.add(lblImage);
        }
        */
        
        List<Movie> list = daoMovie.select("y210he");
        for (int i = 0; i < list.size(); i++) {
            JButton lblImage = new JButton(chaneImageSize(list.get(i).getImageName()));
            int x = 0;
            int y = 0;
            lblImage.setBounds(0, 0, 90, 60);
            panel.add(lblImage);      
        }
        
    }
    
    // 이미지 조절 메소드
    public ImageIcon chaneImageSize(String imgName) {        
        ImageIcon icon = new ImageIcon(imgName);
        Image img = icon.getImage();
        Image changeImg = img.getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);
        
        return changeIcon; 
    }
}
