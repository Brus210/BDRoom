package com.example.basededatos.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.basededatos.model.Exercises;

import java.util.List;

@Dao
public interface ExercisesDAO {
    @Insert
    void insert(Exercises excercise);

    @Update
    void update(Exercises excercise);

    @Delete
    void delete(Exercises excercise);

    @Query("Select * from exercises")
    List<Exercises> getAll();


    @Query("Select * from exercises where id in (:id)")
    Exercises findById(int id);

    @Query("Select * from exercises where id in (:ids)")
    List<Exercises> loadAllByIds(int[] ids);

    @Query("Select * from exercises where name like :name limit 1")
    Exercises findByName(String name);


}
