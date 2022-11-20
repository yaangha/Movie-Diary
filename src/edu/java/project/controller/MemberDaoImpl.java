package edu.java.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.java.project.model.Member;
import oracle.jdbc.OracleDriver;
import static edu.java.project.controller.MemberListSql.*;
import static edu.java.ojdbc.OracleJdbc.*;
import static edu.java.project.model.Member.Entity.*;

public class MemberDaoImpl implements MemberDao {
    
    // Singleton
    private static MemberDaoImpl instance = null;
    private MemberDaoImpl() {}
    public static MemberDaoImpl getInstance() {
        if (instance == null) {
            instance = new MemberDaoImpl();
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
        
    // 전체 목록에서 로그인할 멤버 찾을 때 사용
    @Override
    public List<Member> select() {
        
        List<Member> mList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String mId = rs.getString(COL_MID);
                String mPw = rs.getString(COL_MPW);
                String mName = rs.getString(COL_MNAME);
                String mEmail = rs.getString(COL_MEMAIL);
                Integer no = rs.getInt(COL_NO);
                
                Member memberList = new Member(mId, mPw, mName, mEmail, no);
                mList.add(memberList);
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
                
        return mList;
    }
    
    @Override
    public Member select(String mId) {
        Member memberList = null;;
                
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
                
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setString(1, mId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String mPw = rs.getString(COL_MPW);
                String mName = rs.getString(COL_MNAME);
                String mEmail = rs.getString(COL_MEMAIL);
                Integer no = rs.getInt(COL_NO);
                
                memberList = new Member(mId, mPw, mName, mEmail, no);
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
        
        return memberList;
    }
    @Override
    public int insert(Member member) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, member.getmId());
            stmt.setString(2, member.getmPw());
            stmt.setString(3, member.getmName());
            stmt.setString(4, member.getmEmail());
            
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

}
