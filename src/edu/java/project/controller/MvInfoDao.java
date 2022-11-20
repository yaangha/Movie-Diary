package edu.java.project.controller;

import java.util.List;

import edu.java.project.model.Movie;
import edu.java.project.model.MvInfo;

public interface MvInfoDao {
    
    /**
     * 영화 제목에 따른 데이터 리턴
     * 
     * @param title 확인하고자 하는 영화 제목
     * @return 해당 행 리턴
     */
    MvInfo select(String title, String id);
    
    /**
     *  내 별점 높은 순으로 나열(내림차순)
     * @param userId ID 확인용
     * @param score 정렬시 필요
     * @return 해당 목록 전체 리턴
     */
    List<MvInfo> selectScore(String userId);
 
    /**
     * 상세 내역 추가하면 테이블에 정보 수정
     * 
     * @param title 외래키 역할..?
     * @return 해당 행 리턴
     */
    int update(MvInfo mvInfo);
    
    int updateTitleDirector(MvInfo mvInfo);
    
    /**
     * createFrame에서 받은 정보를 일부 저
     * @param mvInfo
     */
    int inserTitleDirector(MvInfo mvInfo);
    
    void delete(String title);
    
    int countActor(String actor, String id);
    
}
