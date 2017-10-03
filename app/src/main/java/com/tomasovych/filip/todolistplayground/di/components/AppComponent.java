package com.tomasovych.filip.todolistplayground.di.components;

import com.tomasovych.filip.todolistplayground.TodoApplication;
import com.tomasovych.filip.todolistplayground.di.modules.AppModule;
import com.tomasovych.filip.todolistplayground.model.source.TaskRepository;
import com.tomasovych.filip.todolistplayground.model.source.local.TaskDatabase;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

  void inject(TodoApplication todoApplication);

  TaskRepository taskRepository();
}
