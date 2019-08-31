package com.az.ms.starter2.rest;


import com.az.model.TodoEntiry;
import com.az.ms.starter2.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Api("Simple to do CRUD REST endpoint")
public class TodoController {

    private static final Logger log = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    @ApiOperation("Get all todo items")
    public ResponseEntity<List<TodoEntiry>> listAllTodos() {
        log.info("Get all to do items");
        return new ResponseEntity<List<TodoEntiry>>(todoService.getAllTodo(), HttpStatus.OK);
    }

    @PostMapping("/todos")
    @ApiOperation("Create an todoitem")
    public TodoEntiry createTodo(@Valid @RequestBody TodoEntiry todoEntiry) {
        log.info("Create new to do item with title {}", todoEntiry.getId());
        return todoService.createTodo(todoEntiry);
    }


    @GetMapping("/todos/{id}")
    @ApiOperation("Get todo with specific ID")
    public ResponseEntity<TodoEntiry> getTddoById(@PathVariable(value = "id") Long todoId) {
        log.info("Get todo  by id {}", todoId);
        TodoEntiry td = todoService.getTodoById(todoId);
        if(td == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(td);
    }


    @PutMapping("/todos/{id}")
    @ApiOperation("Update todo with specific ID")
    public ResponseEntity<TodoEntiry> updateTodo(@PathVariable(value = "id") Long todoId,
                                                 @Valid @RequestBody TodoEntiry todoEntiryDetail) {
        log.info("Update to do by id {}", todoId);
        TodoEntiry todoEntiry = todoService.updateTodo(todoId, todoEntiryDetail);
        if(todoEntiry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todoEntiry);
    }



    @DeleteMapping("/todos/{id}")
    @ApiOperation("Delete todo by ID")
    public ResponseEntity<TodoEntiry> deleteArticle(@PathVariable(value = "id") Long todoId) {
        log.info("Delete article by id {}", todoId);
        TodoEntiry td = todoService.deleteTodo(todoId);
        if(td == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(td);
    }


}
