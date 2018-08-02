package com.hydrozagadka.DTO;

public class UserDetails {
    private String email;
    private boolean adminaaa;
    private String name;

    public UserDetails(String email, boolean adminaaa, String name) {
        this.email = email;
        this.adminaaa = adminaaa;
        this.name = name;
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
}
