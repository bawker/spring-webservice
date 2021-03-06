package com.bbawker.webservice.dto.practice;

import com.bbawker.webservice.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String userId;

    @JsonIgnore
    private String password;

    private String name;
    private String email;

    public  boolean matchId(Long newId) {
        if (newId == null) {
            return false;
        }

        return newId.equals(id);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public boolean matchPassword(String newPassword) {
        if(newPassword == null) {
            return false;
        }

        return newPassword.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void update(User newUser) {
        this.password = newUser.password;
        this.name = newUser.name;
        this.email = newUser.email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
