package com.bhanuka.tms.repository;

import com.bhanuka.tms.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select b from Task b where b.status like :key")
    List<Task> searchByStatusMemberId(@Param("key") String title);
}
