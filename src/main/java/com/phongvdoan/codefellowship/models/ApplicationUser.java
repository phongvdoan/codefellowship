package com.phongvdoan.codefellowship.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name="following",
            joinColumns = {@JoinColumn(name="userWhoFollows")},
            inverseJoinColumns = {@JoinColumn(name="followed_user")}
    )
    public Set<ApplicationUser> usersIfollow;


//    public void addPosts(ApplicationUser followedUser){
//        this.usersIfollow.add(followedUser);
//    }

    @ManyToMany(mappedBy = "usersIfollow")
    public Set<ApplicationUser> usersWhoFollowMe;

    @OneToMany(mappedBy = "applicationUser")
    List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    String username;
    String password;
    String firstName;
    String lastName;
    String dateofBirth;
    String bio;

    public ApplicationUser() {
    }

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateofBirth, String bio){
        this.bio = bio;
        this.dateofBirth = dateofBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

