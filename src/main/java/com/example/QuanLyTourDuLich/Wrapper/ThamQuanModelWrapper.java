package com.example.QuanLyTourDuLich.Wrapper;

import java.util.ArrayList;
import Model.ThamQuanModel;


public class ThamQuanModelWrapper {
	private ArrayList<ThamQuanModel> thamQuanList;

	public ArrayList<ThamQuanModel> getThamQuanList() {
		return thamQuanList;
	}

	public void setThamQuanList(ArrayList<ThamQuanModel> thamQuan) {
		this.thamQuanList = new ArrayList<>();
		for (ThamQuanModel tqModel : thamQuan) {
			if (tqModel.getMaTour() != null)
				this.thamQuanList.add(tqModel);
		}
	}
	
}
