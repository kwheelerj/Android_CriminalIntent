package com.example.kwheelerj.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import static android.widget.CompoundButton.*;

public class CrimeFragment extends Fragment {
	/* Controller that interacts with model an view objects. */

	private Crime mCrime;
	private EditText mTitleField;
	private Button mDateButton;
	private CheckBox mCheckBox;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCrime = new Crime();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_crime, container, false);


		mTitleField = v.findViewById(R.id.crime_title);
		mTitleField.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// This space intentionally left blank.
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mCrime.setTitle(s.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
				// This space intentionally left blank.
			}
		});


		mDateButton = v.findViewById(R.id.crime_date);
		mDateButton.setText(mCrime.getDate().toString());
		mDateButton.setEnabled(false);


		mCheckBox = v.findViewById(R.id.crime_solved);
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mCrime.setSolved(isChecked);
			}
		});


		return v;
	}

}
