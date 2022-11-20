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
import edu.java.project.view.MovieDetailUpdateFrameAtUpdate.OnMvInfoUpdateListenerAtUpdate;

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

public class MovieUpdateFrame extends JFrame implements OnMvInfoUpdateListenerAtUpdate {
    
    public interface OnMovieUpdateListener {
        void OnMovieUpdated();
    }
    
    private static final String BASICIMAGE = "images/basic.png";
    
    private Component parent;
    private Integer no;
    private String id;
    private MovieDaoImpl daoMovie;
    private MvInfoDaoImpl daoMvInfo;
    private OnMovieUpdateListener listenerUpdate;
    private OnMvInfoUpdateListenerAtUpdate listenerDetail;

    
    private String imageName;
    private JComboBox comboBox;

    private JPanel contentPane;
    private JTextField textTitle;
    private JTextField textDirector;
    private JTextField textViewDate;
    private JTextArea textReview;
    private JLabel lblStatus;
    private JLabel lblImageTitle;
    private JLabel lblImage;
    private JTextField textImageAddress;
    
    /**
     * Launch the application.
     */
    public static void updateMovieFrame(Component parent, Integer no, String id, OnMovieUpdateListener listener, OnMvInfoUpdateListenerAtUpdate listenerDetail) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MovieUpdateFrame frame = new MovieUpdateFrame(parent, no, id, listener, listenerDetail);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public MovieUpdateFrame(Component parent, Integer no, String id, OnMovieUpdateListener listener, OnMvInfoUpdateListenerAtUpdate listenerDetail) {
        this.parent = parent;
        this.no = no;
        this.id = id;
        this.listenerUpdate = listener;
        this.listenerDetail = listenerDetail;
        this.daoMovie = MovieDaoImpl.getInstance();
        this.daoMvInfo = MvInfoDaoImpl.getInstance();
        initialize();
        
        // 해당 인덱스 데이터 채우기
	    Movie movie = daoMovie.select(no, id);
        textTitle.setText(movie.getTitle());
        textDirector.setText(movie.getDirector());
        // textViewDate.setText(movie.getViewDate());
        String dateOracle = movie.getViewDate();
        if (dateOracle == null) {
            textViewDate.setText("");
        } else {
            String[] dateArray = dateOracle.split(" ");
            String dateYMD = dateArray[0];
            String[] dateYMDArray = dateYMD.split("-");
            textViewDate.setText(dateYMDArray[0] + dateYMDArray[1] + dateYMDArray[2]);
        }
        textReview.setText(movie.getReveiw());
        lblStatus.setText("<" + movie.getStatus() + ">");
        
        if (movie.getImageName() == null) {
            lblImage.setIcon(new ImageIcon(BASICIMAGE));
        } else {
            lblImage.setIcon(new ImageIcon(movie.getImageName()));
            lblImage.setText(movie.getImageName());
        }
        
        lblImageTitle.setText("<" + movie.getTitle() + "> 이미지");
        
    }

    /**
     * Create the frame.
     */
    public void initialize() {
        setTitle("상세보기");
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
        
        JLabel label = new JLabel("New label");
        label.setBounds(137, 322, 122, -37);
        contentPane.add(label);
        
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
        textViewDate.setEditable(true);
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
        
        textImageAddress = new JTextField();
        textImageAddress.setText((String) null);
        textImageAddress.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textImageAddress.setEditable(true);
        textImageAddress.setColumns(10);
        textImageAddress.setBounds(85, 667, 199, 26);
        contentPane.add(textImageAddress);
        
        JButton btnUpdate = new JButton("업데이트");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMovie();
            }
        });
        btnUpdate.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnUpdate.setBounds(602, 399, 100, 31);
        contentPane.add(btnUpdate);
        
        JButton btnDelete = new JButton("삭제");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMovie();
            }
        });
        btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnDelete.setBounds(817, 720, 100, 31);
        contentPane.add(btnDelete);
        
        JButton btnDetail = new JButton("영화상세");
        btnDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String title = textTitle.getText();
                String director = textDirector.getText();
                String viewDate = textViewDate.getText();
                String review = textReview.getText();
                if (textImageAddress.getText().equals("")) {
                    imageName = lblImage.getText();
                } else {
                    imageName = textImageAddress.getText();
                }
                
                String status = null;
                for (int i = 0; i < comboBox.getItemCount(); i++) {
                    if (comboBox.getSelectedIndex() == i) {
                        status = comboBox.getItemAt(i).toString();
                        break;
                    }
                }

                Movie movie = new Movie(no, title, director, viewDate, review, status, imageName, id);
                MvInfo mvInfo = new MvInfo(title, director, null, null, null, null, null, imageName, no, id);
                              
                if (title.equals("")) {
                    JOptionPane.showMessageDialog(MovieUpdateFrame.this, "영화 제목을 입력하세요!");
                    return;
                }                 
                
                int resultMvInfo = daoMvInfo.updateTitleDirector(mvInfo);    
                int resultMovie = daoMovie.update(movie);
                
                dispose();
                
                MovieDetailUpdateFrameAtUpdate.newMovieDetailUpdateFrameAtUpdate(MovieUpdateFrame.this, textTitle.getText(), id, listenerDetail);
            }
        });
        btnDetail.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnDetail.setBounds(599, 720, 100, 31);
        contentPane.add(btnDetail);

        JButton btnSave = new JButton("저장");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String title = textTitle.getText();
                String director = textDirector.getText();
                String viewDate = textViewDate.getText();
                String review = textReview.getText();
                if (textImageAddress.getText().equals("")) {
                    imageName = lblImage.getText();
                } else {
                    imageName = textImageAddress.getText();
                }
                
                String status = null;
                for (int i = 0; i < comboBox.getItemCount(); i++) {
                    if (comboBox.getSelectedIndex() == i) {
                        status = comboBox.getItemAt(i).toString();
                        break;
                    }
                }

                Movie movie = new Movie(no, title, director, viewDate, review, status, imageName, id);
                MvInfo mvInfo = new MvInfo(title, director, null, null, null, null, null, imageName, no, id);
                              
                if (title.equals("")) {
                    JOptionPane.showMessageDialog(MovieUpdateFrame.this, "영화 제목을 입력하세요!");
                    return;
                }                 

                int resultMvInfo = daoMvInfo.updateTitleDirector(mvInfo);    
                int resultMovie = daoMovie.update(movie);
                
                if (resultMvInfo == 1 && resultMovie == 1) {
                    JOptionPane.showMessageDialog(MovieUpdateFrame.this, "수정된 내용을 저장했습니다.");
                    listenerUpdate.OnMovieUpdated();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(MovieUpdateFrame.this, "업데이트 실패하였습니다.");
                }
                
            }
        });
        btnSave.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnSave.setBounds(708, 720, 100, 31);
        contentPane.add(btnSave);
        
        JButton btnImageInsert = new JButton("입력");
        btnImageInsert.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String imageName = textImageAddress.getText();
        		if (imageName.equals("")) {
        			JOptionPane.showMessageDialog(MovieUpdateFrame.this, "이미지 주소를 입력하세요!");
        		}
        		lblImage.setIcon(changeImageSize(imageName));
        	}
        });
        btnImageInsert.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnImageInsert.setBounds(285, 665, 69, 31);
        contentPane.add(btnImageInsert);
        
        JButton btnImageDelete = new JButton("지우기");
        btnImageDelete.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		lblImage.setIcon(new ImageIcon(BASICIMAGE)); 
        		textImageAddress.setText(BASICIMAGE);
        	}
        });
        btnImageDelete.setFont(new Font("D2Coding", Font.PLAIN, 10));
        btnImageDelete.setBounds(354, 665, 69, 31);
        contentPane.add(btnImageDelete);
        
        lblImage = new JLabel("");
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setBounds(52, 73, 395, 580);
        contentPane.add(lblImage);
        
        lblStatus = new JLabel();                
        lblStatus.setFont(new Font("D2Coding", Font.PLAIN, 12));
        lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
        lblStatus.setBounds(734, 20, 155, 31);
        contentPane.add(lblStatus);
        
        lblImageTitle = new JLabel("");
        lblImageTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblImageTitle.setFont(new Font("D2Coding", Font.PLAIN, 12));
        lblImageTitle.setBounds(114, 308, 171, 31);
        contentPane.add(lblImageTitle);
        

        JPanel StatusPanel = new JPanel();
        StatusPanel.setBounds(688, 55, 193, 40);
        contentPane.add(StatusPanel);
        
        comboBox = new JComboBox();
        String[] comboBoxItems = {"보고왔어요", "다시 볼래요"};
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(comboBoxItems);
        comboBox.setModel(comboBoxModel);
        comboBox.setSelectedIndex(0);
        comboBox.setFont(new Font("D2Coding", Font.PLAIN, 15));
        StatusPanel.add(comboBox);
        
        JLabel lblStatus = new JLabel("구   분");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblStatus.setBounds(543, 55, 90, 40);
        contentPane.add(lblStatus);
        
        JLabel lblReview = new JLabel("리뷰를 남겨보세요.");
        lblReview.setHorizontalAlignment(SwingConstants.LEFT);
        lblReview.setFont(new Font("D2Coding", Font.PLAIN, 12));
        lblReview.setBounds(511, 355, 120, 31);
        contentPane.add(lblReview);
    }

    /**
     * updateFrame에 입력한 데이터를 MOVIE 테이블 & MV_INFO 테이블에 동시 저장
     */
    private void updateMovie() {
        String title = textTitle.getText();
        String director = textDirector.getText();
        String viewDate = textViewDate.getText();
        String review = textReview.getText();
        if (textImageAddress.getText().equals("")) {
        	imageName = lblImage.getText();
        } else {
            imageName = textImageAddress.getText();
        }
        
        String status = null;
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getSelectedIndex() == i) {
                status = comboBox.getItemAt(i).toString();
                break;
            }
        }

        Movie movie = new Movie(no, title, director, viewDate, review, status, imageName, id);
        MvInfo mvInfo = new MvInfo(title, director, null, null, null, null, null, imageName, no, id);
                      
        if (title.equals("")) {
        	JOptionPane.showMessageDialog(this, "영화 제목을 입력하세요!");
        } else {
	        int confirm = JOptionPane.showConfirmDialog(this, title + " 정보를 업데이트할까요?", "업데이트", JOptionPane.YES_NO_OPTION);
	        
	        if (confirm == JOptionPane.YES_OPTION) {
	            int resultMovie = daoMovie.update(movie);
	            int resultMvInfo = daoMvInfo.updateTitleDirector(mvInfo);
	            
	            if (resultMovie == 1 && resultMvInfo == 1) {
	                dispose();
	                JOptionPane.showMessageDialog(parent, "업데이트 성공!");
	                listenerUpdate.OnMovieUpdated();
	            } else {
	                System.out.println(resultMovie + "/" + resultMvInfo);
	                JOptionPane.showMessageDialog(parent, "업데이트 실패!");
	            }
	        } 
        }
    }

    /**
     * MOVIE 테이블 & MV_INFO 테이블 동시 삭
     */
    private void deleteMovie() {
        int confirm = JOptionPane.showConfirmDialog(this, textTitle.getText() + "의 데이터를 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            int result = daoMovie.delete(no);
            daoMvInfo.delete(textTitle.getText());
            
            if (result == 1) {
                dispose();
                JOptionPane.showMessageDialog(parent, "데이터가 삭제되었습니다.");
                listenerUpdate.OnMovieUpdated();
            } else {
                dispose();
                JOptionPane.showMessageDialog(parent, "데이터 삭제를 실패하였습니다.");
            }
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
    
    @Override
    public void onMvInfoUpdated() {
        Movie movie = daoMovie.select(no, id);
        textTitle.setText(movie.getTitle());
        textDirector.setText(movie.getDirector());
    }
}

