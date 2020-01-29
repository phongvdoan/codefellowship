package com.phongvdoan.codefellowship.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToMany


    String message;
    String body;
    String createdAt;


    public Post(String message,String body, String createdAt) {
    }
}
