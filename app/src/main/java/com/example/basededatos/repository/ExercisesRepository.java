package com.example.basededatos.repository;
import com.example.basededatos.database.ExercisesDAO;
import com.example.basededatos.model.Exercises;

import java.util.List;


public class ExercisesRepository implements ExercisesDAO {
    private ExercisesDAO exercisesDAO;
    public ExercisesRepository(ExercisesDAO exercisesDAO) {
        this.exercisesDAO = exercisesDAO;
    }

    @Override
    public List<Exercises> getAll() {
        return exercisesDAO.getAll();
    }

    @Override
    public Exercises findById(int id) {
        return exercisesDAO.findById(id);
    }

    @Override
    public List<Exercises> loadAllByIds(int[] ids) {
        return exercisesDAO.loadAllByIds(ids);
    }

    @Override
    public Exercises findByName(String name) {
        return exercisesDAO.findByName(name);
    }

    @Override
    public void insert(Exercises excercise) {
        exercisesDAO.insert(excercise);
    }

    @Override
    public void update(Exercises excercise) {
        exercisesDAO.update(excercise);
    }

    @Override
    public void delete(Exercises excercise) {
        exercisesDAO.delete(excercise);
    }

}
