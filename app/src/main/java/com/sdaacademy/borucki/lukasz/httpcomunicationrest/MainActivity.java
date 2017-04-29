package com.sdaacademy.borucki.lukasz.httpcomunicationrest;

import android.os.AsyncTask;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.adapter.TaskArrayAdapter;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto.TaskDTO;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto.mappers.Mapper;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.model.Task;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private List<Task> tasks;
    private ArrayAdapter<Task> arrayAdapter;
    @BindView(R.id.mainActivityListView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tasks = new ArrayList<>();
        arrayAdapter = new TaskArrayAdapter(this, tasks);
        listView.setAdapter(arrayAdapter);
        new GetAllAsyncTask().execute();
    }


    private class PutAsyncTask extends AsyncTask<Void, Void, TaskDTO> {
        @Override
        protected void onPostExecute(TaskDTO taskDTO) {
            super.onPostExecute(taskDTO);
        }

        @Override
        protected TaskDTO doInBackground(Void... params) {
            return null;
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

            tasks.addAll(Mapper.fromTaskDTOToTask(tasksDto));
            arrayAdapter.notifyDataSetChanged();


        }


    }

    private Task getDataFromJason(String s) {
        Gson gson = new Gson();
        TaskDTO taskDTO = gson.fromJson(s, TaskDTO.class);


        return new Task(taskDTO.isCompleted(), taskDTO.getId(), taskDTO.getValue());
    }
}
