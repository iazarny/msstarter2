package com.az.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "article")

@EntityListeners(AuditingEntityListener.class)

@JsonIgnoreProperties(value = {"modified"}, allowGetters = true)

@ApiModel

@DynamicUpdate
public class TodoEntiry  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Id", required = false)
    private Long id;

    @ApiModelProperty(value = "Todo", required = true)
    private String title;

    @ApiModelProperty(value = "Done", required = true)
    private boolean completed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public TodoEntiry(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public TodoEntiry() {
    }
}
