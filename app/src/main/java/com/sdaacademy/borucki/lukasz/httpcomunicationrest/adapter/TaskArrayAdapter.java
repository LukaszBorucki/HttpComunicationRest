package com.sdaacademy.borucki.lukasz.httpcomunicationrest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sdaacademy.borucki.lukasz.httpcomunicationrest.R;
import com.sdaacademy.borucki.lukasz.httpcomunicationrest.model.Task;

import java.util.List;

public class TaskArrayAdapter extends ArrayAdapter<Task> {

    public TaskArrayAdapter(@NonNull Context context, @NonNull List<Task> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row, parent, false);
        }

        TextView itemNo = (TextView) convertView.findViewById(R.id.singleRowNo);
        TextView itemText = (TextView) convertView.findViewById(R.id.singleRowText);
        CheckBox itemCheckBox = (CheckBox) convertView.findViewById(R.id.checkBox);

        itemNo.setText(String.valueOf(position+1));
        itemText.setText(item.getValue());
        itemCheckBox.setChecked(item.isCompleted());
        return convertView;
    }


}
