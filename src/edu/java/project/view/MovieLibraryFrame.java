package edu.java.project.view;

import static edu.java.project.model.Movie.Entity.COL_DIRECTOR;
import static edu.java.project.model.Movie.Entity.COL_NO;
import static edu.java.project.model.Movie.Entity.COL_STATUS;
import static edu.java.project.model.Movie.Entity.COL_TITLE;
import static edu.java.project.model.MvInfo.Entity.*;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import edu.java.project.controller.MovieDaoImpl;
import edu.java.project.controller.MvInfoDaoImpl;
import edu.java.project.model.Movie;
import edu.java.project.model.MvInfo;
import edu.java.project.view.MovieDetailUpdateFrameAtUpdate.OnMvInfoUpdateListenerAtUpdate;
import edu.java.project.view.MovieUpdateFrame.OnMovieUpdateListener;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MovieLibraryFrame extends JFrame implements OnMovieUpdateListener, OnMvInfoUpdateListenerAtUpdate {

    /*
    private static final String[] COL_NAMES = {
            COL_TITLE, COL_DIRECTOR, COL_STATUS, COL_NO
    };
    */ 
	private Component parent;
	private MovieDaoImpl daoMovie;
	private MvInfoDaoImpl daoMvInfo;
	private String id;
    private String[] COL_NAMES = {COL_TITLE, COL_DIRECTOR, COL_STATUS, COL_NO};
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JLabel lblSum;
	private JTextField textSearch;

	/**
	 * Launch the application.
	 */
	public static void openLibrary(Component parent, String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieLibraryFrame frame = new MovieLibraryFrame(parent, id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MovieLibraryFrame(Component parent, String id) {
		this.parent = parent;
		this.id = id;
		this.daoMovie = MovieDaoImpl.getInstance();
		this.daoMvInfo = MvInfoDaoImpl.getInstance();
		initialize();
		
		initializeViewAll();
	}
		
	
	public void initializeViewAll() {
        model = new DefaultTableModel(null, COL_NAMES);
        table.setModel(model);

        List<Movie> list = daoMovie.select(id);
        for (Movie m : list) {
            Object[] row = {m.getTitle(), m.getDirector(), m.getStatus(), m.getNo()};
            model.addRow(row);
        }
        
        int count = daoMovie.count(id);
        lblSum.setText("지금까지 총 " + count + "편의 영화를 보관했어요!");
 
    }

    /**
	 * Create the frame.
	 */
	public void initialize() {
	    setTitle("라이브러리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int x = parent.getX();
		int y = parent.getY();
		setBounds(x, y, 945, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSeen = new JButton("보고왔어요");
		btnSeen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    
               // lblStatus.setText(btnSeen.getText());

				model = new DefaultTableModel(null, COL_NAMES);
		        table.setModel(model);
		        		        
		        List<Movie> list = daoMovie.select(id);
		        for (Movie m : list) {
		            if (m.getStatus().equals(btnSeen.getText())) {
			        	Object[] row = {m.getTitle(), m.getDirector(), m.getStatus(), m.getNo()};
			            model.addRow(row);
		            }
		        }
		        
		        int count = daoMovie.count(id, btnSeen.getText());
		        lblSum.setText("[" + btnSeen.getText() + "] 서랍에 지금까지 총 " + count + "편의 영화를 보관했어요!");
		        
			}
		});
		btnSeen.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnSeen.setBounds(110, 719, 102, 32);
		contentPane.add(btnSeen);
		
		JButton btnAgain = new JButton("다시 볼래요");
		btnAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model = new DefaultTableModel(null, COL_NAMES);
		        table.setModel(model);

		        List<Movie> list = daoMovie.select(id);
		        for (Movie m : list) {
		            if (m.getStatus().equals(btnAgain.getText())) {
			        	Object[] row = {m.getTitle(), m.getDirector(), m.getStatus(), m.getNo()};
			            model.addRow(row);
		            }
		        }
		        
		        int count = daoMovie.count(id, btnAgain.getText());
                lblSum.setText("[" + btnAgain.getText() + "] 서랍에 지금까지 총 " + count + "편의 영화를 보관했어요!");
			}
		});
		btnAgain.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnAgain.setBounds(320, 719, 102, 32);
		contentPane.add(btnAgain);
		
		JButton btnWant = new JButton("보고싶어요");
		btnWant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    model = new DefaultTableModel(null, COL_NAMES);
		        table.setModel(model);

		        List<Movie> list = daoMovie.select(id);
		        for (Movie m : list) {
		            if (m.getStatus().equals(btnWant.getText())) {
			        	Object[] row = {m.getTitle(), m.getDirector(), m.getStatus(), m.getNo()};
			            model.addRow(row);
		            }
		        }
		        
		        int count = daoMovie.count(id, btnWant.getText());
                lblSum.setText("[" + btnWant.getText() + "] 서랍에 지금까지 총 " + count + "편의 영화를 보관했어요!");
		        
			}
		});
		btnWant.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnWant.setBounds(215, 719, 102, 32);
		contentPane.add(btnWant);
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHome.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnHome.setBounds(6, 10, 71, 40);
		contentPane.add(btnHome);
		
        JButton btnViewAll = new JButton("전체보기");
        btnViewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model = new DefaultTableModel(null, COL_NAMES);
                table.setModel(model);

                List<Movie> list = daoMovie.select(id);
                for (Movie m : list) {
                    Object[] row = {m.getTitle(), m.getDirector(), m.getStatus(), m.getNo()};
                    model.addRow(row);
                }
                
                int count = daoMovie.count(id);
                lblSum.setText("[" + btnViewAll.getText() + "] 서랍에 지금까지 총 " + count + "편의 영화를 보관했어요!");
            }
        });
        btnViewAll.setFont(new Font("D2Coding", Font.PLAIN, 12));
        btnViewAll.setBounds(6, 719, 102, 32);
        contentPane.add(btnViewAll);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 155, 911, 554);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("D2Coding", Font.PLAIN, 12));
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		
		JButton btnDetail = new JButton("수정 / 삭제");
		btnDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    detailMovie();
			    /*
				int row = table.getSelectedRow();
				
				if (row == -1) {
					JOptionPane.showMessageDialog(MovieLibraryFrame.this, "행을 선택하세요!");
					return;
				}
				String title = model.getValueAt(row, 0).toString();
				MovieDetailUpdateFrameAtUpdate.newMovieDetailUpdateFrameAtUpdate(MovieLibraryFrame.this, title, id, MovieLibraryFrame.this);
			    */
			}
		});
		btnDetail.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnDetail.setBounds(426, 719, 111, 32);
		contentPane.add(btnDetail);
		
		lblSum = new JLabel("New label");
		lblSum.setHorizontalAlignment(SwingConstants.LEFT);
		lblSum.setFont(new Font("D2Coding", Font.PLAIN, 12));
		lblSum.setBounds(12, 133, 382, 23);
		contentPane.add(lblSum);
		
		textSearch = new JTextField();
        textSearch.setHorizontalAlignment(SwingConstants.RIGHT);
        textSearch.setFont(new Font("D2Coding", Font.PLAIN, 12));
        textSearch.setBounds(642, 121, 200, 23);
        contentPane.add(textSearch);
        textSearch.setColumns(10);
        
        JButton btnSearch = new JButton("검 색");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchSelectKeyword();
            }
        });
        btnSearch.setFont(new Font("D2Coding", Font.BOLD, 12));
        btnSearch.setBounds(846, 121, 71, 24);
        contentPane.add(btnSearch);
        
        JButton btnSearch_1 = new JButton("내 별점 높은 순↓");
        btnSearch_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columnScore = {COL_TITLE, COL_DIRECTOR, COL_GENRE, COL_COUNTRY, COL_MYSCORE};
                model = new DefaultTableModel(null, columnScore);
                table.setModel(model);

                List<MvInfo> list = daoMvInfo.selectScore(id);
                for (MvInfo m : list) {
                    Object[] row = {m.getTitle(), m.getDirector(), m.getGenre(), m.getCountry(), m.getMyScore()};
                    model.addRow(row);
                }
            }
        });
        btnSearch_1.setFont(new Font("D2Coding", Font.BOLD, 12));
        btnSearch_1.setBounds(765, 719, 152, 24);
        contentPane.add(btnSearch_1);
	}
	
	private void searchSelectKeyword() {
        String textKeyword = textSearch.getText();
        
        if (textKeyword.equals("")) {
            JOptionPane.showMessageDialog(this, "검색할 키워드를 입력하세요.");
            return;
        }
            
        model = new DefaultTableModel(null, COL_NAMES);
        table.setModel(model);

        List<Movie> list = daoMovie.selectKeyword(id, textKeyword);

        if (list.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "일치하는 데이터가 없습니다.");
        	initializeViewAll();
        	} else {
            for (Movie m : list) {
                Object[] row = {m.getTitle(), m.getDirector(), m.getStatus(), m.getNo()};
                model.addRow(row);
            }
            textSearch.setText("");
        }
    }
	
    private void detailMovie() {
        int row = table.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "확인하려는 영화를 선택하세요.");
            return;
        }
        
        Integer no = (Integer) model.getValueAt(row, 3);
        
        MovieUpdateFrame.updateMovieFrame(this, no, id, this, this);        
    }

    @Override
    public void OnMovieUpdated() {
        initializeViewAll();
        
    }

    @Override
    public void onMvInfoUpdated() {
        initializeViewAll();
    }
}
