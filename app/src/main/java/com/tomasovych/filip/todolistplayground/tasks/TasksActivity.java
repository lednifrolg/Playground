package com.tomasovych.filip.todolistplayground.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.tomasovych.filip.todolistplayground.newtask.CreateTaskActivity;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksPresenter;
import com.tomasovych.filip.todolistplayground.tasks.TasksContract.TasksView;
import javax.inject.Inject;

public class TasksActivity extends BaseActivity implements TasksView,
    OnNavigationItemSelectedListener {

  public static final String TAG = TasksActivity.class.getSimpleName();

  @BindView(R.id.fab_add_task)
  FloatingActionButton mAddTaskFab;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.drawer_layout)
  DrawerLayout drawer;

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

    setSupportActionBar(toolbar);

    initNavDrawer();
    initTasksRecyclerView();

    tasksPresenter.attachView(this);

    mAddTaskFab.setOnClickListener(view -> tasksPresenter.createTaskButtonClicked());
  }

  private void initNavDrawer() {
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  private void initTasksRecyclerView() {
    tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//    tasksRecyclerView.addItemDecoration(new DividerItemDecoration(tasksRecyclerView.getContext(),
//        LinearLayoutManager.VERTICAL));
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

  @Override
  public void startCreateTask() {
    Intent intent = new Intent(this, CreateTaskActivity.class);
    startActivity(intent);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
