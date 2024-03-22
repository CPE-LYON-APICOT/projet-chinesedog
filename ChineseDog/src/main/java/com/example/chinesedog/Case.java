package com.example.chinesedog;
public class Case {
    private TypeCase type;
    private int x;
    private int y;
    private String imagePath;

    public Case(int x, int y, TypeCase type, String imagePath) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.imagePath = imagePath;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TypeCase getType() {
        return type;
    }

    public String getImagePath() {
        return imagePath;
    }
}
