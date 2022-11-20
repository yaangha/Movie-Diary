package edu.java.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import edu.java.project.controller.MovieDaoImpl;
import edu.java.project.controller.MvInfoDaoImpl;
import edu.java.project.model.Movie;
import edu.java.project.model.MvInfo;
import edu.java.project.view.LoginFrame.OnMemberLoginListener;
import edu.java.project.view.MovieCreateFrame.OnMovieCreateListener;
import edu.java.project.view.MovieDetailUpdateFrameAtCreate.OnMvInfoUpdateListenerAtCreate;
import edu.java.project.view.MovieDetailUpdateFrameAtUpdate.OnMvInfoUpdateListenerAtUpdate;
import edu.java.project.view.MovieUpdateFrame.OnMovieUpdateListener;

import static edu.java.project.model.Movie.Entity.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoviesMain_now implements OnMemberLoginListener, OnMovieUpdateListener, OnMovieCreateListener, OnMvInfoUpdateListenerAtCreate, OnMvInfoUpdateListenerAtUpdate {
    private static final String[] COL_NAMES = {
            COL_TITLE, COL_DIRECTOR, COL_STATUS, COL_NO
    };

    private MovieDaoImpl daoMovie;
    private MvInfoDaoImpl daoMvInfo;
    
    private String id;
    private int index; // poster 넣을 때 사용할 인덱스
    
    private JFrame frame;
    // private JTable table;
    // private DefaultTableModel model;
    // private JComboBox comboBox;
    private JLabel lblSum;
    private JLabel lblUserId;
    private JButton btnLogOut;
    // private JButton btnLogin;
    // private JTextField textSearch;
    private JLabel lblMvTitle_set;
    private JLabel lblMvDirector_set;
    private JLabel lblMvGenre_set;
    private JLabel lblMvCountry_set;
    private JLabel lblMvViewDate_set;
    private JLabel lblMoviePoster;
    private JLabel lblComent;
    private JLabel lblBigFrame;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MoviesMain_now window = new MoviesMain_now();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    
    public MoviesMain_now() {
        daoMovie = MovieDaoImpl.getInstance();
        daoMvInfo = MvInfoDaoImpl.getInstance();
        
        // MainBeforeFrame.showMainBeforeFrame();
        
        initialize();
        
        LoginFrame.newLoginFrame(frame, MoviesMain_now.this);     
        
    }
    
    // 로그인 후 -> id 값 받아서 일치하는 데이터만 메인 화면에 세팅
    private void initializeInfo(String id) { 
        frame.setVisible(true);
        lblBigFrame.setVisible(false);
        lblUserId.setText(id);
        btnLogOut.setVisible(true);
        
        int countAll = daoMovie.count(id);
        int countWant = daoMovie.count(id, "보고싶어요");
        lblSum.setText("[지금까지 " + (countAll - countWant) + "편의 영화를 봤어요!]"); // TODO: 숫자만 따로 키우기
        
        // 영화 정보 기본 세팅
        List<Movie> list = daoMovie.select(id); // 전체 영화
        List<Movie> listStatus = daoMovie.selectWish(id); // 보고싶어요 제외 리스트
                
        if (listStatus.size() == 0) {
            lblMoviePoster.setIcon(new ImageIcon("images/basic.png"));
            lblMvTitle_set.setText("");
            lblMvDirector_set.setText("");
            lblMvGenre_set.setText("");
            lblMvCountry_set.setText("");
            lblMvViewDate_set.setText("");
            // return; 리턴 필요?
        } else {
            MvInfo listInfo = daoMvInfo.select(listStatus.get(0).getTitle(), id);
            lblMoviePoster.setIcon(changeImageSize(listInfo.getImageName()));
            lblMvTitle_set.setText(listInfo.getTitle());
            lblMvDirector_set.setText(listInfo.getDirector());
            lblMvGenre_set.setText(listInfo.getGenre());
            lblMvCountry_set.setText(listInfo.getCountry());
            lblComent.setText("님이 최근에 본 영화 [" + listInfo.getTitle() + "]");
            
            // date 형식 변환
            String dateOracle = listStatus.get(0).getViewDate();
            if (dateOracle == null) {
                lblMvViewDate_set.setText("");
            } else {
                String[] dateArray = dateOracle.split(" ");
                String dateYMD = dateArray[0];
                String[] dateYMDArray = dateYMD.split("-");
                lblMvViewDate_set.setText(dateYMDArray[0] + "년 " + dateYMDArray[1] + "월 " + dateYMDArray[2] + "일");
            }
 
        }
        
    }

    /**
     * Initialize the contents of the frame. 
     */
    private void initialize() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.getContentPane().setFont(new Font("D2Coding", Font.PLAIN, 12));
        frame.setTitle("MOVIES");

        frame.setBounds(500, 180, 945, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JButton btnBox = new JButton("서랍");
        btnBox.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		MovieLibraryFrame.openLibrary(frame, lblUserId.getText());
        	}
        });
        
        lblBigFrame = new JLabel("");
        lblBigFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(lblBigFrame, "로그인 또는 비회원(체험판)으로 접속해주세요.");
                frame.setVisible(false);
            }
        });
        lblBigFrame.setEnabled(false);
        lblBigFrame.setBounds(0, 0, 929, 761);
        frame.getContentPane().add(lblBigFrame);
        
        lblComent = new JLabel("님! 최근에 본 영화가 없어요 :(");
        lblComent.setHorizontalAlignment(SwingConstants.LEFT);
        lblComent.setFont(new Font("D2Coding", Font.BOLD, 12));
        lblComent.setBounds(197, 715, 266, 36);
        frame.getContentPane().add(lblComent);
        btnBox.setFont(new Font("D2Coding", Font.BOLD, 12));
        btnBox.setBounds(762, 667, 155, 28);
        frame.getContentPane().add(btnBox);
        
        JButton btnCreate = new JButton("추가");
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieCreateFrame.newMovieCreateFrame(frame, MoviesMain_now.this, MoviesMain_now.this, lblUserId.getText());
            }
        });
        btnCreate.setFont(new Font("D2Coding", Font.BOLD, 12));
        btnCreate.setBounds(762, 705, 155, 28);
        frame.getContentPane().add(btnCreate);
        
        lblSum = new JLabel("");
        lblSum.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblSum.setHorizontalAlignment(SwingConstants.LEFT);
        lblSum.setBounds(612, 252, 260, 36);
        frame.getContentPane().add(lblSum);
        
        lblUserId = new JLabel("[비회원]");
        lblUserId.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUserId.setFont(new Font("D2Coding", Font.BOLD, 12));
        lblUserId.setBounds(102, 715, 88, 36);
        frame.getContentPane().add(lblUserId);
        
        JButton btnMain = new JButton("HOME");
        btnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeInfo(lblUserId.getText());
            }
        });
        btnMain.setFont(new Font("D2Coding", Font.BOLD, 15));
        btnMain.setBounds(6, 6, 71, 36);
        frame.getContentPane().add(btnMain);
        
        JLabel lblTitle = new JLabel("MOVIE DIARY");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("D2Coding", Font.BOLD, 36));
        lblTitle.setBounds(289, 13, 353, 74);
        frame.getContentPane().add(lblTitle);
                
        btnLogOut = new JButton("LOGOUT");
        btnLogOut.setVisible(false);
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "로그아웃하시겠습니까?", "LOGOUT", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    LoginFrame.newLoginFrame(frame, MoviesMain_now.this);                    
                }
            }
        });
        btnLogOut.setFont(new Font("D2Coding", Font.PLAIN, 12));
        btnLogOut.setBounds(846, 13, 71, 23);
        frame.getContentPane().add(btnLogOut);
        
        lblMoviePoster = new JLabel(new ImageIcon("images/basic.png"));
        lblMoviePoster.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoviePoster.setFont(new Font("D2Coding", Font.PLAIN, 12));
        lblMoviePoster.setBounds(40, 90, 450, 630);
        frame.getContentPane().add(lblMoviePoster);
        
        JLabel lblRight = new JLabel(">");
        lblRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Movie> list = daoMovie.select(id);
                List<Movie> listStatus = daoMovie.selectWish(id); // 보고싶어요 제외 리스트
                
                if (index < listStatus.size() - 1) {
                    index++;
                } else {
                    index = 0;
                }
                
                if (listStatus.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "다음 영화가 존재하지 않습니다!");
                    return;
                } else {
                    MvInfo listInfo = daoMvInfo.select(listStatus.get(index).getTitle(), id);
    
                    lblMoviePoster.setIcon(new ImageIcon(listInfo.getImageName()));
                    lblMvTitle_set.setText(listInfo.getTitle());
                    lblMvDirector_set.setText(listInfo.getDirector());
                    lblMvGenre_set.setText(listInfo.getGenre());
                    lblMvCountry_set.setText(listInfo.getCountry());
                    lblComent.setText("님이 최근에 본 영화 [" + listInfo.getTitle() + "]");
                    
                    String dateOracle = listStatus.get(index).getViewDate();
                    if (dateOracle == null) {
                        lblMvViewDate_set.setText("");
                    } else {
                        String[] dateArray = dateOracle.split(" ");
                        String dateYMD = dateArray[0];
                        String[] dateYMDArray = dateYMD.split("-");
                        lblMvViewDate_set.setText(dateYMDArray[0] + "년 " + dateYMDArray[1] + "월 " + dateYMDArray[2] + "일");
                    }
                } 
            }
        });
        lblRight.setHorizontalAlignment(SwingConstants.CENTER);
        lblRight.setFont(new Font("D2Coding", Font.BOLD, 21));
        lblRight.setBounds(495, 394, 30, 28);
        frame.getContentPane().add(lblRight);
        
        JLabel lblLeft = new JLabel("<");
        lblLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Movie> list = daoMovie.select(id);
                List<Movie> listStatus = daoMovie.selectWish(id); // 보고싶어요 제외 리스트

                if (index > 0) {
                    index--;
                } else {
                    index = listStatus.size() - 1;
                }

                if (listStatus.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "이전 영화가 존재하지 않습니다!");
                    return;
                } else {
                    MvInfo listInfo = daoMvInfo.select(listStatus.get(index).getTitle(), id);
                    lblMoviePoster.setIcon(new ImageIcon(listInfo.getImageName()));
                    lblMvTitle_set.setText(listInfo.getTitle());
                    lblMvDirector_set.setText(listInfo.getDirector());
                    lblMvGenre_set.setText(listInfo.getGenre());
                    lblMvCountry_set.setText(listInfo.getCountry());
                    lblComent.setText("님이 최근에 본 영화 [" + listInfo.getTitle() + "]");
                    
                    String dateOracle = listStatus.get(index).getViewDate();
                    
                    if (dateOracle == null) {
                        lblMvViewDate_set.setText("");
                    } else {
                        String[] dateArray = dateOracle.split(" ");
                        String dateYMD = dateArray[0];
                        String[] dateYMDArray = dateYMD.split("-");
                        lblMvViewDate_set.setText(dateYMDArray[0] + "년 " + dateYMDArray[1] + "월 " + dateYMDArray[2] + "일");
                    }
                }
            }
        });
        lblLeft.setHorizontalAlignment(SwingConstants.CENTER);
        lblLeft.setFont(new Font("D2Coding", Font.BOLD, 21));
        lblLeft.setBounds(5, 394, 30, 28);
        frame.getContentPane().add(lblLeft);
        
        JLabel lblMvTitle = new JLabel("제    목");
        lblMvTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblMvTitle.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblMvTitle.setBounds(550, 298, 78, 36);
        frame.getContentPane().add(lblMvTitle);
        
        JLabel lblMvDirector = new JLabel("감    독");
        lblMvDirector.setHorizontalAlignment(SwingConstants.CENTER);
        lblMvDirector.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblMvDirector.setBounds(550, 344, 78, 36);
        frame.getContentPane().add(lblMvDirector);
        
        JLabel lblMvGenre = new JLabel("장    르");
        lblMvGenre.setHorizontalAlignment(SwingConstants.CENTER);
        lblMvGenre.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblMvGenre.setBounds(550, 390, 78, 36);
        frame.getContentPane().add(lblMvGenre);
        
        JLabel lblMvCountry = new JLabel("국    가");
        lblMvCountry.setHorizontalAlignment(SwingConstants.CENTER);
        lblMvCountry.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblMvCountry.setBounds(550, 436, 78, 36);
        frame.getContentPane().add(lblMvCountry);
        
        lblMvTitle_set = new JLabel("");
        lblMvTitle_set.setHorizontalAlignment(SwingConstants.LEFT);
        lblMvTitle_set.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblMvTitle_set.setBounds(657, 297, 215, 36);
        frame.getContentPane().add(lblMvTitle_set);
        
        lblMvDirector_set = new JLabel("");
        lblMvDirector_set.setHorizontalAlignment(SwingConstants.LEFT);
        lblMvDirector_set.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblMvDirector_set.setBounds(657, 343, 215, 36);
        frame.getContentPane().add(lblMvDirector_set);
        
        lblMvGenre_set = new JLabel("");
        lblMvGenre_set.setHorizontalAlignment(SwingConstants.LEFT);
        lblMvGenre_set.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblMvGenre_set.setBounds(657, 389, 215, 36);
        frame.getContentPane().add(lblMvGenre_set);
        
        lblMvCountry_set = new JLabel("");
        lblMvCountry_set.setHorizontalAlignment(SwingConstants.LEFT);
        lblMvCountry_set.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblMvCountry_set.setBounds(657, 436, 215, 36);
        frame.getContentPane().add(lblMvCountry_set);
        
        JLabel lblMvViewDate = new JLabel("관 람 일");
        lblMvViewDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblMvViewDate.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblMvViewDate.setBounds(550, 482, 78, 36);
        frame.getContentPane().add(lblMvViewDate);
        
        lblMvViewDate_set = new JLabel("");
        lblMvViewDate_set.setHorizontalAlignment(SwingConstants.LEFT);
        lblMvViewDate_set.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblMvViewDate_set.setBounds(657, 482, 215, 36);
        frame.getContentPane().add(lblMvViewDate_set);
        
        // TODO : 필요 없을 시 삭제
        /*
        JLabel lblUserId_1 = new JLabel("님!");
        lblUserId_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblUserId_1.setFont(new Font("D2Coding", Font.BOLD, 12));
        lblUserId_1.setBounds(215, 715, 30, 36);
        frame.getContentPane().add(lblUserId_1);
        */
        
        JButton btnViewDetail = new JButton("상세보기");
        btnViewDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                if (lblMvTitle_set.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "등록된 영화가 없습니다.");
                    return;
                }
                
                MovieDetailUpdateFrameAtUpdate.newMovieDetailUpdateFrameAtUpdate(frame, lblMvTitle_set.getText(), lblUserId.getText(), MoviesMain_now.this);
            }
        });
        btnViewDetail.setFont(new Font("D2Coding", Font.PLAIN, 10));
        btnViewDetail.setBounds(550, 528, 78, 28);
        frame.getContentPane().add(btnViewDetail);

    }
    
    // 이미지 사이즈 변환 메소드
    public ImageIcon changeImageSize(String imgName) {        
        ImageIcon icon = new ImageIcon(imgName);
        Image img = icon.getImage();
        Image changeImg = img.getScaledInstance(450, 630, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);
        
        return changeIcon; 
    }

    /**
     * 일부 키워드 검색 후 일치하는 행의 데이터 노출시키는 메서드
     */
    // TODO: 필요 없을 시 삭제
    /*
    private void searchSelectKeyword() {
        String textKeyword = textSearch.getText();
        
        if (textKeyword.equals("")) {
            JOptionPane.showMessageDialog(frame, "검색할 키워드를 입력하세요.");
            return;
        }
            
        model = new DefaultTableModel(null, COL_NAMES);
        table.setModel(model);

        List<Movie> list = daoMovie.selectKeyword(id, textKeyword);

        if (list.isEmpty()) {
        	JOptionPane.showMessageDialog(frame, "일치하는 데이터가 없습니다.");
        	initializeInfo(id);;
        } else {
            for (Movie m : list) {
                Object[] row = {m.getTitle(), m.getDirector(), m.getStatus(), m.getNo()};
                model.addRow(row);
            }
            textSearch.setText("");
        }
    }
    */
    
    
    /**
     * 영화 상세 정보 보여주기 + 데이터 수정
     */
    // TODO: 필요 없을 시 삭제
    /*
    private void detailMovie() {
        int row = table.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(frame, "확인하려는 영화를 선택하세요.");
            return;
        }
        
        Integer no = (Integer) model.getValueAt(row, 3);
        
        MovieUpdateFrame.updateMovieFrame(frame, no, lblUserId.getText(), MoviesMain_now.this);        
    }
    */

    @Override
    public void OnMovieUpdated() {
        initializeInfo(lblUserId.getText());
    }

    @Override
    public void onMovieCreated() {
       initializeInfo(lblUserId.getText());
    }

    @Override
    public void onMvInfoUpdateListener() {
        initializeInfo(lblUserId.getText());
    }

    @Override
    public void onMemberLogined(String id) {
        this.id = id;
        frame.dispose();
        initialize();
        initializeInfo(id);
    }

    @Override
    public void onMvInfoUpdated() {
        initializeInfo(lblUserId.getText());        
    }
}

