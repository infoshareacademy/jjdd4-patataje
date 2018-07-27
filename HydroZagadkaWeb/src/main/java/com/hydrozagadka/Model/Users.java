package com.hydrozagadka.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USERS")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Email")
    @NotNull
    private String email;
    @Column(name="Token")
    @NotNull
    private String token;
    @Column(name="Adminaaa")
    @NotNull
    private boolean adminaaa;
    @Column(name="Stats")
    private Integer stats;

    public Users() {
    }

    public Users(String email, String token, boolean adminaaa, Integer stats) {
        this.email = email;
        this.token = token;
        this.adminaaa = adminaaa;
        this.stats = stats;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAdminaaa() {
        return adminaaa;
    }

    public void setAdminaaa(boolean adminaaa) {
        this.adminaaa = adminaaa;
    }

    public Integer getStats() {
        return stats;
    }

    public void setStats(Integer stats) {
        this.stats = stats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
