package com.example.QuanLyTourDuLich.Wrapper;

import java.util.ArrayList;
import Model.PhanBoNVDoanModel;


public class PhanBoNVDoanModelWrapper {
	private ArrayList<PhanBoNVDoanModel> phanBoNVList;

	public ArrayList<PhanBoNVDoanModel> getPhanBoNVList() {
		return phanBoNVList;
	}

	public void setPhanBoNVList(ArrayList<PhanBoNVDoanModel> listPB) {
		this.phanBoNVList = new ArrayList<>();
		for (PhanBoNVDoanModel pb : listPB) {
			if (pb.getMaDoan() != null)
				this.phanBoNVList.add(pb);
		}
	}
	
}
