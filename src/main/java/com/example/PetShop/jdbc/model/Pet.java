package com.example.PetShop.jdbc.model;

public class Pet {

    private int id;
    private String name;
    private String breed;
    private String date_created;
    private String date_modified;
    private int owner_id;

    public Pet() {
    }

    public Pet(int id, String name, String breed, String date_created, String date_modified, int owner_id) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.date_created = date_created;
        this.date_modified = date_modified;
        this.owner_id = owner_id;
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

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", date_created='" + date_created + '\'' +
                ", date_modified='" + date_modified + '\'' +
                ", owner_id=" + owner_id +
                '}';
    }
}
