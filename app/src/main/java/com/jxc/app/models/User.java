package com.jxc.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String email;
    private String photoUrl;
    private String bio;
    private Address address;
    private List<String> friendsId;

    public User(String username, String email, String photoUrl, String bio, Address address, List<String> friendsId) {
        this.username = username;
        this.email = email;
        this.photoUrl = photoUrl;
        this.bio = bio;
        this.address = address;
        this.friendsId = friendsId;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(List<String> friendsId) {
        this.friendsId = friendsId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", address=" + address +
                ", friendsId=" + friendsId +
                '}';
    }
}
