package com.mediomelon.mvvmbasic.model;

public class Hero {
    public final String name;
    public final String realname;
    public final String team;
    public final String imageurl;

    public Hero(String name, String realname, String team, String imageurl) {
        this.name = name;
        this.realname = realname;
        this.team = team;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public String getRealname() {
        return realname;
    }

    public String getTeam() {
        return team;
    }

    public String getImageurl() {
        return imageurl;
    }
}
