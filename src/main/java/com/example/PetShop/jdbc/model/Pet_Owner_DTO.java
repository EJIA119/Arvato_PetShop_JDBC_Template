package com.example.PetShop.jdbc.model;

public class Pet_Owner_DTO {

    Pet pet;
    Owner owner;

    public Pet_Owner_DTO() {
    }

    public Pet_Owner_DTO(Pet pet, Owner owner) {
        this.pet = pet;
        this.owner = owner;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet_Owner_DTO{" +
                "pet=" + pet +
                ", owner=" + owner +
                '}';
    }
}
