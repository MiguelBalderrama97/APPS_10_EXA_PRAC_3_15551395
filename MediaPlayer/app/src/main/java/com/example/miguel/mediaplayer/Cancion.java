package com.example.miguel.mediaplayer;

public class Cancion {

    private int resource, image;
    private String name;

    public  Cancion(){}

    public Cancion(int resource, int image, String name) {
        this.resource = resource;
        this.image = image;
        this.name = name;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
