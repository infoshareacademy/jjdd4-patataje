package com.hydrozagadka.DTO;

public class UserDetails {
    private String email;
    private boolean adminaaa;
    private String name;
    private Integer stats;

    public UserDetails(String email, boolean adminaaa, String name, Integer stats) {
        this.email = email;
        this.adminaaa = adminaaa;
        this.name = name;
        this.stats = stats;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdminaaa() {
        return adminaaa;
    }

    public void setAdminaaa(boolean adminaaa) {
        this.adminaaa = adminaaa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStats() {
        return stats;
    }

    public void setStats(Integer stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserDetails{");
        sb.append("email='").append(email).append('\'');
        sb.append(", adminaaa=").append(adminaaa);
        sb.append(", name='").append(name).append('\'');
        sb.append(", stats=").append(stats);
        sb.append('}');
        return sb.toString();
    }
}
