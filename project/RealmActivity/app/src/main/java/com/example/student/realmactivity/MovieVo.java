package com.example.student.realmactivity;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class MovieVo extends RealmObject {

    private int number;
    private String title;

    public RealmList<ActorVo> actorList = new RealmList<>();

    public RealmList<ActorVo> getActorList(){
        return actorList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActorList(RealmList<ActorVo> actorList) {
        this.actorList = actorList;
    }
}
