package com.geno.unicoder;

import java.util.Locale;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.text.*;
import android.view.View.*;

public class MainActivity extends Activity
{
	public EditText in, out;
	public ToggleButton s;
	public Button[] b;
	public int[] id;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		in = (EditText) findViewById(R.id.in);
		out = (EditText) findViewById(R.id.out);
		id = new int[17];
		b = new Button[17];
		for(int i = 0; i < 17; i++)
		{
			id[i] = R.id.bksp + i;
			b[i] = (Button) findViewById(id[i]);
		}
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
				String output = Integer.toHexString(cp).toUpperCase(Locale.ENGLISH);
				in.setText(output);
			}

			@Override
			public void afterTextChanged(Editable p1)
			{
			}
		};
		in.addTextChangedListener(indeal);
		out.addTextChangedListener(outdeal);
		OnClickListener c = new OnClickListener()
		{
			@Override
			public void onClick(View p1)
			{
				String s = in.getText().toString();
				int l = s.length();
				int vid = p1.getId();
				if(vid == id[0])
				{
					if(l == 0)return;
					else in.setText(s.substring(0,l-1));
				}
				else in.setText(s + (new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'})[p1.getId()-id[0]-1]);
			}
		};
		for(int i = 0; i < 17; i++)
			b[i].setOnClickListener(c);
	}
}
