package com.az.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private String value;

    @ApiModelProperty(value = "Done", required = true)
    private boolean done;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public TodoEntiry(String value, boolean done) {
        this.value = value;
        this.done = done;
    }

    public TodoEntiry() {
    }
}
