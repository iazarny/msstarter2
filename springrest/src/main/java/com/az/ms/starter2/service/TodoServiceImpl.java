package com.az.ms.starter2.service;

import com.az.model.TodoEntiry;
import com.az.ms.starter2.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoServiceImpl  implements TodoService {

    @Autowired
    private TodoDao todoDao;

    public List<TodoEntiry> getAllTodo() {
        List<TodoEntiry> rz = todoDao.findAll();
        if (rz.isEmpty()) {
            createTodo(new TodoEntiry("Have", false));
            createTodo(new TodoEntiry(" some", true));
            createTodo(new TodoEntiry("  fun", false));
        }
        return todoDao.findAll();
    }

    public TodoEntiry getTodoById(Long todoId){
        return todoDao.findById(todoId).orElse(null);
    }

    @Transactional
    public TodoEntiry createTodo(TodoEntiry totoEntity) {
        return  todoDao.save(totoEntity);
    }

    @Transactional
    public TodoEntiry updateTodo(Long todoId, TodoEntiry todoEntity) {
        TodoEntiry found =this.getTodoById(todoId);
        if(found != null) {
            found.setTitle(todoEntity.getTitle());
            found.setCompleted(todoEntity.isCompleted());
            todoDao.save(found);
        }
        return null;
    }

    @Transactional
    public TodoEntiry deleteTodo(Long todoId){
        TodoEntiry article = getTodoById(todoId);
        if (article != null) {
            todoDao.deleteById(todoId);
        }
        return article;
    }

}
