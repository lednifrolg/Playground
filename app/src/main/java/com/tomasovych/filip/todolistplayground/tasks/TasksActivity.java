package com.tomasovych.filip.todolistplayground.tasks;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tomasovych.filip.todolistplayground.R;
import com.tomasovych.filip.todolistplayground.TodoApplication;
import com.tomasovych.filip.todolistplayground.base.BaseActivity;
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

  @Inject
  TasksPresenter tasksPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    ((TodoApplication) getApplication()).getAppComponent().inject(this);
    setSupportActionBar(mToolbar);
    tasksPresenter.attachView(this);

    mAddTaskFab.setOnClickListener(
        v -> Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_SHORT)
            .setAction("Action", view -> tasksPresenter.buttonClicked()).show());

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
  }

  @Override
  public void showMessage(String message) {
    Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    Log.d(TAG, "showMessage: ");
  }
}
