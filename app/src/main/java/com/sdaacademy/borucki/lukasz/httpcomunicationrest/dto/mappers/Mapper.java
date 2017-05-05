package com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto.mappers;

import com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto.TaskDTO;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Mapper {


    public static List<Task> fromTaskDTOToTask(List<TaskDTO> taskDTOList) {
        List<Task> resultList = new ArrayList<>();
        for (TaskDTO taskDTO : taskDTOList) {
            resultList.add(new Task(taskDTO.isCompleted(), taskDTO.getId(), taskDTO.getValue()));
        }
        return resultList;
    }

    public static List<Task> fromTaskDTOToTask(TaskDTO taskDTO) {
        List<Task> resultList = new ArrayList<>();

        resultList.add(new Task(taskDTO.isCompleted(), taskDTO.getId(), taskDTO.getValue()));

        return resultList;
    }

    public static TaskDTO fromTaskToTaskDTO(Task task) {

        return new TaskDTO(task.isCompleted(), 5L, task.getId(), task.getValue());
    }
}
