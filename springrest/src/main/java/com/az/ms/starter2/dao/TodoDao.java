package com.az.ms.starter2.dao;


import com.az.model.TodoEntiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoDao  extends JpaRepository<TodoEntiry, Long> {
}
