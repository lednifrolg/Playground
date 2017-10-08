package com.tomasovych.filip.todolistplayground;

import static com.tomasovych.filip.todolistplayground.tasks.TasksActivity.TAG;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.squareup.leakcanary.LeakCanary;
import com.tomasovych.filip.todolistplayground.di.components.AppComponent;
import com.tomasovych.filip.todolistplayground.di.components.DaggerAppComponent;
import com.tomasovych.filip.todolistplayground.di.modules.AppModule;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import javax.inject.Inject;

public class TodoApplication extends Application {

  @Inject
  public TaskRepository taskRepository;
  protected AppComponent appComponent;

  public static TodoApplication get(Context context) {
    return (TodoApplication) context.getApplicationContext();
  }

  public AppComponent getComponent() {
    return appComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "onCreate: ");

    appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
    appComponent.inject(this);

    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
  }
}
