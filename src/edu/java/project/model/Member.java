package edu.java.project.model;

public class Member {
    
    public interface Entity {
        String TBL_MEMBER = "MEMBER";
        String COL_MID = "MID";
        String COL_MPW = "MPW";
        String COL_MNAME = "MNAME";
        String COL_MEMAIL = "MEMAIL";
        String COL_NO = "NO";
    }
    
    private String mId;
    private String mPw;
    private String mName;
    private String mEmail;
    private Integer no;
    
    public Member() {}

    public Member(String mId, String mPw, String mName, String mEmail, Integer no) {
        this.mId = mId;
        this.mPw = mPw;
        this.mName = mName;
        this.mEmail = mEmail;
        this.no = no;
    }

    public String getmId() {
        return mId;
    }

    public String getmPw() {
        return mPw;
    }

    public String getmName() {
        return mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public Integer getNo() {
        return no;
    }
    
    @Override
    public String toString() {
        return String.format(
                "MemberList(MID:%s, MPW:%s, MNAME:%s, MEMAIL:%s, NO:%s)", 
                mId, mPw, mName, mEmail, no);
    }

}
