package com.phongvdoan.codefellowship.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    ApplicationUser applicationUser;

    String message;
    String body;
    String createdAt;


    public Post( ApplicationUser applicationUser, String message,String body, String createdAt) {
        this.applicationUser = applicationUser;
        this.message = message;
        this.body = body;
        this.createdAt = createdAt;
    }

    public Post(){}

    public String getMessage() {
        return message;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
