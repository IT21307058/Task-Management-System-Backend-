package com.bhanuka.tms.dto;

import com.bhanuka.tms.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {
    private Integer id;
    private String title;
    private String description;
    private Date dueDate;
    private String status;
    private Long userId;
}
