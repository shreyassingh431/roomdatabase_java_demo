package com.example.demoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<User> taskList;

    public UserAdapter(Context mCtx, List<User> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_user, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        User t = taskList.get(position);
        holder.textViewLogin.setText(t.getEmailId());
        holder.textViewPswd.setText(t.getPassword());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder {

        TextView  textViewLogin, textViewPswd;

        public TasksViewHolder(View itemView) {
            super(itemView);

            textViewLogin = itemView.findViewById(R.id.login);
            textViewPswd = itemView.findViewById(R.id.pswd);


        }

    }
}