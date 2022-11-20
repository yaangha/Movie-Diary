package edu.java.project.controller;

import static edu.java.project.model.Member.Entity.*;

public interface MemberListSql {
    
    String SQL_SELECT_ALL = String.format(
            "select * from %s", 
            TBL_MEMBER);
    
    String SQL_SELECT_BY_ID = String.format(
            "select * from %s where %s = ?", 
            TBL_MEMBER, COL_MID);
    
    String SQL_INSERT = String.format(
            "insert into %s (%s, %s, %s, %s) values (?, ?, ?, ?)", 
            TBL_MEMBER, COL_MID, COL_MPW, COL_MNAME, COL_MEMAIL);

}
