package com.example.kwheelerj.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity {

	protected abstract Fragment createFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);	// activity_fragment.xml

		FragmentManager fm = getSupportFragmentManager();

		/* Use the fm to find the fragment created in the xml of the activity (framelayout) */
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);
		// R.id.fragment_container -> activity_fragment.xml (see note 2)
		/* If fragment is already in the fm's list, the fm will return in. */
		/* How could it already exist?  onSaveInstanceState() -> onDestroy() [rotation] */
		/* When an activity is destroyed, its FragmentManager saves out its list of fragments. */
		/* When the activity is recreated, the NEW FragmentManager retrieves the list (FROM WHERE)
			(bundle?) and recreates the listed fragments to make everything as it was before. */

		if (fragment == null) {
			fragment = createFragment();
			fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
			// R.id.fragment_container -> activity_fragment.xml (see note 1)
		}

		/* NOTE on the container view ID:
		 *	1) Tells the FragmentManager where in activity view to have fragment view.
		 *	2) Used as a unique identifier for a fragment in the FragmentManager's list.
		 */

		/* In the *Fragment class, the onActivityCreated(..) method is called. */
	}
}
