package com.example.helpingpaws.Model;

public class Pet {
    private String Name, Image, Description, PetID;

    public Pet()
    {
    }
    public Pet(String name, String image, String description, String petID)
    {
        Name=name;
        Image=image;
        Description=description;
        PetID=petID;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public String getPetID() {
        return PetID;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPetID(String petID) {
        PetID = petID;
    }
}
