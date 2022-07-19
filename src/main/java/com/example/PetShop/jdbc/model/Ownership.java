package com.example.PetShop.jdbc.model;

public class Ownership {

    private int pet_id;
    private int owner_id;

    public Ownership() {
    }

    public Ownership(int pet_id, int owner_id) {
        this.pet_id = pet_id;
        this.owner_id = owner_id;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "Ownership{" +
                "pet_id=" + pet_id +
                ", owner_id=" + owner_id +
                '}';
    }
}
