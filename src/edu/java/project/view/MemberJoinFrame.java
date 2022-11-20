package edu.java.project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.project.controller.MemberDaoImpl;
import edu.java.project.model.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.JobAttributes;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemberJoinFrame extends JFrame {
    
    private Component parent;
    private MemberDaoImpl daoMember;

    private JPanel contentPane;
    private JTextField textUserName;
    private JTextField textUserId;
    private JTextField textUserEmail;
    private JPasswordField pwField;
    private JPasswordField pwChkField;
    
    private JLabel lblPwChkImg_1;
    private JLabel lblPwChkImg_2;
    private JTextField lblComent;
    private JLabel lblStar_2;
    private JLabel lblStar_3;

    /**
     * Launch the application.
     */
    public static void newMemberJoinFrame(Component parent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemberJoinFrame frame = new MemberJoinFrame(parent);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public MemberJoinFrame(Component parent) {
        this.parent = parent;
        this.daoMember = MemberDaoImpl.getInstance();
        initialize();
    }
    

    /**
     * Create the frame.
     */
    public void initialize() {
        setTitle("회원가입");
        int x = parent.getX();
        int y = parent.getY();
        setBounds(x - 18, y - 28, 420, 336);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblComent = new JTextField("이메일 형식이 올바르지 않습니다!");
        lblComent.setEditable(false);
        lblComent.setHorizontalAlignment(SwingConstants.CENTER);
        lblComent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblComent.setVisible(false); // 클릭하면 사라짐
                textUserEmail.setVisible(true);
            }
        });
        lblComent.setVisible(false); // 이메일 형식이 올바르지 않으면 true 변경
        lblComent.setFont(new Font("D2Coding", Font.PLAIN, 12));
        lblComent.setBounds(104, 205, 210, 35);
        contentPane.add(lblComent);
        
        JLabel lblUserId = new JLabel("ID");
        lblUserId.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserId.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblUserId.setBounds(12, 13, 80, 35);
        contentPane.add(lblUserId);
        
        JLabel lblUserName = new JLabel("NAME");
        lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserName.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblUserName.setBounds(12, 157, 80, 35);
        contentPane.add(lblUserName);
        
        JLabel lblUserPw = new JLabel("PW");
        lblUserPw.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserPw.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblUserPw.setBounds(12, 61, 80, 35);
        contentPane.add(lblUserPw);
        
        JLabel lblUserPwChk = new JLabel("PW(Chk)");
        lblUserPwChk.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserPwChk.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblUserPwChk.setBounds(12, 109, 80, 35);
        contentPane.add(lblUserPwChk);
        
        JLabel lblUserEmail = new JLabel("EMAIL");
        lblUserEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserEmail.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblUserEmail.setBounds(12, 205, 80, 35);
        contentPane.add(lblUserEmail);
        
        textUserName = new JTextField();
        textUserName.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textUserName.setBounds(104, 157, 210, 35);
        contentPane.add(textUserName);
        textUserName.setColumns(10);
        
        textUserId = new JTextField();
        textUserId.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textUserId.setColumns(10);
        textUserId.setBounds(104, 13, 210, 35);
        contentPane.add(textUserId);
        
        textUserEmail = new JTextField();
        textUserEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textUserEmail.getText();
                
                if (email.indexOf("@", 3) == -1) {
                    textUserEmail.setVisible(false);
                    textUserEmail.setText("");
                    lblComent.setVisible(true);
                    return;
                }                
            }
        });
        textUserEmail.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textUserEmail.setColumns(10);
        textUserEmail.setBounds(104, 205, 210, 35);
        contentPane.add(textUserEmail);
        
        JButton btnIdChk = new JButton("CHECK");
        btnIdChk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkUserId();
            }
        });
        btnIdChk.setFont(new Font("D2Coding", Font.PLAIN, 12));
        btnIdChk.setBounds(326, 13, 64, 35);
        contentPane.add(btnIdChk);
        
        JButton btnJoin = new JButton("JOIN");
        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinMember();
            }
        });
        btnJoin.setFont(new Font("D2Coding", Font.BOLD, 21));
        btnJoin.setBounds(12, 250, 378, 35);
        contentPane.add(btnJoin);
        
        pwField = new JPasswordField();
        pwField.setBounds(104, 61, 210, 35);
        contentPane.add(pwField);
        
        pwChkField = new JPasswordField();
        pwChkField.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    checkUserPw();
        	}
        });
        pwChkField.setBounds(104, 109, 210, 35);
        contentPane.add(pwChkField);
        
        lblPwChkImg_1 = new JLabel(chaneImageSize("images/chkIcon.gif"));
        lblPwChkImg_1.setVisible(false);
        lblPwChkImg_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblPwChkImg_1.setBounds(343, 61, 30, 30);
        contentPane.add(lblPwChkImg_1);
        
        lblPwChkImg_2 = new JLabel(chaneImageSize("images/chkIcon.gif"));
        lblPwChkImg_2.setVisible(false);
        lblPwChkImg_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblPwChkImg_2.setBounds(343, 109, 30, 30);
        contentPane.add(lblPwChkImg_2);
        
        JLabel lblStar_1 = new JLabel("*");
        lblStar_1.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblStar_1.setForeground(Color.red);
        lblStar_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblStar_1.setBounds(20, 10, 30, 22);
        contentPane.add(lblStar_1);
        
        lblStar_2 = new JLabel("*");
        lblStar_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblStar_2.setForeground(Color.RED);
        lblStar_2.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblStar_2.setBounds(20, 58, 30, 22);
        contentPane.add(lblStar_2);
        
        lblStar_3 = new JLabel("*");
        lblStar_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblStar_3.setForeground(Color.RED);
        lblStar_3.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblStar_3.setBounds(0, 106, 30, 22);
        contentPane.add(lblStar_3);
    }

    private void joinMember() {
        String id = textUserId.getText();
        char[] userPw = pwField.getPassword();
        String pw = new String(userPw);
        String name = textUserName.getText();
        String email = textUserEmail.getText();
        
        if (id.equals("") || pw.equals("") || name.equals("")) {
            JOptionPane.showMessageDialog(this, "필수항목을 모두 입력해주세요!");
            return;
        }
            
        
        Member member = new Member(id, pw, name, email, null);
        
        int result = daoMember.insert(member);
        
        if (result == 1) {
            JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.");
            dispose();
        }
        
    }

    private void checkUserPw() {
        char[] userPw = pwField.getPassword();
        char[] userPwChk = pwChkField.getPassword();
           
        String pw = new String(userPw); // char[] 배열을 String으로 변환
        String pwChk = new String(userPwChk);
           
        if (pw.equals("") || pwChk.equals("")) {
           JOptionPane.showMessageDialog(MemberJoinFrame.this, "비밀번호칸을 채워주세요.");
           return;
       }
           
       if (pw.equals(pwChk)) {
           lblPwChkImg_1.setVisible(true);
           lblPwChkImg_2.setVisible(true);
           return;
       } 
       
       if (!pw.equals(pwChk)) {
           lblPwChkImg_1.setVisible(false);
           lblPwChkImg_2.setVisible(false);
           JOptionPane.showMessageDialog(MemberJoinFrame.this, "비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
           return;
       }
        
    }

    private void checkUserId() {
        String userId = textUserId.getText();
        
        if (userId.equals("")) {
            JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.");
            return;
        }
        
        if (daoMember.select(userId) != null) {
            JOptionPane.showMessageDialog(this, "중복된 아이디입니다. 다시 입력하세요.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(
                    this, 
                    "사용가능한 아이디입니다. 사용하시겠습니까?", 
                    "아이디 확인", 
                    JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                textUserId.setEnabled(false);
            }
        }
    }
    
    public ImageIcon chaneImageSize(String imgName) {        
        ImageIcon icon = new ImageIcon(imgName);
        Image img = icon.getImage();
        Image changeImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);
        
        return changeIcon; 
    }
}
