package com.example.basededatos.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basededatos.R;
import com.example.basededatos.model.Exercises;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExercisesListAdapter extends RecyclerViewAdapter<ExercisesListAdapter.MyViewHolder> {
    private Context context;
    private List<Exercises> exercisesList;
    private ElementClickListener elementClickListener;

    public ExercisesListAdapter(Context context, List<Exercises> exercisesList, ElementClickListener elementClickListener) {
        this.context = context;
        this.exercisesList = exercisesList;
        this.elementClickListener = elementClickListener;
    }

    public void setExercisesList(List<Exercises> exercisesList) {
        this.exercisesList = exercisesList;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ExercisesListAdapter .MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.renglon_exercises, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull ExercisesListAdapter.MyViewHolder holder, int position){
        holder.txtId.setText(String.valueOf((this.exercisesList.get(position)).getId()));
        holder.txtName.setText(String.valueOf((this.exercisesList.get(position)).getName()));
        holder.txtMuscle.setText(String.valueOf((this.exercisesList.get(position)).getMuscle()));
        holder.txtDescription.setText(String.valueOf((this.exercisesList.get(position)).getDescription()));

        holder.itemView.setOnClickListener(new Viev.OnClickListener(){
            @Override
            public void onClick(View v){
                elementClickListener.onBtnElementClick(exercisesList.get(position));
            }
        });
    }

    @Override
    public int getItemCount(){
        if(this.exercisesList !=null){
            return this.exercisesList.size();
        }
        return 0;
    }

    public class MyVViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtName, txtMuscle, txtDescription;
        private Button btnHecho;
        public MyVViewHolder(@NonNull View itemView){
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
            txtMuscle = itemView.findViewById(R.id.txtMuscle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            btnHecho = itemView.findViewById(R.id.btnHecho);
        }
    }

    //clase que obliga al adaptador del componente
    public interface ElementClickListener{
        void onElementClick(Exercises exercises);
        void onBtnElementClick(Exercises exercises);

    }
}
