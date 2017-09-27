package com.tomasovych.filip.todolistplayground.di;

import com.tomasovych.filip.todolistplayground.tasks.TasksActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {

  void inject(TasksActivity target);
}
