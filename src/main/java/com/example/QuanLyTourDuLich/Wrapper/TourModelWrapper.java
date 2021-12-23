package com.example.QuanLyTourDuLich.Wrapper;

import java.util.ArrayList;
import Model.TourModel;

public class TourModelWrapper {
	private ArrayList<TourModel> tourList;

	public ArrayList<TourModel> getTourList() {
		return tourList;
	}

	public void setTourList(ArrayList<TourModel> tours) {
		this.tourList = new ArrayList<>();
		for (TourModel tourModel : tours) {
			if (tourModel.getTenTour() != null)
				this.tourList.add(tourModel);
		}
	}

	public String getSTT(String maTour) {
		String stt = "";
		int length = maTour.length();
		if (length > 1) {
			stt = maTour.substring(1);
		}

		return stt;
	}

}
