package com.community.jboss.leadmanagement.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.jboss.leadmanagement.R;

import java.util.ArrayList;

public class RecordingsListAdapter extends ArrayAdapter<String> {

    private Activity context;
    private ArrayList<String> recordingIDs;
    private ArrayList<String> dates;
    private ArrayList<String> filePaths;
    private final String AUDIO_RECORDER_FOLDER = "Lead Management Recordings";

    public RecordingsListAdapter(Activity context, ArrayList<String> recordingIDs, ArrayList<String> dates, ArrayList<String> filePaths) {
        super(context, R.layout.recorded_file_list_item, recordingIDs);

        this.context=context;
        this.recordingIDs = recordingIDs;
        this.dates = dates;
        this.filePaths = filePaths;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.recorded_file_list_item, null,true);

        TextView recordingText = rowView.findViewById(R.id.tv_recn);
        TextView dateText = rowView.findViewById(R.id.tv_date);
        ImageView playRec = rowView.findViewById(R.id.im_play);

        recordingText.setText(recordingIDs.get(position));
        dateText.setText(dates.get(position));
        playRec.setOnClickListener(view1 -> {
            String filePath = Environment.getExternalStorageDirectory().getPath()+"/"+AUDIO_RECORDER_FOLDER;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(filePath+"/"+filePaths.get(position)));
            intent.setDataAndType(Uri.parse(filePath+"/"+filePaths.get(position)), "video/mp4");
            context.startActivity(intent);
        });

        return rowView;

    };

}