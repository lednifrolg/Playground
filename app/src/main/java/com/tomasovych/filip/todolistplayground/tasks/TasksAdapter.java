package com.tomasovych.filip.todolistplayground.tasks;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tomasovych.filip.todolistplayground.R;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksItemView;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksListAdapterView;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksListPresenter;
import javax.inject.Inject;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> implements
    TasksListAdapterView {

  private TasksListPresenter tasksListPresenter;
  private int listSize = 0;

  @Inject
  public TasksAdapter(TasksListPresenter tasksItemPresenter) {
    this.tasksListPresenter = tasksItemPresenter;
    tasksItemPresenter.attachView(this);
  }

  @Override
  public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    super.onDetachedFromRecyclerView(recyclerView);
    tasksListPresenter.detachView();
  }

  @Override
  public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.todo_item, parent, false);

    return new TasksViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(TasksViewHolder holder, int position) {
    tasksListPresenter.onBindTasksItemView(holder, position);
  }

  @Override
  public int getItemCount() {
    return listSize;
  }

  @Override
  public void setListSize(int listSize) {
    this.listSize = listSize;
  }

  @Override
  public void notifyDataChanged() {
    this.notifyDataSetChanged();
  }

  public class TasksViewHolder extends ViewHolder implements TasksItemView {

    @BindView(R.id.tv_todo)
    TextView taskTextView;

    @BindView(R.id.tv_task_description)
    TextView taskDescriptionTextView;

    @BindView(R.id.iv_task_badge)
    ImageView taskBadgeImageView;


    public TasksViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @Override
    public void setItemName(String itemName) {
      taskTextView.setText(itemName);
    }

    @Override
    public void setItemDescription(String description) {
      taskDescriptionTextView.setText(description);
    }

    @Override
    public void setItemColor(String color) {
      taskBadgeImageView.getDrawable().setColorFilter(Color.parseColor(color), Mode.SRC);
    }

    @Override
    public void setPriority(int priority) {

    }

    @Override
    public void setState() {

    }
  }

}
