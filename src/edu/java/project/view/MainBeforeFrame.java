package edu.java.project.view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.java.project.controller.MemberDaoImpl;
import edu.java.project.model.Member;
import edu.java.project.view.LoginFrame.OnMemberLoginListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class MainBeforeFrame extends JFrame implements OnMemberLoginListener {
    
    private MemberDaoImpl daoMember;

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void showMainBeforeFrame() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainBeforeFrame frame = new MainBeforeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public MainBeforeFrame() {
        
        initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setTitle("LOGIN");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(750, 350, 384, 280);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnLogin = new JButton("로 그 인");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LoginFrame_Practice.newLoginFrame((edu.java.project.view.LoginFrame_Practice.OnMemberLoginListener) MainBeforeFrame.this);
            }
        });
        
        btnLogin.setFont(new Font("D2Coding", Font.BOLD, 18));
        btnLogin.setBounds(22, 82, 323, 43);
        contentPane.add(btnLogin);
        
        JButton btnJoin = new JButton("회 원 가 입");
        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberJoinFrame.newMemberJoinFrame(MainBeforeFrame.this);
            }
        });
        btnJoin.setFont(new Font("D2Coding", Font.BOLD, 15));
        btnJoin.setBounds(22, 135, 155, 43);
        contentPane.add(btnJoin);
        
        JButton btnNotMember = new JButton("비 회 원");
        btnNotMember.setFont(new Font("D2Coding", Font.BOLD, 15));
        btnNotMember.setBounds(190, 137, 155, 43);
        contentPane.add(btnNotMember);
        
        JLabel lblProgramTitle = new JLabel("프로그램 이름 적기");
        lblProgramTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblProgramTitle.setFont(new Font("D2Coding", Font.PLAIN, 12));
        lblProgramTitle.setBounds(120, 29, 131, 43);
        contentPane.add(lblProgramTitle);
        
    }

    @Override
    public void onMemberLogined(String id) {
        // TODO Auto-generated method stub
        
    }
}
