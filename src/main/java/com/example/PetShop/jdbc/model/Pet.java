package com.example.PetShop.jdbc.model;

public class Pet {

    private int id;
    private String name;
    private String breed;
    private String date_created;
    private String date_modified;

    public Pet() {
    }

    public Pet(int id, String name, String breed, String date_created, String date_modified) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.date_created = date_created;
        this.date_modified = date_modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", date_created='" + date_created + '\'' +
                ", date_modified='" + date_modified + '\'' +
                '}';
    }
}
