package edu.java.project.controller;

import java.util.List;

import edu.java.project.model.Member;

public interface MemberDao {

    /**
     * 전체 회원 정보 검색
     * 
     * @return 전체 목록
     */
    List<Member> select();
    
    /**
     * 아이디 받아서 비밀번호 확인하기 위해, 해당 아이디 행 불러오기
     * 
     * @param mId 확인할 아이디
     * @return 아이디가 동일한 행 리턴
     */
    Member select(String mId);
    
    /**
     * 회원가입할 신규멤버 정보 받아서 등록
     * 
     * @param member 회원가입창에 적은 정보
     * @return 성공하면 1을 리턴
     */
    int insert(Member member);
    
}
