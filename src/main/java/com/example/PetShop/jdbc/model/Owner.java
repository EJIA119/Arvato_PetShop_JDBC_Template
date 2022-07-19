package com.example.PetShop.jdbc.model;

import java.util.List;

public class Owner {

    private int id;
    private String first_name;
    private String last_name;
    private String date_created;
    private String date_modified;

    public Owner() {
    }

    public Owner(int id, String first_name, String last_name, String date_created, String date_modified) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_created = date_created;
        this.date_modified = date_modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", dateCreated='" + date_created + '\'' +
                ", dateModified='" + date_modified + '\'' +
                '}';
    }
}
