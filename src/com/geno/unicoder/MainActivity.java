package com.geno.unicoder;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.text.*;

public class MainActivity extends Activity
{
	public EditText in, out;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		in = (EditText) findViewById(R.id.in);
		out = (EditText) findViewById(R.id.out);
		in.setHint("Hex without 0x prefix here. For example 41.");
		TextWatcher t = new TextWatcher()
		{

			@Override
			public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
			{
				
			}

			@Override
			public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
			{
				String unicode = in.getText().toString();
				int cp;
				try
				{
					cp = Integer.valueOf(unicode, 16);
				}
				catch(Exception e)
				{cp = 32;}
				char output = Character.toChars(cp)[0];
				out.setText(String.valueOf(output));
			}

			@Override
			public void afterTextChanged(Editable p1)
			{
				
			}
		};
		in.addTextChangedListener(t);
	}
}
