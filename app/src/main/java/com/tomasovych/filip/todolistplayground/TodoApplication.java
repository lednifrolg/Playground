package com.tomasovych.filip.todolistplayground;

import android.app.Application;
import com.tomasovych.filip.todolistplayground.di.AppComponent;
import com.tomasovych.filip.todolistplayground.di.AppModule;
import com.tomasovych.filip.todolistplayground.di.DaggerAppComponent;

public class TodoApplication extends Application {

  private AppComponent appComponent;

  public AppComponent getAppComponent() {
    return appComponent;
  }

  protected AppComponent initDagger(TodoApplication application) {
    return DaggerAppComponent.builder()
        .appModule(new AppModule(application))
        .build();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = initDagger(this);
  }
}
