package com.tomasovych.filip.todolistplayground.di.components;

import com.tomasovych.filip.todolistplayground.di.annotations.PerActivity;
import com.tomasovych.filip.todolistplayground.di.modules.ActivityModule;
import com.tomasovych.filip.todolistplayground.tasks.TasksActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)

public interface ActivityComponent {

  void inject(TasksActivity activity);

}
