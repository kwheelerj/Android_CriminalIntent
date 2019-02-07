package com.example.kwheelerj.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {

	private RecyclerView mCrimeRecyclerView;
	private CrimeAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

		mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
		mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		updateUI();

		return view;
	}

	public void updateUI() {
		CrimeLab crimeLab = CrimeLab.get(getActivity());
		List<Crime> crimes = crimeLab.getCrimes();

		mAdapter = new CrimeAdapter(crimes);
		mCrimeRecyclerView.setAdapter(mAdapter);
	}

	/* Inner Class */
	private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		private Crime mCrime;

		private TextView mTitleTextView;
		private TextView mDateTextView;

		public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
			super(inflater.inflate(R.layout.list_item_crime, parent, false));
			itemView.setOnClickListener(this);

			mTitleTextView = itemView.findViewById(R.id.crime_title);	/* ViewHolder.itemView */
			mDateTextView = itemView.findViewById(R.id.crime_date);
		}

		public void bind(Crime crime) {
			mCrime = crime;
			mTitleTextView.setText(mCrime.getTitle());
			mDateTextView.setText(mCrime.getDate().toString());
		}

		@Override
		public void onClick(View view) {
			Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
		}

	}

	/* Inner Class */
	private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

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
			Crime crime = mCrimes.get(position);
			crimeHolder.bind(crime);
		}

	}

}
