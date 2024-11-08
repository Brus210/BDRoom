package com.example.basededatos.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "exercises")
public class Exercises implements Serializable {

    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id")
    private int id;

    @ColumnInfo (name = "name")
    private String name;

    @ColumnInfo (name = "muscle")
    private String muscle;

    @ColumnInfo (name = "description")
    private String description;

    /*public Exercises() { }
    public Exercises(String nombre, String muscle, String description, String image, int trainer_id) {
        this.name = nombre;
        this.muscle = muscle;
        this.description = description;

    }*/

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

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Constructor vacío
    public Exercises() {
        // Constructor vacío para instanciación sin argumentos
    }




}