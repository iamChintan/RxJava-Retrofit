package com.example.rxjavaretrpfot.Model;

public class Root{
    public int userId;
    public int id;
    public String title;
    public String body;

    public Root() {
    }

    public Root(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}