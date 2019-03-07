package com.example.kwheelerj.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {

	private RecyclerView mCrimeRecyclerView;
	private CrimeAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Recall: LayoutInflater coverts an XML layout file into corresponding ViewGroups and Widgets
		View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
		// R.layout.fragment_crime_list -> fragment_crime_list.xml	(RecyclerView)

		mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);	// fragment_crime_list.xml
		mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		// setLayoutManager is specifically for RecyclerViews - REQUIRED
		// The LayoutManager will position every item, and define how scrolling works.

		updateUI();

		return view;
	}
	/* When CrimeListActivity is resumed, it receives a call to onResume() from the OS,
	 *	which in turn causes the FragmentManager to call onResume() on CrimeListFragment.
	 *	We will override this call.
	 */
	@Override
	public void onResume() {
		super.onResume();
		updateUI();
	}

	public void updateUI() {
		CrimeLab crimeLab = CrimeLab.get(getActivity());
		List<Crime> crimes = crimeLab.getCrimes();

		if (mAdapter == null) {
			mAdapter = new CrimeAdapter(crimes);
			mCrimeRecyclerView.setAdapter(mAdapter);
		} else {
			mAdapter.notifyDataSetChanged();
		}

	}

	/* For RecyclerView to get the View objects to handle in the first place, it needs:
	 *	an Adapter subclass
	 *	a ViewHolder subclass
	 */
	// The Recycler view is just the manager, no actual work is done by it - delegation.

	/* Inner Class */
	private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
		/* An adapter is a controller object that sits between the RecyclerView and the
		 *	data set that the RecyclerView should display.
		 *	It adapts the data to the RecyclerView (via the ViewHolder).
		 *
		 * Responsibilities:
		 *	create the necessary ViewHolders
		 *	bind the ViewHolders to the data from the model layer
		 *
		 * Normal conversation between the RecyclerView and the adapter:
		 *	How many objects are in the list? [ getItemCount() ]
		 *	Please create a new ViewHolder, with the View to display. [ onCreateViewHolder(ViewGroup) ]
		 *	Please use this ViewHolder you just created to bind the model data (based off the position passed in) to the ViewHolder's View
		 */

		private List<Crime> mCrimes;

		public CrimeAdapter(List<Crime> crimes) {
			mCrimes = crimes;
		}

		@Override
		public int getItemCount() {
			return mCrimes.size();
		}

		@NonNull
		@Override
		public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			return new CrimeHolder(layoutInflater, parent);
		}

		/* The following method is called each time the RecyclerView requests that a
			given CrimeHolder be bound to a particular crime.
			NOTE: this is called EVERY time necessary (due to scrolling).
			How fast (small) this method is affects the performance while
			quickly scrolling!
		 */
		@Override
		public void onBindViewHolder(@NonNull CrimeHolder crimeHolder, int position) {
			// The adapter will fill in the View to reflect the data in the model object.
			/* Binding is taking java code (like model data in a Crime, or click listeners) and
			 *	hooking it up to a widget.
			 */
			Crime crime = mCrimes.get(position);
			crimeHolder.bind(crime);
			// The real work of binding is in the CrimeHolder (inner)class.
		}

	}

	/* Inner Class */
	private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		/* Create ListRow and access:
		 *	View objects you created and
		 *	the itemView, which is a field the superclass RecyclerView.ViewHolder assigns for you.
		 *	The itemView field is the whole reason for my ViewHolder (CrimeHolder) to exist:
		 *		it holds a reference to the entire View passed into super(view).
		 */
		/* A RecyclerView never creates Views by itself, it creates ViewHolders, which then
		 *	bring their itemViews along for the ride.
		 */
		// A RecyclerView does not actually create ViewHolders itself; it asks an adapter.

		private Crime mCrime;

		private TextView mTitleTextView;
		private TextView mDateTextView;
		private ImageView mCrimeSolved;

		/* The constructor is called by the adapter's onCreateViewHolder(..)
		 *	Pulling all of the widgets you are interested in only needs to happen once;
		 *	do this in the constructor then.
		 */
		public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
			super(inflater.inflate(R.layout.list_item_crime, parent, false));
			/* The view hierarchy can now be accessed through the itemView field,
			 *	which is defined in the superclass, ViewHolder.
			 */
			itemView.setOnClickListener(this);
			/* The CrimeHolder itself is implementing the OnClickListener interface.  On the itemView
			 *	which is the View for the entire row, the CrimeHolder is set as the receiver of
			 *	click events.
			 */

			mTitleTextView = itemView.findViewById(R.id.crime_title);	/* ViewHolder.itemView */
			mDateTextView = itemView.findViewById(R.id.crime_date);
			mCrimeSolved = itemView.findViewById(R.id.crime_solved);
		}

		public void bind(Crime crime) {
			mCrime = crime;
			mTitleTextView.setText(mCrime.getTitle());
			mDateTextView.setText(mCrime.getDate().toString());
			mCrimeSolved.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
		}

		@Override
		public void onClick(View view) {
			//Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
			//Intent intent = new Intent(getActivity(), CrimeActivity.class);
			String mess = "before: " + mCrime.getId();
			Log.d("kwheelerj", mess);
			Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
			startActivity(intent);
		}

	}

}
