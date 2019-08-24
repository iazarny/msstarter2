package com.az.ms.starter2.service;

import com.az.model.TodoEntiry;

import java.util.List;

public interface TodoService {


     List<TodoEntiry> getAllTodo();

     TodoEntiry createTodo(TodoEntiry article);

     TodoEntiry getTodoById(Long articleId);

     TodoEntiry updateTodo(Long articleId, TodoEntiry artDetail);

     TodoEntiry deleteTodo(Long articleId);
}
