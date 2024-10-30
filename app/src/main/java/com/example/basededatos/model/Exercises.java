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
    private String nombre;

    @ColumnInfo (name = "muscle")
    private String muscle;

    @ColumnInfo (name = "description")
    private String description;

    @ColumnInfo (name = "image")
    private String image;

    @ColumnInfo (name = "trainer_id")
    private int trainer_id;

    public Exercises() { }
    public Exercises(String nombre, String muscle, String description, String image, int trainer_id) {
        this.nombre = nombre;
        this.muscle = muscle;
        this.description = description;
        this.image = image;
        this.trainer_id = trainer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }






}