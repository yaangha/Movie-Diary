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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;

import edu.java.project.view.MovieCreateFrame.OnMovieCreateListener;
import edu.java.project.view.MovieDetailUpdateFrameAtCreate.OnMvInfoUpdateListenerAtCreate;
import edu.java.project.view.MovieUpdateFrame;

public class MovieDetailUpdateFrameAtCreate extends JFrame {
    // 상세화면에서 추가된 부분 MoviesMain에 알려주기 ㅠㅠ
    public interface OnMvInfoUpdateListenerAtCreate {
        void onMvInfoUpdateListener();
    }

    private static final String BASICIMAGE = "images/basic.png";
    
    private OnMvInfoUpdateListenerAtCreate listenerDetail;
    private Component parent;
    private MovieDaoImpl daoMovie;
    private MvInfoDaoImpl daoMvInfo;
    private String title;
    private String id;
    
    private JPanel contentPane;
    private JTextField textTitle;
    private JTextField textDirector;
    private JTextField textActor_1;
    private JTextField textActor_2;
    private JComboBox CountryComboBox;
    private JComboBox GenreComboBox;
    private JComboBox ScoreComboBox;
    private JLabel lblImage;

    /**
     * Launch the application. Frame 실행 메소드
     */
    public static void newMovieDetailUpdateFrameAtCreate(Component parent, String title, String id, OnMvInfoUpdateListenerAtCreate listenerDetail) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MovieDetailUpdateFrameAtCreate frame = new MovieDetailUpdateFrameAtCreate(parent, title, id, listenerDetail);
                    frame.setVisible(true); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    // 생성자
    public MovieDetailUpdateFrameAtCreate(Component parent, String title, String id, OnMvInfoUpdateListenerAtCreate listenerDetail) {
        this.parent = parent;
        this.title = title;
        this.id =id;
        this.listenerDetail = listenerDetail;
        this.daoMovie = MovieDaoImpl.getInstance();
        this.daoMvInfo = MvInfoDaoImpl.getInstance();
        initialize();
        
        initializeDetailAtCreate();
    }
    
    private void initializeDetailAtCreate() {
        textTitle.setText(title);
                
        MvInfo mvInfo = daoMvInfo.select(title, id);

        if (mvInfo.getDirector() == null) {
            textDirector.setText("");
        } else {
            textDirector.setText(mvInfo.getDirector());
        }
        
        if (mvInfo.getImageName() == null) {
            lblImage.setIcon(new ImageIcon(BASICIMAGE));
            lblImage.setText(BASICIMAGE);
        } else {
            lblImage.setIcon(changeImageSize(mvInfo.getImageName()));
            lblImage.setText(mvInfo.getImageName());
        }
        
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setTitle("영화 정보");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int x = parent.getX();
        int y = parent.getY();
        setBounds(x, y, 945, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("제   목");
        lblTitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(560, 213, 70, 40);
        contentPane.add(lblTitle);
        
        JLabel lblDirector = new JLabel("감   독");
        lblDirector.setHorizontalAlignment(SwingConstants.CENTER);
        lblDirector.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblDirector.setBounds(560, 275, 70, 40);
        contentPane.add(lblDirector);
        
        JLabel lblActor = new JLabel("배 우 1");
        lblActor.setHorizontalAlignment(SwingConstants.CENTER);
        lblActor.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblActor.setBounds(560, 337, 70, 40);
        contentPane.add(lblActor);
        
        JLabel lblGenre = new JLabel("장   르");
        lblGenre.setHorizontalAlignment(SwingConstants.CENTER);
        lblGenre.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblGenre.setBounds(560, 461, 70, 40);
        contentPane.add(lblGenre);
        
        JLabel lblCountry = new JLabel("국   가");
        lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
        lblCountry.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblCountry.setBounds(560, 523, 70, 40);
        contentPane.add(lblCountry);
        
        JLabel lblMyScore = new JLabel("별   점");
        lblMyScore.setHorizontalAlignment(SwingConstants.CENTER);
        lblMyScore.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblMyScore.setBounds(560, 585, 70, 40);
        contentPane.add(lblMyScore);
        
        textTitle = new JTextField();
        textTitle.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textTitle.setBounds(664, 213, 210, 40);
        contentPane.add(textTitle);
        textTitle.setColumns(10);
        
        textDirector = new JTextField();
        textDirector.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textDirector.setColumns(10);
        textDirector.setBounds(664, 275, 210, 40);
        contentPane.add(textDirector);
        
        textActor_1 = new JTextField();
        textActor_1.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textActor_1.setColumns(10);
        textActor_1.setBounds(664, 337, 210, 40);
        contentPane.add(textActor_1);
        
        JPanel GenrePanel = new JPanel();
        GenrePanel.setBounds(695, 461, 149, 40);
        contentPane.add(GenrePanel);
        
        GenreComboBox = new JComboBox();
        String[] genreComboBoxItems = {"공포", "다큐멘터리", "드라마", "로맨스/멜로", "뮤지컬", "미스터리", "스릴러", "액션", "판타지/SF", "코미디"};
        DefaultComboBoxModel<String> genreComboBoxModel = new DefaultComboBoxModel<>(genreComboBoxItems);
        GenreComboBox.setModel(genreComboBoxModel);
        GenreComboBox.setSelectedIndex(0);
        GenreComboBox.setFont(new Font("D2Coding", Font.PLAIN, 12));
        GenrePanel.add(GenreComboBox);
        
        JPanel CountryPanel = new JPanel();
        CountryPanel.setBounds(695, 523, 149, 40);
        contentPane.add(CountryPanel);
        
        CountryComboBox = new JComboBox();
        String[] countryComboBoxItems = {"대만", "대한민국", "미국", "스페인", "영국", "인도", "일본", "프랑스", "홍콩"};
        DefaultComboBoxModel<String> countryComboBoxModel = new DefaultComboBoxModel<>(countryComboBoxItems);
        CountryComboBox.setModel(countryComboBoxModel);
        CountryComboBox.setSelectedIndex(0);
        CountryComboBox.setFont(new Font("D2Coding", Font.PLAIN, 12));
        CountryPanel.add(CountryComboBox);
        
        JButton btnClose = new JButton("닫기");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnClose.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnClose.setBounds(847, 711, 70, 40);
        contentPane.add(btnClose);
        
        JButton btnSave = new JButton("저장");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMovieDetailInfo();
            }
        });
        btnSave.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnSave.setBounds(774, 711, 70, 40);
        contentPane.add(btnSave);
        
        JPanel ScorePanel = new JPanel();
        ScorePanel.setBounds(695, 585, 149, 40);
        contentPane.add(ScorePanel);
        
        ScoreComboBox = new JComboBox();
        String[] scoreComboBoxItems = {"★", "★ ★", "★ ★ ★", "★ ★ ★ ★", "★ ★ ★ ★ ★"};
        DefaultComboBoxModel<String> scoreComboBoxModel = new DefaultComboBoxModel<>(scoreComboBoxItems);
        ScoreComboBox.setModel(scoreComboBoxModel);
        ScoreComboBox.setSelectedIndex(0);
        ScoreComboBox.setFont(new Font("D2Coding", Font.PLAIN, 12));
        ScorePanel.add(ScoreComboBox);
        
        textActor_2 = new JTextField();
        textActor_2.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textActor_2.setColumns(10);
        textActor_2.setBounds(664, 399, 210, 40);
        contentPane.add(textActor_2);
        
        lblImage = new JLabel("");
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setBounds(39, 63, 447, 630);
        contentPane.add(lblImage);
        
        JLabel lblActor_2 = new JLabel("배 우 2");
        lblActor_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblActor_2.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblActor_2.setBounds(560, 399, 70, 40);
        contentPane.add(lblActor_2);
    }

    private void saveMovieDetailInfo() {
        String title = textTitle.getText();
        String director = textDirector.getText();
        String actor_1 = textActor_1.getText();
        String actor_2 = textActor_2.getText();
                
        String genre = null;
        for (int i = 0; i < GenreComboBox.getItemCount(); i++) {
            if (GenreComboBox.getSelectedIndex() == i) {
                genre = GenreComboBox.getItemAt(i).toString();
                break;
            }
        }
        
        String country = null;
        for (int i = 0; i < CountryComboBox.getItemCount(); i++) {
            if (CountryComboBox.getSelectedIndex() == i) {
                country = CountryComboBox.getItemAt(i).toString();
                break;
            }
        }
        
        String myScore = null;
        for (int i = 0; i < ScoreComboBox.getItemCount(); i++) {
            if (ScoreComboBox.getSelectedIndex() == i) {
                myScore = ScoreComboBox.getItemAt(i).toString();
                break;
            }
        }
        
        String imageName = lblImage.getText();
        
        MvInfo mvInfo = new MvInfo(title, director, actor_1, actor_2, genre, country, myScore, imageName, null, id);
        Movie movie = new Movie(null, title, director, null, null, null, imageName, id);
                
        int resultMvInfo = daoMvInfo.update(mvInfo);
        int resultMovie = daoMovie.updateTitleDirector(movie);
        
        System.out.println(resultMovie + "/" + resultMvInfo);
                
        if (resultMvInfo == 1 && resultMovie == 1) {
            dispose();
            JOptionPane.showMessageDialog(parent, "데이터를 저장했습니다.");
            listenerDetail.onMvInfoUpdateListener();
        } else {
            JOptionPane.showMessageDialog(parent, "저장에 실패했습니다.");
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
