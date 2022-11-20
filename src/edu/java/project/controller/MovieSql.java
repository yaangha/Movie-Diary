package edu.java.project.controller;

import static edu.java.project.model.Movie.Entity.*;
import static edu.java.project.model.MvInfo.Entity.TBL_MVINFO;

public interface MovieSql {
    
    // 전체 목록 검색 - 관람일 내림차순
    // select * from TABLE;
    //String SQL_SELECT_ALL = String.format(
      //      "select * from %s where %s = ? order by %s desc, %s desc, %s desc", 
        //    TBL_MOVIES, COL_ID, COL_STATUS, COL_TITLE, COL_VIEWDATE);
    
    String SQL_SELECT_ALL = String.format(
            "select * from %s where %s = ? order by %s desc", 
            TBL_MOVIES, COL_ID, COL_NO);
    
    // <본 영화>만 메인 화면에 노출
    // select * from TABLE where WISH = ?
    String SQL_SELECT_STATUS = String.format(
            "select * from %s, %s where %s.%s = %s.%s and %s.%s = ? and (%s.%s = ? or %s.%s = ?) order by %s desc",
            TBL_MVINFO, TBL_MOVIES, TBL_MVINFO, COL_ID, TBL_MOVIES, COL_ID, TBL_MOVIES, COL_ID, TBL_MOVIES, COL_STATUS, TBL_MOVIES, COL_STATUS, COL_VIEWDATE);
    
    // 키워드에 맞는 목록 검색(제목, 감독, 관람일) - 관람일 내림차순
    // select * from TABLE where KEYWORD like %?%; -> %?%라고 자바에서는 작성 X
    String SQL_SELECT_BY_KEYWORD = String.format(
            "select * from %s where (lower(%s) like ? or lower(%s) like ? or lower(%s) like ?) and %s = ? order by %s desc, %s desc", 
            TBL_MOVIES, COL_TITLE, COL_DIRECTOR, COL_REVIEW, COL_ID, COL_STATUS, COL_TITLE);
    
    // 상세 내역 검색
    // select * from TABLE where NO = ?;
    String SQL_SELECT_BY_INDEX = String.format(
            "select * from %s where %s = ? and %s = ?", 
            TBL_MOVIES, COL_NO, COL_ID);

      String SQL_INSERT = String.format(
      "insert into %s (%s, %s, %s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?, ?, ?)", 
      TBL_MOVIES, COL_NO, COL_TITLE, COL_DIRECTOR, COL_VIEWDATE, COL_REVIEW, COL_STATUS, COL_IMAGENAME, COL_ID);

    // 데이터 수정
    // update TABLE set COLUMN = ?, ... where NO = ?;
    String SQL_UPDATE = String.format(
            "update %s set %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? where %s = ?", 
            TBL_MOVIES, COL_TITLE, COL_DIRECTOR, COL_VIEWDATE, COL_REVIEW, COL_STATUS, COL_IMAGENAME, COL_NO);
    
    String SQL_UPDATE_TITLE_DIRECTOR = String.format(
            "update %s set %s = ?, %s = ? where %s = ? and %s = ?", 
            TBL_MOVIES, COL_TITLE, COL_DIRECTOR, COL_TITLE, COL_ID);
    
    // 데이터 삭제
    // delete from TABLE where NO = ?
    String SQL_DELETE = String.format(
            "delete from %s where %s = ?", 
            TBL_MOVIES, COL_NO);
    
    // 데이터 합계
    // select count(*) from TABLE where id = ?;
    String SQL_COUNT = String.format(
            "select count(*) from %s where %s = ?", 
            TBL_MOVIES, COL_ID);
    
    // 사용자의 status에 따른 데이터 합계
    // select count(*) from TABLE where status = 본영화;
    String SQL_COUNT_STATUS = String.format(
            "select count(*) from %s where %s = ? and %s = ?", 
            TBL_MOVIES, COL_ID, COL_STATUS);
    
    // 선호하는 감독/배우 누구인가
    String SQL_COUNT_PREFER = String.format(
            "select count(*) from %s where %s = ? and %s = ?", 
            TBL_MOVIES, COL_DIRECTOR, COL_ID);

}
