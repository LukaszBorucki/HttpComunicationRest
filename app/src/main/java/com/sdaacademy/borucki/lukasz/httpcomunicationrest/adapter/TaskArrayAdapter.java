package com.sdaacademy.borucki.lukasz.httpcomunicationrest.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sdaacademy.borucki.lukasz.httpcomunicationrest.R;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto.TaskDTO;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.dto.mappers.Mapper;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.model.Task;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class TaskArrayAdapter extends ArrayAdapter<Task> {

    public TaskArrayAdapter(@NonNull Context context, @NonNull List<Task> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Task task = getItem(position);
        Task item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row, parent, false);

        }


        TextView itemNo = (TextView) convertView.findViewById(R.id.singleRowNo);
        TextView itemText = (TextView) convertView.findViewById(R.id.singleRowText);
        CheckBox itemCheckBox = (CheckBox) convertView.findViewById(R.id.checkBox);

        itemNo.setText(String.valueOf(position + 1));
        itemText.setText(item.getValue());
        itemCheckBox.setChecked(item.isCompleted());
        itemCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        return convertView;
    }

    private class PostAsyncTask extends AsyncTask<Void, Void, TaskDTO> {
        @Override
        protected TaskDTO doInBackground(Void... params) {


            String link = "https://shrouded-fjord-81597.herokuapp.com/api/task/";

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            TaskDTO[] result = restTemplate.getForObject(link, TaskDTO[].class);
//            return Arrays.asList(result);
            return null;


        }

        @Override
        protected void onPostExecute(TaskDTO tasksDto) {
//
//            tasks.addAll(Mapper.fromTaskDTOToTask(tasksDto));
//            arrayAdapter.notifyDataSetChanged();


        }


    }

}
