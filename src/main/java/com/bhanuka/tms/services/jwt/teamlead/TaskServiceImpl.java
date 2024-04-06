package com.bhanuka.tms.services.jwt.teamlead;

import com.bhanuka.tms.dto.TaskDto;
import com.bhanuka.tms.entity.Task;
import com.bhanuka.tms.entity.User;
import com.bhanuka.tms.exception.ResourceNotFoundException;
import com.bhanuka.tms.repository.TaskRepository;
import com.bhanuka.tms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TaskDto assignTask(TaskDto taskDto) {
        User user = this.userRepository.findById(taskDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "user id", taskDto.getUserId()));

        Task task = this.modelMapper.map(taskDto, Task.class);

        task.setAssignedTo(user);

        Date dueDate = taskDto.getDueDate();
        task.setDueDate(dueDate);
        Task task1 = this.taskRepository.save(task);
        TaskDto createdTask = this.modelMapper.map(task1, TaskDto.class);
        return createdTask;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> taskList = this.taskRepository.findAll();

        List<TaskDto> taskdtoList = taskList.stream().map((task) ->  this.modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
        return taskdtoList;
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, Integer taskId) {
        Task updatetask = this.taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "task id", taskId));

        User user = this.userRepository.findById(taskDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "user id", taskDto.getUserId()));

        updatetask.setTitle(taskDto.getTitle());
        updatetask.setDescription(taskDto.getDescription());
        updatetask.setDueDate(taskDto.getDueDate());
        updatetask.setStatus(taskDto.getStatus());
        updatetask.setAssignedTo(user);

        Task updatedTask = this.taskRepository.save(updatetask);

        return this.modelMapper.map(updatedTask, TaskDto.class);
    }

    @Override
    public void deleteTask(Integer taskId) {
        Task deleteTask = this.taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "task id", taskId));

        this.taskRepository.delete(deleteTask);

    }

    @Override
    public TaskDto updatestatusTask(String status, Integer taskId) {
        Task updatestatustask = this.taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "task id", taskId));

        updatestatustask.setStatus(status);

        Task updatedTask = this.taskRepository.save(updatestatustask);

        return this.modelMapper.map(updatedTask, TaskDto.class);
    }

    @Override
    public List<TaskDto> searchTask(String keyword) {
        List<Task> tasks = this.taskRepository.searchByStatusMemberId("%" + keyword + "%");
        List<TaskDto> taskDtoList = tasks.stream().map((task) -> this.modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());

        return taskDtoList;
    }


}
