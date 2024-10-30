package com.example.basededatos.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.basededatos.model.Exercises;

import java.util.List;

public class ListaExercisesViewModel extends ViewModel {
    private MutableLiveData<List<Exercises>> lista;
    public ListaExercisesViewModel(){this.lista = new MutableLiveData<List<Exercises>>();}

    public LiveData<List<Exercises>> getLista(){return lista;}

    public void setExercises (List<Exercises> lista ){this.lista.setValue(lista);}
}
