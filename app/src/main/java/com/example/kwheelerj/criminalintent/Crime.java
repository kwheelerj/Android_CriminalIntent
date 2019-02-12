package com.example.kwheelerj.criminalintent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime {

	private UUID mId;
	private String mTitle;
	private String mDate;
	private boolean mSolved;

	public Crime() {
		mId = UUID.randomUUID();
		mDate = (new SimpleDateFormat("EEEE, MMM dd, yyyy")).format(new Date());
	}

	public UUID getId() {
		return mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = (new SimpleDateFormat("EEEE, MMM dd, yyyy")).format(date);
	}

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean solved) {
		mSolved = solved;
	}
}
