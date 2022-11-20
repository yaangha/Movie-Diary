package edu.java.project.model;

public class MvInfo {
    
    public interface Entity {
        String TBL_MVINFO = "MV_INFO";
        String COL_TITLE = "TITLE";
        String COL_DIRECTOR = "DIRECTOR";
        String COL_ACTOR_1 = "ACTOR_1";
        String COL_ACTOR_2 = "ACTOR_2";
        String COL_GENRE = "GENRE";
        String COL_COUNTRY = "COUNTRY";
        String COL_MYSCORE = "MYSCORE";
        String COL_IMAGENAME = "IMAGENAME";
        String COL_NO = "NO";
        String COL_ID = "ID";
    }
    
    private String title;
    private String director;
    private String actor_1;
    private String actor_2;
    private String genre;
    private String country;
    private String myScore;
    private String imageName;
    private Integer no;
    private String id;    
    
    public MvInfo() {}

    public MvInfo(String title, String director, String actor_1, String actor_2, String genre, String country, String myScore, String imageName, Integer no, String id) {
        super();
        this.title = title;
        this.director = director;
        this.actor_1 = actor_1;
        this.actor_2 = actor_2;
        this.genre = genre;
        this.country = country;
        this.myScore = myScore;
        this.imageName = imageName;
        this.no = no;
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getActor_1() {
        return actor_1;
    }

    public String getActor_2() {
        return actor_2;
    }

    public String getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }
    
    public String getMyScore() {
        return myScore;
    }

    public String getImageName() {
        return imageName;
    }
    
    public Integer getNo() {
        return no;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return String.format(
                "MvInfo(Title:%s, Director:%s, Actor_1:%s, Actor_2:%s Genre:%s, Country:%s, MyScore:%s, ImageName:%s, No:%d, Id:%s)", 
                title, director, actor_1, actor_2, genre, country, myScore, imageName, no, id);
    }

}
