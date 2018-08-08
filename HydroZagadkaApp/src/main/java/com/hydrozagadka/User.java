package com.hydrozagadka;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    @NotNull
    private String email;
    @Column(name = "adminaaa")
    @NotNull
    private Boolean adminaaa;
    @NotNull
    @Column
    private String token;
    @Column(name = "stats")
    private Integer stats;
    @Column(name="name")
    private String name;
    @Column(name="pic_path")
    private String urlPicPath;
    @Column(name="locale")
    private String locale;
    @ManyToMany
    @JoinTable(
            name = "USER_WATERCONTAINER",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "container_id"))
    private List<WaterContainer> waterContainerId;

    public User() {
    }

      public User(@NotNull String token, @NotNull String name , @NotNull String email, @NotNull Boolean adminaaa, Integer stats, List<WaterContainer> waterContainerId, String urlPicPath, String locale) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.adminaaa = adminaaa;
        this.stats = stats;
        this.waterContainerId = waterContainerId;
        this.urlPicPath = urlPicPath;
        this.locale = locale;
    }

    public String getUrlPicPath() {
        return urlPicPath;
    }

    public void setUrlPicPath(String urlPicPath) {
        this.urlPicPath = urlPicPath;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Boolean isAdminaaa() {
        return adminaaa;
    }

    public void setAdminaaa(Boolean adminaaa) {
        this.adminaaa = adminaaa;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", adminaaa=").append(adminaaa);
        sb.append(", token='").append(token).append('\'');
        sb.append(", stats=").append(stats);
        sb.append(", name='").append(name).append('\'');
        sb.append(", urlPicPath='").append(urlPicPath).append('\'');
        sb.append(", locale='").append(locale).append('\'');
        sb.append(", waterContainerId=").append(waterContainerId);
        sb.append('}');
        return sb.toString();
    }
}