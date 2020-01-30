package com.phongvdoan.codefellowship.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name="feed",
            joinColumns = {@JoinColumn(name="user")},
            inverseJoinColumns = {@JoinColumn(name="followed_user")}
    )
    public Set<Post> postsOfUsersIfollow;


    public void addPosts(Post followedUser){
        this.postsOfUsersIfollow.add(followedUser);
    }

    @ManyToMany(mappedBy = "postsOfUsersIfollow")
    public Set<Post> postsIShare;


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
