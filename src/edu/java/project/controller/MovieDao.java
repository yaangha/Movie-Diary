package edu.java.project.controller;

import java.util.List;

import edu.java.project.model.Movie;

public interface MovieDao {
    
    /**
     * 전체 목록 선택시 메인 화면에 노출될 목록
     * 
     * @return 전체 목록
     */
    List<Movie> select(String userId); // SQL_SELECT_ALL
    
    /**
     * 메인 화면에 노출될 <본 영화> 전체 목록
     * 
     * @return <본 영화> 전체 목록
     */
    List<Movie> selectWish(String id); // SQL_SELECT_WISH
    
    /**
     * 키워드로 검색
     * 
     * @param textSearch 찾을 내용(전체 데이터에서)
     * @return 키워드를 포함하는 목록 전체 리턴
     */
    List<Movie> selectKeyword(String id, String textSearch); // SQL_SELECT_BY_TEXTSEARCH
    
    /**
     * 상세 내역 확인
     * 
     * @param no 상세 내역 확인하고자 하는 행 번호
     * @return 해당 행 데이터 리턴
     */
    Movie select(Integer no, String id); // SQL_SELECT_BY_NO
    
    /**
     * 데이터 추가
     * 
     * @param movie Movie 타입의 데이터
     * @return 성공하면 1을 리턴
     */
    int insert(Movie movie); // SQL_INSERT
    
    /**
     * 데이터 수정
     * 
     * @param movie Movie 타입의 수정 데이터
     * @return 성공하면 1을 리턴
     */
    int update(Movie movie); // SQL_UPDATE
    
    /**
     * 상세내역 수정 시 MV_INFO와 동일한 컬럼만 변
     * @param movie
     * @return
     */
    int updateTitleDirector(Movie movie);
    
    /**
     * 데이터 삭제
     * 
     * @param no 삭제하고자 하는 행 번호
     * @return 성공하면 1을 리턴
     */
    int delete(Integer no); //SQL_DELETE
    
    /**
     * 데이터 개수 구하기
     * 
     * @return 총 합계
     */
    int count(String id);
    
    /**
     * status에 따른 개수 구하기
     * @param id 이용자가 누구인지
     * @param status 어떤 상태인지
     * @return 총 개수를 리턴
     */
    int count(String id, String status);
    
    int countDirector(String director, String id);


}
