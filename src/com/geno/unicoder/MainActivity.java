package com.geno.unicoder;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.text.*;

public class MainActivity extends Activity
{
	public EditText in, out;
	public ToggleButton s;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		in = (EditText) findViewById(R.id.in);
		out = (EditText) findViewById(R.id.out);
		s = (ToggleButton) findViewById(R.id.swi);
		in.setHint("Hex without 0x prefix here. For example 41.");
		TextWatcher indeal = new TextWatcher()
		{

			@Override
			public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
			{
			}

			@Override
			public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
			{
				if(s.getText().toString() == s.getTextOn())return;
				String unicode = in.getText().toString();
				int cp;
				char output;
				try
				{
					cp = Integer.valueOf(unicode, 16);
				}
				catch(Exception e)
				{
					cp = 32;
				}
				try
				{
					output = Character.toChars(cp)[0];
				}
				catch(Exception e)
				{
					output = ' ';
				}
				out.setText(String.valueOf(output));
			}

			@Override
			public void afterTextChanged(Editable p1)
			{
			}
		};
		TextWatcher outdeal = new TextWatcher()
		{

			@Override
			public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
			{
			}

			@Override
			public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
			{

				if(s.getText().toString() == s.getTextOff())return;
				String chara = out.getText().toString();
				int cp ;
				try
				{
					cp = Character.codePointAt(chara, 0);
				}
				catch(Exception e)
				{
					cp = 0;
				}
				String output = Integer.toHexString(cp).toUpperCase();
				in.setText(output);
			}

			@Override
			public void afterTextChanged(Editable p1)
			{
			}
		};
		in.addTextChangedListener(indeal);
		out.addTextChangedListener(outdeal);
	}
}
