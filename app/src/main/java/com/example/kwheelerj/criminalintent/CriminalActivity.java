package com.example.kwheelerj.criminalintent;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class CriminalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_criminal);

		FragmentManager fm = getFragmentManager();

		/* Use the fm to find the fragment created in the xml of the activity (framelayout) */
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);
		/* If fragment is already in the fm's list, the fm will return in. */
		/* How could it already exist?  onSaveInstanceState() -> onDestroy() [rotation] */
		/* When an activity is destroyed, its FragmentManager saves out its list of fragments. */
		/* When the activity is recreated, the NEW FragmentManager retrieves the list (FROM WHERE)
			(bundle?) and recreates the listed fragments to make everything as it was before. */

		if (fragment == null) {
			fragment = new CrimeFragment();
			fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
		}

		/* NOTE on the container view ID:
		 *	1) Tells the FragmentManager where in activity view to have fragment view.
		 *	2) Used as a unique identifier for a fragment in the FragmentManager's list.
		 */

		/* In the CrimeFragment class, the onActivityCreated(..) method is called. */
	}
}
