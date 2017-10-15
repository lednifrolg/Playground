package com.tomasovych.filip.todolistplayground.tasks;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tomasovych.filip.todolistplayground.R;
import com.tomasovych.filip.todolistplayground.TodoApplication;
import com.tomasovych.filip.todolistplayground.base.BaseActivity;
import com.tomasovych.filip.todolistplayground.di.components.ActivityComponent;
import com.tomasovych.filip.todolistplayground.di.components.DaggerActivityComponent;
import com.tomasovych.filip.todolistplayground.di.modules.ActivityModule;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksPresenter;
import javax.inject.Inject;

public class TasksActivity extends BaseActivity implements TasksContract.TasksView {

  public static final String TAG = TasksActivity.class.getSimpleName();

  @BindView(R.id.fab_add_task)
  FloatingActionButton mAddTaskFab;

  @BindView(R.id.toolbar)
  Toolbar mToolbar;

  @BindView(R.id.coordinatorLayour)
  CoordinatorLayout coordinatorLayout;

  @BindView(R.id.rv_tasks)
  RecyclerView tasksRecyclerView;

  @Inject
  TasksPresenter tasksPresenter;

  @Inject
  TasksAdapter tasksAdapter;

  private ActivityComponent activityComponent;

  private ActivityComponent getActivityComponent() {
    if (activityComponent == null) {
      Log.d(TAG, "getActivityComponent: null");
      activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
          .appComponent(TodoApplication.get(this).getComponent())
          .build();
    }
    return activityComponent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getActivityComponent().inject(this);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    tasksPresenter.attachView(this);

    initTasksRecyclerView();

    mAddTaskFab.setOnClickListener(
        v -> Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_SHORT)
            .setAction("Action", view -> tasksPresenter.buttonClicked()).show());
  }

  private void initTasksRecyclerView() {
    tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    tasksRecyclerView.addItemDecoration(new DividerItemDecoration(tasksRecyclerView.getContext(),
        LinearLayoutManager.VERTICAL));
    tasksRecyclerView.setAdapter(tasksAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    tasksPresenter.detachView();
    tasksRecyclerView.setAdapter(null);
  }

  @Override
  public void showMessage(String message) {
    Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    Log.d(TAG, "showMessage: ");
  }
}
