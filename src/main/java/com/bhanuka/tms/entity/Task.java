package com.bhanuka.tms.entity;


import com.bhanuka.tms.dto.TaskDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    // create large string variable
    @Lob
    private String description;

    private Date dueDate;
    private String status;

    @OneToOne
    @JoinColumn(name = "member_id")
    private User assignedTo;

    public TaskDto getDto(){
        TaskDto taskDto = new TaskDto();

        taskDto.setId(id);
        taskDto.setTitle(title);
        taskDto.setDescription(description);
        taskDto.setDueDate(dueDate);
        taskDto.setUserId(assignedTo.getId());

        return taskDto;

    }

}
