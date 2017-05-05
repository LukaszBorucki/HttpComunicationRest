package com.sdaacademy.borucki.lukasz.httpcomunicationrest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.adapter.TaskArrayAdapter;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto.TaskDTO;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto.mappers.Mapper;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.model.Task;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private List<Task> tasks;
    private ArrayAdapter<Task> arrayAdapter;
    ListView listView;
    @BindView(R.id.mainActivityTaskTODOEditText)
    EditText newToDoTaskText;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listView = (ListView) findViewById(R.id.mainActivityListView);
        tasks = new ArrayList<>();
        arrayAdapter = new TaskArrayAdapter(this, tasks);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new DeleteAsyncTask().execute(Mapper.fromTaskToTaskDTO(tasks.get(position)));
                return false;
            }
        });

        new GetAllAsyncTask().execute();
    }

    @OnClick(R.id.mainActivityNewTask)
    void addNewTask() {
        message = newToDoTaskText.getText().toString();
               new PutAsyncTask().execute();




    }


    private class PutAsyncTask extends AsyncTask<String, Void, TaskDTO> {
        @Override
        protected TaskDTO doInBackground(String... params) {
            String url = "https://shrouded-fjord-81597.herokuapp.com/api/task";
            TaskDTO taskDTO = new TaskDTO(false, 5L, message);
            Gson gson = new Gson();
            String requestBody = gson.toJson(taskDTO);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> entity = new HttpEntity<Object>(requestBody, headers);
            ResponseEntity<TaskDTO> response = restTemplate.exchange(url, HttpMethod.PUT, entity, TaskDTO.class);
            int statusCode = response.getStatusCode().value();
            TaskDTO responseBody = response.getBody();
//                        Log.d("TAG", requestBody.toString());

            return null;
        }


        @Override
        protected void onPostExecute(TaskDTO taskDTO) {
            new GetAllAsyncTask().execute();

        }


    }


    private class GetAllAsyncTask extends AsyncTask<Void, Void, List<TaskDTO>> {
        @Override
        protected List<TaskDTO> doInBackground(Void... params) {

            String link = "https://shrouded-fjord-81597.herokuapp.com/api/task/all/5";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            TaskDTO[] result = restTemplate.getForObject(link, TaskDTO[].class);
            return Arrays.asList(result);


        }

        @Override
        protected void onPostExecute(List<TaskDTO> tasksDto) {
            tasks.clear();
            tasks.addAll(Mapper.fromTaskDTOToTask(tasksDto));
            arrayAdapter.notifyDataSetChanged();


        }


    }

    private class DeleteAsyncTask extends AsyncTask<TaskDTO, Void, List<TaskDTO>> {
        @Override
        protected List<TaskDTO> doInBackground(TaskDTO... params) {
            TaskDTO taskDTO = params[0];
            String url = "https://shrouded-fjord-81597.herokuapp.com/api/task/" + taskDTO.getId();
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.delete(url);

            return null;


        }

        @Override
        protected void onPostExecute(List<TaskDTO> tasksDto) {
            new GetAllAsyncTask().execute();
//            tasks.addAll(Mapper.fromTaskDTOToTask(tasksDto));
//            arrayAdapter.notifyDataSetChanged();


        }


    }


    private Task getDataFromJason(String s) {
        Gson gson = new Gson();
        TaskDTO taskDTO = gson.fromJson(s, TaskDTO.class);


        return new Task(taskDTO.isCompleted(), taskDTO.getId(), taskDTO.getValue());
    }
}
