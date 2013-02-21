package com.yvelabs.timeedit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.yvelabs.timeedit.R;
import com.yvelabs.timeedit.TimeEdit;

public class MainActivity extends Activity {
	
	private TimeEdit timeEdit;
	private Button getTimeBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        timeEdit = (TimeEdit) findViewById(R.id.timeEdit1);
        getTimeBut = (Button) findViewById(R.id.get_time_but);
        
        /*timeEdit.setHours(hours);
        timeEdit.setMinutes(minutes);
        timeEdit.setSeconds(seconds);
        
        timeEdit.getHours();
        timeEdit.getMinutes();
        timeEdit.setSeconds(seconds);
        
        timeEdit.setTextSize(size);
        timeEdit.setTextColor(color);
        timeEdit.setTypeFace(typeFace);*/
        
        getTimeBut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("sun_yve", "getTime:" + timeEdit.getTime());
			}
		});
    }
}
