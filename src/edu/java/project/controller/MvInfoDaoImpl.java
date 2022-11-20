package edu.java.project.controller;

import edu.java.project.model.Movie;
import edu.java.project.model.MvInfo;
import edu.java.project.view.MovieDetailUpdateFrameAtCreate.OnMvInfoUpdateListenerAtCreate;
import oracle.jdbc.OracleDriver;

import static edu.java.ojdbc.OracleJdbc.*;
import static edu.java.project.controller.MvInfoSql.*;
import static edu.java.project.model.MvInfo.Entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MvInfoDaoImpl implements MvInfoDao {
    
    // Singleton
    private static MvInfoDaoImpl instance = null;
    private MvInfoDaoImpl() {}
    public static MvInfoDaoImpl getInstance() {
        if (instance == null) {
            instance = new MvInfoDaoImpl();
        }
        return instance;
    }

    // 반복되는 코드 메서드로 정의
    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void closeSources(Connection conn, Statement stmt) throws SQLException {
        stmt.close();
        conn.close();
    }
    
    private void closeSources(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        rs.close();
        stmt.close();
        conn.close();
    }
    
    @Override
    public MvInfo select(String title, String id) {
        MvInfo mvInfo = null;
       
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_TITLE_ID);
            stmt.setString(1, title);
            stmt.setString(2, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String director = rs.getString(COL_DIRECTOR);
	            String actor_1 = rs.getString(COL_ACTOR_1);
	            String actor_2 = rs.getString(COL_ACTOR_2);
	            String genre = rs.getString(COL_GENRE);
	            String country = rs.getString(COL_COUNTRY);
	            String myScore = rs.getString(COL_MYSCORE);
	            String imageName = rs.getString(COL_IMAGENAME);
	            Integer no = rs.getInt(COL_NO);
            
                mvInfo = new MvInfo(title, director, actor_1, actor_2, genre, country, myScore, imageName, no, id);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeSources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return mvInfo;
    }
    
	@Override
	public int update(MvInfo mvInfo) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, mvInfo.getTitle());
			stmt.setString(2, mvInfo.getDirector());
			stmt.setString(3, mvInfo.getActor_1());
			stmt.setString(4, mvInfo.getActor_2());
			stmt.setString(5, mvInfo.getGenre());
			stmt.setString(6, mvInfo.getCountry());
			stmt.setString(7, mvInfo.getMyScore());
			stmt.setString(8, mvInfo.getImageName());
			stmt.setString(9, mvInfo.getTitle());
			stmt.setString(10, mvInfo.getId());
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeSources(conn, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	@Override
	public int inserTitleDirector(MvInfo mvInfo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_INSERT_TITLE_DIRECTOR);
			stmt.setString(1, mvInfo.getTitle());
			stmt.setString(2, mvInfo.getDirector());
			stmt.setString(3, mvInfo.getImageName());
			stmt.setInt(4, mvInfo.getNo());
			stmt.setString(5, mvInfo.getId());
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeSources(conn, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	@Override
	public void delete(String title) { // TODO: id 필요한지 chk
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setString(1, title);
			stmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeSources(conn, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public int updateTitleDirector(MvInfo mvInfo) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_TITLE_DIRECTOR_IMAGE);
			stmt.setString(1, mvInfo.getTitle());
			stmt.setString(2, mvInfo.getDirector());
			stmt.setString(3, mvInfo.getImageName());
			stmt.setInt(4, mvInfo.getNo());
			
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeSources(conn, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
    @Override
    public int countActor(String actor, String id) {
        // TODO
        int count = 0;
        return count;
    }
    @Override
    public List<MvInfo> selectScore(String userId) {
        List<MvInfo> listScore = new ArrayList<>();
        String seen = "보고왔어요";
        String again = "다시 볼래요";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_SCORE);
            stmt.setString(1, userId);
            stmt.setString(2, seen);
            stmt.setString(3, again);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
             String title = rs.getString(COL_TITLE);
             String director = rs.getString(COL_DIRECTOR);
             String genre = rs.getString(COL_GENRE);
             String country = rs.getString(COL_COUNTRY);
             String score = rs.getString(COL_MYSCORE);
             
             MvInfo addList = new MvInfo(title, director, null, null, genre, country, score, null, null, userId);
             listScore.add(addList);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeSources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return listScore;
    }

}
