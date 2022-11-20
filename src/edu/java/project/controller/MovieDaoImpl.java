package edu.java.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static edu.java.ojdbc.OracleJdbc.*; // static 붙여줘야 함-!
import static edu.java.project.controller.MovieSql.*;
import static edu.java.project.model.Movie.Entity.*;
import static edu.java.project.model.MvInfo.Entity.COL_DIRECTOR;

import edu.java.project.model.Movie;
import oracle.jdbc.OracleDriver;


public class MovieDaoImpl implements MovieDao {
    
    // Singleton
    private static MovieDaoImpl instance = null;
    private MovieDaoImpl() {}
    public static MovieDaoImpl getInstance() {
        if (instance == null) {
            instance = new MovieDaoImpl();
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
    public List<Movie> select(String userId) {
        List<Movie> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Integer no = rs.getInt(COL_NO);
                String title = rs.getString(COL_TITLE);
                String director = rs.getString(COL_DIRECTOR);
                String viewDate = rs.getString(COL_VIEWDATE);
                String reveiw = rs.getString(COL_REVIEW);
                String status = rs.getString(COL_STATUS);
                String imageName = rs.getString(COL_IMAGENAME);
                String id = rs.getString(COL_ID);
                
                Movie movie = new Movie(no, title, director, viewDate, reveiw, status, imageName, id);
                list.add(movie);                   
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
        
        return list;
    }

    @Override
    public List<Movie> selectKeyword(String id, String textSearch) {
        List<Movie> list = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_KEYWORD);
            stmt.setString(1, "%" + textSearch + "%");
            stmt.setString(2, "%" + textSearch + "%");
            stmt.setString(3, "%" + textSearch + "%");
            stmt.setString(4, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Integer no = rs.getInt(COL_NO);
                String title = rs.getString(COL_TITLE);
                String director = rs.getString(COL_DIRECTOR);
                String viewDate = rs.getString(COL_VIEWDATE);
                String review = rs.getString(COL_REVIEW);
                String status = rs.getString(COL_STATUS);
                String userId = rs.getString(COL_ID);
                
                Movie movie = new Movie(no, title, director, viewDate, review, status, null, userId);
                list.add(movie);    
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
        
        return list;
    }

    @Override
    public Movie select(Integer no, String id) {
        Movie movie = null;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_INDEX);
            stmt.setInt(1, no);
            stmt.setString(2, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String title = rs.getString(COL_TITLE);
                String director = rs.getString(COL_DIRECTOR);
                String viewDate = rs.getString(COL_VIEWDATE);
                String review = rs.getString(COL_REVIEW);
                String status = rs.getString(COL_STATUS);
                String imageName = rs.getString(COL_IMAGENAME);
               // String userId = rs.getString(COL_ID);
                
                movie = new Movie(no, title, director, viewDate, review, status, imageName, id);
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
        
        return movie;
    }

    @Override
    public int insert(Movie movie) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, movie.getNo());
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getDirector());
            stmt.setString(4, movie.getViewDate());
            stmt.setString(5, movie.getReveiw());
            stmt.setString(6, movie.getStatus());
            stmt.setString(7, movie.getImageName());
            stmt.setString(8, movie.getId());
            
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
    public int update(Movie movie) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDirector());
            stmt.setString(3, movie.getViewDate());
            stmt.setString(4, movie.getReveiw());
            stmt.setString(5, movie.getStatus());
            stmt.setString(6, movie.getImageName());
            stmt.setInt(7, movie.getNo());
            
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
    public int updateTitleDirector(Movie movie) {
    	int result = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_TITLE_DIRECTOR);
			stmt.setString(1, movie.getTitle());
			stmt.setString(2, movie.getDirector());
			stmt.setString(3, movie.getTitle());
			stmt.setString(4, movie.getId());
			
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
    public int delete(Integer no) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, no);
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
    public int count(String id) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_COUNT);
            stmt.setString(1, id);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
            result = rs.getInt(1);
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
        
        return result;
    }
    
    @Override
    public int count(String id, String status) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_COUNT_STATUS);
            stmt.setString(1, id);
            stmt.setString(2, status);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
            result = rs.getInt(1);
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
        
        return result;
    }
    
    @Override
    public List<Movie> selectWish(String id) {
        List<Movie> list = new ArrayList<>();
        String seen = "보고왔어요";
        String again = "다시 볼래요";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_STATUS);
            stmt.setString(1, id);
            stmt.setString(2, seen);
            stmt.setString(3, again);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
               ; Integer no = rs.getInt(COL_NO);
                String title = rs.getString(COL_TITLE);
                String director = rs.getString(COL_DIRECTOR);
                String viewDate = rs.getString(COL_VIEWDATE);
                String review = rs.getString(COL_REVIEW);
                String status = rs.getString(COL_STATUS);
                String imageName = rs.getString(COL_IMAGENAME);
                
                Movie movie = new Movie(no, title, director, viewDate, review, status, imageName, id);
                list.add(movie);
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
        
        return list;
    }
    
    @Override
    public int countDirector(String director, String id) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_COUNT_PREFER);
            stmt.setString(1, director);
            stmt.setString(2, id);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                result = rs.getInt(1);
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
        
        return result;
    }

}
