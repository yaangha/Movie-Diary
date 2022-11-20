package edu.java.project.model;

public class Movie {
    
    public interface Entity {
        String TBL_MOVIES = "MOVIES";
        String COL_NO = "NO";
        String COL_TITLE = "TITLE";
        String COL_DIRECTOR = "DIRECTOR";
        String COL_VIEWDATE = "VIEWDATE";
        String COL_REVIEW = "REVIEW";
        String COL_STATUS = "STATUS";
        String COL_IMAGENAME = "IMAGENAME";
        String COL_ID = "ID";
    }
    
    private Integer no;
    private String title;
    private String director;
    private String viewDate;
    private String review;
    private String status;
    private String imageName;
    private String id;
    
    // constructor
    public Movie() {}

    public Movie(Integer no, String title, String director, String viewDate, String reveiw, String status, String imageName, String id) {
        this.no = no;
        this.title = title;
        this.director = director;
        this.viewDate = viewDate;
        this.review = reveiw;
        this.status = status;
        this.imageName = imageName;
        this.id = id;
    }

    // getters

    public Integer getNo() {
        return no;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getViewDate() {
        return viewDate;
    }

    public String getReveiw() {
        return review;
    }

    public String getStatus() {
        return status;
    }
    
    public String getImageName() {
        return imageName;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return String.format("Movie(No: %d, Title: %s, Director: %s, ViewDate: %s, Review: %s, Status: %s, ImageName:%s, Id:%s)", 
                no, title, director, viewDate, review, status, imageName, id);
    }

    
}
