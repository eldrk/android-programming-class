package com.example.student.hellomovie.format;

import java.util.GregorianCalendar;

/**
 * Created by student on 2018-12-14.
 */

public class BookDate {

    GregorianCalendar movie_start_time;
    String runningTime;
    String title;
    int totalSeat;
    int resSeat;

    public BookDate(GregorianCalendar movie_start_time, String runningTime, String title, int totalSeat, int resSeat) {
        this.movie_start_time = movie_start_time;
        this.runningTime = runningTime;
        this.title = title;
        this.totalSeat = totalSeat;
        this.resSeat = resSeat;
    }

    public GregorianCalendar getMovie_start_time() {
        return movie_start_time;
    }

    public void setMovie_start_time(GregorianCalendar movie_start_time) {
        this.movie_start_time = movie_start_time;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public int getResSeat() {
        return resSeat;
    }

    public void setResSeat(int resSeat) {
        this.resSeat = resSeat;
    }
}
