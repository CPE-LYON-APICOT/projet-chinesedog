package com.example.chinesedog.Model;

import com.example.chinesedog.TypeCase;

public class Case {
    private TypeCase type;
    private int x;
    private int y;
    private boolean isOccupied;

    public Case(int x, int y, TypeCase type, boolean isOccupied) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.isOccupied = isOccupied;
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
    public boolean getIsOccupied() {
        return isOccupied;
    }
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
}
