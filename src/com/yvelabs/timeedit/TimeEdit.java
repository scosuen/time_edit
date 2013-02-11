package com.yvelabs.timeedit;

import java.util.regex.Pattern;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeEdit extends LinearLayout {
	private EditText hourET;
	private TextView colonTV1;
	private EditText minuteET;
	private TextView colonTV2;
	private EditText secondET;

	private Context context;

	public TimeEdit(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public TimeEdit(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public TimeEdit(Context context) {
		super(context);
		this.context = context;
		init();
	}

	private void init() {
		setOrientation(HORIZONTAL);
		
		hourET = new EditText(context);
		minuteET = new EditText(context);
		secondET = new EditText(context);
		colonTV1 = new TextView(context);
		colonTV2 = new TextView(context);
		
		addView(hourET);
		addView(colonTV1);
		addView(minuteET);
		addView(colonTV2);
		addView(secondET);

		hourET.setText("00");
		colonTV1.setText(":");
		minuteET.setText("00");
		colonTV2.setText(":");
		secondET.setText("00");
		
		hourET.setSingleLine();
		minuteET.setSingleLine();
		secondET.setSingleLine();
		
		minuteET.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
		secondET.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
		
		hourET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == false) {
					String str = ((EditText)v).getText().toString();
					hourET.setText(hoursValidate(str));
				}
			}
		});
		
		minuteET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == false) {
					String str = ((EditText)v).getText().toString();
					minuteET.setText(minuteValidate(str));
				}
			}
		});
		
		secondET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus == false) {
					String str = ((EditText)v).getText().toString();
					secondET.setText(secondsValidate(str));
				}
			}
		});
		
		secondET.setKeyListener(new NumberKeyListener() {
		    @Override
		    protected char[] getAcceptedChars() {
		        return new char[] { '1', '2', '3', '4', '5', '6', '7', '8','9', '0' };
		    }
		    @Override
		    public int getInputType() {
		        return android.text.InputType.TYPE_CLASS_PHONE;
		    }
		});
		
		minuteET.setKeyListener(new NumberKeyListener() {
		    @Override
		    protected char[] getAcceptedChars() {
		        return new char[] { '1', '2', '3', '4', '5', '6', '7', '8','9', '0' };
		    }
		    @Override
		    public int getInputType() {
		        return android.text.InputType.TYPE_CLASS_PHONE;
		    }
		});
		
		hourET.setKeyListener(new NumberKeyListener() {
		    @Override
		    protected char[] getAcceptedChars() {
		        return new char[] { '1', '2', '3', '4', '5', '6', '7', '8','9', '0' };
		    }
		    @Override
		    public int getInputType() {
		        return android.text.InputType.TYPE_CLASS_PHONE;
		    }
		});
		
	}
	
	private String timeValidate (String timeStr) {
		if (timeStr == null || timeStr.length() <= 0)
			return "00";
		if (!isNumeric(timeStr))
			return "00";
		
		int time = Integer.parseInt(timeStr);
		if (time <= 0)
			return "00";
		
		return timeStr;
	}

	private String hoursValidate(String hoursStr) {
		String tempStr = timeValidate(hoursStr);
		if (!tempStr.equals(hoursStr)) {
			return tempStr;
		}
		
		int hours = Integer.parseInt(hoursStr);
		if (hours < 10) {
			return "0" + hours;
		}
		
		return hoursStr;
	}
	
	private String minuteValidate (String minuteStr) {
		String tempStr = timeValidate(minuteStr);
		if (!tempStr.equals(minuteStr)) {
			return tempStr;
		}
		
		int minutes = Integer.parseInt(minuteStr);
		if (minutes < 10) {
			return "0" + minutes;
		}
		
		if (minutes > 59) {
			return "59";
		}
		
		return minuteStr;
	}
	
	private String secondsValidate (String secondStr) {
		String tempStr = timeValidate(secondStr);
		if (!tempStr.equals(secondStr)) {
			return tempStr;
		}
		
		int secondes = Integer.parseInt(secondStr);
		if (secondes < 10) {
			return "0" + secondes;
		}
		
		if (secondes > 59) {
			return "59";
		}
		
		return secondStr;
	}

	public long getTime() {
		int hours = Integer.parseInt(hourET.getText().toString());
		int minutes = Integer.parseInt(minuteET.getText().toString());
		int seconds = Integer.parseInt(secondET.getText().toString());

		return new Long(hours) * 60 * 60 * 1000 + new Long(minutes) * 60 * 1000 + new Long(seconds) * 1000;
	}

	public int getHours() {
		return Integer.parseInt(hourET.getText().toString());
	}

	public int getMinutes() {
		return Integer.parseInt(minuteET.getText().toString());
	}

	public int getSeconds() {
		return Integer.parseInt(secondET.getText().toString());
	}
	
	public void setHours(int hours) {
		String hourStr = hoursValidate(hours + "");
		hourET.setText(hourStr);
	}
	public void setMinutes (int minutes) {
		String minutesStr = minuteValidate(minutes + "");
		minuteET.setText(minutesStr);
	}
	public void setSeconds (int seconds) {
		String secondsStr = secondsValidate(seconds + "");
		secondET.setText(secondsStr);
	}
	
	public void setTextSize (float size) {
		hourET.setTextSize(size);
		colonTV1.setTextSize(size);
		minuteET.setTextSize(size);
		colonTV2.setTextSize(size);
		secondET.setTextSize(size);
	}
	
	public void setTypeFace (Typeface typeFace) {
		hourET.setTypeface(typeFace);
		colonTV1.setTypeface(typeFace);
		minuteET.setTypeface(typeFace);
		colonTV2.setTypeface(typeFace);
		secondET.setTypeface(typeFace);
	}
	
	public void setTextColor (int color) {
		hourET.setTextColor(color);
		colonTV1.setTextColor(color);
		minuteET.setTextColor(color);
		colonTV2.setTextColor(color);
		secondET.setTextColor(color);
	}
	
	public void setTextColor (ColorStateList color) {
		hourET.setTextColor(color);
		colonTV1.setTextColor(color);
		minuteET.setTextColor(color);
		colonTV2.setTextColor(color);
		secondET.setTextColor(color);
	}
	
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
}
