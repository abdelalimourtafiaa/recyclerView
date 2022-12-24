package com.example.recycler;

public class Post {

    String Title ;
    String Description;


    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Post(String title, String description) {
        Title = title;
        Description = description;

    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }




}
