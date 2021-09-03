package net.hwcloud.dto;

import javax.persistence.*;

@Entity // Database와 연결되는 것을 나타내는 Annotation
public class User {

    @Id // Primary Key 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 Increment
    private Long id;

    @Column(nullable = false)
    private String userId;

    private String password;
    private String name;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void update(User newUser) {
        this.userId = newUser.getUserId();
        this.password = newUser.getPassword();
        this.name = newUser.getName();
        this.email = newUser.getEmail();
    }
}
