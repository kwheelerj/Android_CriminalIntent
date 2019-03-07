package com.example.kwheelerj.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

import static android.widget.CompoundButton.*;

public class CrimeFragment extends Fragment {
	/* Controller that interacts with model an view objects. */

	/* ActivityToFragment - Solution #1 (cheap, limited): *
	public static final String ARG_CRIME_ID = "crime_id"; */

	/* ActivityToFragment - Solution #2: great */
	/* ZERO connection to the Activity */
	private static final String ARG_CRIME_ID = "crime_id";

	private Crime mCrime;
	private EditText mTitleField;
	private Button mDateButton;
	private CheckBox mCheckBox;

	/* ActivityToFragment - Solution #2 (great): */
	public static CrimeFragment newInstance(UUID crimeId) {
		Bundle args = new Bundle();
		args.putSerializable(ARG_CRIME_ID, crimeId);

		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* First, here is the shortcut solution to getting the intent extra from an activity:
		 *	Pros: easy, simple code
		 *	Cons: Expects that fragment will ALWAYS be hosted by an activity whose intent defines
		 *		the extra from the activity.  Now CrimeFragment cannot be used with just any
		 *		activity.
		 */
		/* Solution #1 (simple, limited) */
		//UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
		/* The BETTER solution is to stash the crimeId someplace that belongs to the CrimeFragment,
				not the CrimeActivity's personal space.  This someplace: "arguments bundle".
		 */

		UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
		/* ERROR DEBUGGING...
		CrimeLab scl = CrimeLab.get(getActivity());

		if (scl == null) {
			mess = "error: " + scl.getCrime(crimeId);
			Log.d("kwheelerj", mess);
		} else {
			mess = "check: " + scl;
			Log.d("kwheelerj", mess);
		}
		mess = "check2: " + scl.getCrime(crimeId);
		Log.d("kwheelerj", mess);

		mCrime = scl.get(getActivity()).getCrime(crimeId);

		mess = "final: " + mCrime.getId();
		Log.d("kwheelerj", mess);
		mess = "final2: " + mCrime.getDate();
		Log.d("kwheelerj", mess);

		//mCrime = new Crime();
		*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_crime, container, false);


		mTitleField = v.findViewById(R.id.crime_title);
		mTitleField.setText(mCrime.getTitle());
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
		mCheckBox.setChecked(mCrime.isSolved());
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mCrime.setSolved(isChecked);
			}
		});


		return v;
	}

}
