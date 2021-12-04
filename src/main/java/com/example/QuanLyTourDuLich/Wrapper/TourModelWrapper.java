package com.example.QuanLyTourDuLich.Wrapper;

import java.util.ArrayList;

import Model.TourModel;

public class TourModelWrapper {
	private ArrayList<TourModel> tourList;

	   public ArrayList<TourModel> getTourList() {
	      return tourList;
	   }
	   public void setTourList(ArrayList<TourModel> tours) {
	      this.tourList = tours;
	   }
}
