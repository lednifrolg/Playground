package com.tomasovych.filip.todolistplayground.tasks;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.tomasovych.filip.todolistplayground.R;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksItemPresenter;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksItemView;
import javax.inject.Inject;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

  @Inject
  TasksItemPresenter tasksItemPresenter;

  @Override
  public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(TasksViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class TasksViewHolder extends ViewHolder implements TasksItemView {

    @BindView(R.id.tv_todo)
    TextView todoTextView;

    public TasksViewHolder(View itemView) {
      super(itemView);
    }

    @Override
    public void setItemName(String itemName) {

    }

    @Override
    public void setPriority(int priority) {

    }

    @Override
    public void setState() {

    }
  }

}
