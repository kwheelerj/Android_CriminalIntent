package com.example.kwheelerj.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CrimeListFragment extends Fragment {

	private RecyclerView mCrimeRecyclerView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

		mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
		mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		return view;
	}

}
