package com.bhanuka.tms.controller;


import com.bhanuka.tms.dto.ApiResponse;
import com.bhanuka.tms.dto.TaskDto;
import com.bhanuka.tms.services.jwt.teamlead.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/")
    public ResponseEntity<TaskDto> assignTeamMember(@RequestBody TaskDto taskDto){
        TaskDto createdTask = this.taskService.assignTask(taskDto);

        return new ResponseEntity<TaskDto>(createdTask, HttpStatus.CREATED);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTaskDetails(@RequestBody TaskDto taskDto, @PathVariable("taskId") Integer taskId){
        TaskDto updatetaskDto = this.taskService.updateTask(taskDto, taskId);

        return new ResponseEntity<TaskDto>(updatetaskDto, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> getAllTask(){
        List<TaskDto> taskDtoList = this.taskService.getAllTasks();

        return new ResponseEntity<List<TaskDto>>(taskDtoList, HttpStatus.OK);
    }

    @DeleteMapping ("/{taskId}")
    ResponseEntity<ApiResponse> deleteTask(@PathVariable("taskId") Integer taskId){
        this.taskService.deleteTask(taskId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Task Deleted Successfully", true), HttpStatus.OK);
    }

    @PatchMapping("/status/{taskId}")
    public ResponseEntity<TaskDto> updatestatusTask(@RequestBody String status, @PathVariable("taskId") Integer taskId){
        TaskDto updatetaskDto = this.taskService.updatestatusTask(status, taskId);

        return new ResponseEntity<TaskDto>(updatetaskDto, HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<TaskDto>> searchTask(@PathVariable String keywords){
        List<TaskDto> taskDtoList = this.taskService.searchTask(keywords);

        return new ResponseEntity<List<TaskDto>>(taskDtoList, HttpStatus.OK);
    }
}
