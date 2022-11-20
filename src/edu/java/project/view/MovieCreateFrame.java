package edu.java.project.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.java.project.controller.MovieDaoImpl;
import edu.java.project.controller.MvInfoDaoImpl;
import edu.java.project.model.Movie;
import edu.java.project.model.MvInfo;
import edu.java.project.view.MovieDetailUpdateFrameAtCreate.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class MovieCreateFrame extends JFrame {
    
    public interface OnMovieCreateListener {
        void onMovieCreated();
    }
    
    private static final String BASICIMAGE = "images/basic.png";
    
    private MovieDaoImpl daoMovie;
    private MvInfoDaoImpl daoMvInfo;
    private Component parent;
    private OnMovieCreateListener listener;
    private OnMvInfoUpdateListenerAtCreate listenerDetail;
    private String id;

    private JPanel contentPane;
    private JTextField textTitle;
    private JTextField textDirector;
    private JTextField textViewDate;
    private JTextArea textReview;
    private JComboBox comboBox;
    private JLabel lblImage;
    private JLabel lblImageTitle;
    private JTextField textImageAddress;
    
    private String imageName;

    /**
     * Launch the application.
     */
    public static void newMovieCreateFrame(Component parent, OnMovieCreateListener listener, OnMvInfoUpdateListenerAtCreate listenerDetail, String id) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MovieCreateFrame frame = new MovieCreateFrame(parent, listener, listenerDetail, id);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public MovieCreateFrame(Component parent, OnMovieCreateListener listener, OnMvInfoUpdateListenerAtCreate listenerDetail, String id) {
        this.parent = parent;
        this.listener = listener;
        this.listenerDetail = listenerDetail;
        this.id = id;
        this.daoMovie = MovieDaoImpl.getInstance();
        this.daoMvInfo = MvInfoDaoImpl.getInstance();
        initialize();
        
        lblImage.setIcon(new ImageIcon(BASICIMAGE));
        
        JLabel lblReview = new JLabel("리뷰를 남겨보세요.");
        lblReview.setHorizontalAlignment(SwingConstants.LEFT);
        lblReview.setFont(new Font("D2Coding", Font.PLAIN, 12));
        lblReview.setBounds(511, 355, 120, 31);
        contentPane.add(lblReview);

    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setTitle("영화 추가");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int x = parent.getX();
        int y = parent.getY();
        setBounds(x, y, 945, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("제   목");
        lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(543, 135, 90, 40);
        contentPane.add(lblTitle);
        
        JLabel lblViewDate = new JLabel("관 람 일");
        lblViewDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblViewDate.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblViewDate.setBounds(543, 295, 90, 40);
        contentPane.add(lblViewDate);
        
        JLabel lblDirector = new JLabel("감   독");
        lblDirector.setHorizontalAlignment(SwingConstants.CENTER);
        lblDirector.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblDirector.setBounds(543, 215, 90, 40);
        contentPane.add(lblDirector);
        
        textTitle = new JTextField();
        textTitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textTitle.setBounds(679, 135, 210, 40);
        contentPane.add(textTitle);
        textTitle.setColumns(10);
        
        textDirector = new JTextField();
        textDirector.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textDirector.setColumns(10);
        textDirector.setBounds(679, 215, 210, 40);
        contentPane.add(textDirector);
        
        textViewDate = new JTextField();
        textViewDate.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textViewDate.setColumns(10);
        textViewDate.setBounds(679, 295, 210, 40);
        contentPane.add(textViewDate);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(511, 387, 406, 317);
        contentPane.add(scrollPane);
        
        textReview = new JTextArea();
        textReview.setLineWrap(true);
        textReview.setFont(new Font("D2Coding", Font.PLAIN, 12));
        scrollPane.setViewportView(textReview);
        
        JButton btnCreate = new JButton("추 가");
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMovie();
            }
        });
        btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 13));
        btnCreate.setBounds(773, 720, 144, 31);
        contentPane.add(btnCreate);
        
        lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setBounds(12, 30, 450, 630);
        contentPane.add(lblImage);
        
        JLabel lblStatus = new JLabel("구   분");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblStatus.setBounds(543, 55, 90, 40);
        contentPane.add(lblStatus);
        
        JPanel StatusPanel = new JPanel();
        StatusPanel.setBounds(688, 55, 193, 40);
        contentPane.add(StatusPanel);
        
        comboBox = new JComboBox();
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int type = comboBox.getSelectedIndex();

                if (type == 1) {
                    textViewDate.setEditable(false);
                    //TODO
                    //textViewDate.setText("관람 후 업데이트");
                }

                if (type == 0 || type == 2) {
                    textViewDate.setEditable(true);
                    textViewDate.setText("");
                }
            }
        });
        String[] comboBoxItems = {"보고왔어요", "보고싶어요", "다시 볼래요"};
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(comboBoxItems);
        comboBox.setModel(comboBoxModel);
        comboBox.setSelectedIndex(0);
        comboBox.setFont(new Font("D2Coding", Font.PLAIN, 15));
        StatusPanel.add(comboBox);
        
        lblImageTitle = new JLabel("이미지를 추가하세요!");
        lblImageTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblImageTitle.setFont(new Font("D2Coding", Font.PLAIN, 12));
        lblImageTitle.setBounds(155, 657, 165, 31);
        contentPane.add(lblImageTitle);
        
        JButton btnDetail = new JButton("상세내역");
        btnDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textTitle.getText().equals("")) {
                    JOptionPane.showMessageDialog(MovieCreateFrame.this, "영화 제목을 먼저 입력해주세요.");
                } else {
                    createMovieAtDetail();
                    dispose();
                    MovieDetailUpdateFrameAtCreate.newMovieDetailUpdateFrameAtCreate(MovieCreateFrame.this, textTitle.getText(), id, listenerDetail);
                }
            }
        });
        btnDetail.setFont(new Font("D2Coding", Font.PLAIN, 13));
        btnDetail.setBounds(623, 720, 144, 31);
        contentPane.add(btnDetail);
        
        textImageAddress = new JTextField();
        textImageAddress.setFont(new Font("D2Coding", Font.PLAIN, 12));
        textImageAddress.setColumns(10);
        textImageAddress.setBounds(67, 695, 199, 26);
        contentPane.add(textImageAddress);
        
        JButton btnImageInsert = new JButton("입력");
        btnImageInsert.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		imageName = textImageAddress.getText();
        		
        		if (imageName.equals("")) {
        			JOptionPane.showMessageDialog(MovieCreateFrame.this, "이미지 주소를 입력하세요!");
        			return;
        		}
        		
        		lblImage.setIcon(new ImageIcon(imageName));
        	}
        });
        btnImageInsert.setFont(new Font("D2Coding", Font.PLAIN, 10));
        btnImageInsert.setBounds(268, 693, 69, 31);
        contentPane.add(btnImageInsert);
        
        JButton btnImageDelete = new JButton("지우기");
        btnImageDelete.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		lblImage.setIcon(new ImageIcon(BASICIMAGE));
        	}
        });
        btnImageDelete.setFont(new Font("D2Coding", Font.PLAIN, 10));
        btnImageDelete.setBounds(340, 693, 69, 31);
        contentPane.add(btnImageDelete);
    }
    
    private void createMovieAtDetail() { // 상세내역 눌렀을 때 저장됨
        String title = textTitle.getText();
        
        if (title.equals("")) {
            JOptionPane.showMessageDialog(this, "제목을 입력하세요.");
            return;
        }
        
        String director = textDirector.getText();
        String viewDate = textViewDate.getText(); 
        String review = textReview.getText();
        String status = null;
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getSelectedIndex() == i) {
                status = comboBox.getItemAt(i).toString();
                break;
            }
        }
        String imageName = textImageAddress.getText();
        
        if (imageName == null) {
            imageName = BASICIMAGE;
        } else {
            imageName = textImageAddress.getText();
        }
        
        Integer no = daoMovie.count(id) + 1;
                
        Movie movie = new Movie(no, title, director, viewDate, review, status, imageName, id);
        MvInfo mvInfo = new MvInfo(title, director, null, null, null, null, null, imageName, no, id);
                
        int resultMvInfo = daoMvInfo.inserTitleDirector(mvInfo);    
        int resultMovie = daoMovie.insert(movie);
    }

	private void createMovie() {
        String title = textTitle.getText();
        
        if (title.equals("")) {
            JOptionPane.showMessageDialog(this, "제목을 입력하세요.");
            return;
        }
        
        String director = textDirector.getText();
        String viewDate = textViewDate.getText(); 
        String review = textReview.getText();
        String status = null;
        for (int i = 0; i < comboBox.getItemCount(); i++) {
        	if (comboBox.getSelectedIndex() == i) {
        		status = comboBox.getItemAt(i).toString();
        		break;
        	}
        }
        String imageName = textImageAddress.getText();
        
        if (imageName == null) {
        	imageName = BASICIMAGE;
        } else {
        	imageName = textImageAddress.getText();
        }
        
        Integer no = daoMovie.count(id) + 1;
                
        Movie movie = new Movie(no, title, director, viewDate, review, status, imageName, id);
        MvInfo mvInfo = new MvInfo(title, director, null, null, null, null, null, imageName, no, id);
                
        int resultMvInfo = daoMvInfo.inserTitleDirector(mvInfo);    
        int resultMovie = daoMovie.insert(movie);
        
        if (resultMovie == 1 && resultMvInfo == 1) {
            dispose();
            JOptionPane.showMessageDialog(parent, title + " 업데이트 성공!");
            listener.onMovieCreated();
        } else {
            dispose();
            JOptionPane.showMessageDialog(parent, title + " 업데이트 실패!");
        }
        
    }
	
    // 이미지 사이즈 변환 메소드
    public ImageIcon changeImageSize(String imgName) {        
        ImageIcon icon = new ImageIcon(imgName);
        Image img = icon.getImage();
        Image changeImg = img.getScaledInstance(450, 630, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);
        
        return changeIcon; 
    }

}
