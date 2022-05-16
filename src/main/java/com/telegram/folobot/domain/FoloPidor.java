package com.telegram.folobot.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Comparator;

@Entity // This tells Hibernate to make a table out of this class
@IdClass(FolopidorId.class)
public class FoloPidor {
    @Id
    private long chatid;
    @Id
    private long userid;
    private int score;
    private String tag;

    public FoloPidor() {
    }

    public FoloPidor(long chatid, long userid) {
        this(chatid, userid, 0);
    }

    public FoloPidor(long chatid, long userid, int score) {
        this(chatid, userid, score, "");
    }

    public FoloPidor(long chatid, long userid, String tag) {
        this(chatid, userid, 0, tag);
    }

    public FoloPidor(long chatid, long userid, int score, String tag) {
        this.chatid = chatid;
        this.userid = userid;
        this.score = score;
        this.tag = tag;
    }

    public long getChatid() {
        return chatid;
    }

    public void setChatid(long chatid) {
        this.chatid = chatid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

//    @Override
//    public int compareTo(FoloPidor o) {
//        Comparator.comparing(FoloPidor::getChatid)
//                .thenComparing(FoloPidor::getScore);
//        return 0;
//    }
}

