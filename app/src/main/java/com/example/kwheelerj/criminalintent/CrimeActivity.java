package com.example.kwheelerj.criminalintent;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

	public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

	public static Intent newIntent(Context packageContext, UUID crimeId) {
		Intent intent = new Intent(packageContext, CrimeActivity.class);
		intent.putExtra(EXTRA_CRIME_ID, crimeId);
		return intent;
		/* Now the crimeId is safely stashed in the intent that belongs to CrimeActivity.  But,
		 *	CrimeFragment needs to retrieve and use the data.  See CrimeFragment.onCreate(..).
		 */
	}

	@Override
	protected Fragment createFragment() {
		/* Before Solution #2, the CrimeFragment would be added to this activity
		 *	before it was created, but we want the fragment to be created
		 *	before being added to the activity, in order to add a bundle to
		 *	the fragment.
		 *
		return new CrimeFragment(); */
		UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
		return CrimeFragment.newInstance(crimeId);
	}

}
