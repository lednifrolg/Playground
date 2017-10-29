package com.tomasovych.filip.todolistplayground.newtask;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.lottie.LottieAnimationView;
import com.tomasovych.filip.todolistplayground.R;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.TaskItemsAdapterView;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.TaskItemsPresenter;
import com.tomasovych.filip.todolistplayground.newtask.CreateTasksContract.TaskItemsView;
import javax.inject.Inject;

public class TaskItemsAdapter extends
    RecyclerView.Adapter<TaskItemsAdapter.TaskItemsViewHolder> implements
    TaskItemsAdapterView {

  public static final String TAG = TaskItemsAdapter.class.getSimpleName();

  private TaskItemsPresenter taskItemsPresenter;
  private int listSize = 0;

  @Inject
  public TaskItemsAdapter(TaskItemsPresenter taskItemsPresenter) {
    this.taskItemsPresenter = taskItemsPresenter;
    taskItemsPresenter.attachView(this);
  }

  @Override
  public TaskItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.task_item, parent, false);

    return new TaskItemsViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(TaskItemsViewHolder holder, int position) {
    taskItemsPresenter.onBindTasksItemView(holder, position);
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

  @Override
  public void notifyTaskItemChanged(int position) {
    this.notifyItemChanged(position);
  }


  public class TaskItemsViewHolder extends ViewHolder implements TaskItemsView, OnClickListener {

    @BindView(R.id.tv_item_text)
    TextView taskItemTextView;

    @BindView(R.id.animation_view)
    LottieAnimationView checkedAnimationView;

    float progress = 0f;

    public TaskItemsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      itemView.setOnClickListener(this);
    }

    @Override
    public void setItemName(String itemName) {
      taskItemTextView.setText(itemName);
    }

    @Override
    public void setChecked(boolean checked) {
      if (checked) {
        progress = 1f;
      } else {
        progress = 0f;
      }

      checkedAnimationView.setProgress(progress);
    }

    @Override
    public void showAnimation() {
      Log.i(TAG, "showAnimation()");
      if (progress == 0f) {
        progress = 1f;
        checkedAnimationView.playAnimation();
      } else {
        progress = 0f;
        checkedAnimationView.cancelAnimation();
        checkedAnimationView.setProgress(progress);
      }
    }

    @Override
    public void setTag(int tag) {
      this.itemView.setTag(tag);
    }

    @Override
    public void onClick(View view) {
      showAnimation();
      taskItemsPresenter.itemClicked((Integer) view.getTag());
    }
  }

}
