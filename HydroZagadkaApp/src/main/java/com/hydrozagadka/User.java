package com.hydrozagadka;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Email")
    @NotNull
    private String email;
    @Column(name = "Token")
    @NotNull
    private String token;
    @Column(name = "Adminaaa")
    @NotNull
    private boolean adminaaa;
    @Column(name = "Stats")
    private Integer stats;
    @ManyToMany
    @JoinTable(
            name = "USER_WATERCONTAINER",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "container_id", referencedColumnName = "id"))
            List<WaterContainer> waterContainerId;

    public User() {
    }

    public User(@NotNull String email, @NotNull String token, @NotNull boolean adminaaa, Integer stats, List<WaterContainer> waterContainerId) {
        this.email = email;
        this.token = token;
        this.adminaaa = adminaaa;
        this.stats = stats;
        this.waterContainerId = waterContainerId;
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
    public List<WaterContainer> getWaterContainerId() {
        return waterContainerId;
    }

    public void setWaterContainerId(List<WaterContainer> waterContainerId) {
        this.waterContainerId = waterContainerId;
    }
}
