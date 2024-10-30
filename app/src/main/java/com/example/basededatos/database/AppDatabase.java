package com.example.basededatos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.basededatos.model.Exercises;

@Database(entities = {Exercises.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;
    public abstract ExercisesDAO exercisesDAO();

    public static AppDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "gympro")
                    .fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
