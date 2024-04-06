package com.bhanuka.tms.services.jwt.teamlead;

import com.bhanuka.tms.dto.TaskDto;
import com.bhanuka.tms.entity.Task;

import java.util.List;

public interface TaskService {

    TaskDto assignTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    TaskDto updateTask(TaskDto taskDto, Integer taskId);

    void deleteTask(Integer taskId);

    TaskDto updatestatusTask(String status, Integer taskId);

    List<TaskDto> searchTask(String keyword);
}
