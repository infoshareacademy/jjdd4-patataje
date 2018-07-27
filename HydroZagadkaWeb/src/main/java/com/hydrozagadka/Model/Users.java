package com.hydrozagadka.Model;

import javax.persistence.*;

@Entity
@Table(name="USERS")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String token;
    @Column
    private boolean admin;
    @Column
    private Long stats;

    public Users() { }

    public Users(String email, String token, boolean admin, Long stats) {
        this.email = email;
        this.token = token;
        this.admin = admin;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Long getStats() {
        return stats;
    }

    public void setStats(Long stats) {
        this.stats = stats;
    }
}
