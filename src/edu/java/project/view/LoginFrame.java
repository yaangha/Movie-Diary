package edu.java.project.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import edu.java.project.controller.MemberDaoImpl;
import edu.java.project.model.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class LoginFrame extends JFrame {
    
    public interface OnMemberLoginListener {
        void onMemberLogined(String id);
    }
    
    private MemberDaoImpl daoMember;
    private Component parent;
    private OnMemberLoginListener listenerLogin;

    private JPanel contentPane;
    private JTextField textId;
    private JPasswordField pwField;

    /**
     * Launch the application.
     */
    public static void newLoginFrame(Component parent, OnMemberLoginListener listenerLogin) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame(parent, listenerLogin);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public LoginFrame(Component parent, OnMemberLoginListener listenerLogin) {
        this.listenerLogin = listenerLogin;
        this.parent = parent;
        this.daoMember = MemberDaoImpl.getInstance();
        
        initialize();
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setTitle("LOGIN");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int x = parent.getX();
        int y = parent.getY();
        setBounds(x + 280, y + 260, 384, 280);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblId = new JLabel("ID");
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblId.setBounds(12, 13, 96, 43);
        contentPane.add(lblId);
        
        JLabel lblPW = new JLabel("PW");
        lblPW.setHorizontalAlignment(SwingConstants.CENTER);
        lblPW.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblPW.setBounds(12, 72, 96, 43);
        contentPane.add(lblPW);
        
        textId = new JTextField();
        textId.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textId.setColumns(10);
        textId.setBounds(120, 13, 225, 43);
        contentPane.add(textId);
        
        pwField = new JPasswordField();
        pwField.setFont(new Font("D2Coding", Font.PLAIN, 12));
        pwField.setBounds(120, 69, 225, 43);
        contentPane.add(pwField);
        
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginMember();
            }
        });
        
        btnLogin.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnLogin.setBounds(22, 125, 155, 43);
        contentPane.add(btnLogin);
        
        JButton btnJoin = new JButton("JOIN");
        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberJoinFrame.newMemberJoinFrame(LoginFrame.this);
            }
        });
        btnJoin.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnJoin.setBounds(190, 125, 155, 43);
        contentPane.add(btnJoin);
        
        JButton btnNotMember = new JButton("비회원");
        btnNotMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listenerLogin.onMemberLogined("user");
            }
        });
        btnNotMember.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnNotMember.setBounds(22, 181, 323, 43);
        contentPane.add(btnNotMember);
        
    }

    private void loginMember() {
        String id = textId.getText();
        char[] userPw = pwField.getPassword();
        String pw = new String(userPw);
        
        Member mList = daoMember.select(id);
                                   
        if (mList == null) {
            JOptionPane.showConfirmDialog(this, "존재하는 아이디가 없습니다.");
        } else {
            if (mList.getmPw().equals(pw)) {
                dispose();
                JOptionPane.showMessageDialog(this, "로그인 성공!");
                listenerLogin.onMemberLogined(id);
            } else {
                JOptionPane.showMessageDialog(this, "로그인 실패!");
            }
        }
    }        

}
