package edu.java.project.controller;

import static edu.java.project.model.Movie.Entity.COL_STATUS;
import static edu.java.project.model.Movie.Entity.TBL_MOVIES;
import static edu.java.project.model.MvInfo.Entity.*;

public interface MvInfoSql {
    
    // 영화 제목에 맞는 데이터 검색
    // select * from TABLE where TITLE = ?
    String SQL_SELECT_BY_TITLE_ID = String.format(
            "select * from %s where %s = ? and %s = ?", 
            TBL_MVINFO, COL_TITLE, COL_ID);

    String SQL_SELECT_BY_SCORE = String.format(
            "select %s.%s, %s.%s, %s.%s, %s.%s, %s.%s from %s, %s where %s.%s = %s.%s and %s.%s = ? and (%s.%s = ? or %s.%s = ?) order by %s desc", 
            TBL_MVINFO, COL_TITLE, TBL_MVINFO, COL_DIRECTOR, TBL_MVINFO, COL_GENRE, TBL_MVINFO, COL_COUNTRY, TBL_MVINFO, COL_MYSCORE, TBL_MVINFO, TBL_MOVIES, 
            TBL_MVINFO, COL_NO, TBL_MOVIES, COL_NO, TBL_MOVIES, COL_ID, TBL_MOVIES, COL_STATUS, TBL_MOVIES, COL_STATUS, COL_MYSCORE);
    
    
    String SQL_INSERT_TITLE_DIRECTOR = String.format(
           "insert into %s (%s, %s, %s, %s, %s) values (?, ?, ?, ?, ?)", 
           TBL_MVINFO, COL_TITLE, COL_DIRECTOR, COL_IMAGENAME, COL_NO, COL_ID);

   String SQL_INSERT_ALL = String.format(
           "insert into %s (%s, %s, %s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?, ?, ?)", 
           TBL_MVINFO, COL_TITLE, COL_DIRECTOR, COL_ACTOR_1, COL_ACTOR_2, COL_GENRE, COL_COUNTRY, COL_MYSCORE, COL_IMAGENAME);
   
   String SQL_UPDATE = String.format(
           "update %s set %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? where %s = ? and %s = ?", 
           TBL_MVINFO, COL_TITLE, COL_DIRECTOR, COL_ACTOR_1, COL_ACTOR_2, COL_GENRE, COL_COUNTRY, COL_MYSCORE, COL_IMAGENAME, COL_TITLE, COL_ID);
   
   String SQL_UPDATE_TITLE_DIRECTOR_IMAGE= String.format(
           "update %s set %s = ?, %s = ?, %s = ? where %s = ?", 
           TBL_MVINFO, COL_TITLE, COL_DIRECTOR, COL_IMAGENAME, COL_NO);
   
   String SQL_DELETE = String.format(
           "delete %s where %s = ?", 
           TBL_MVINFO, COL_TITLE);



}
