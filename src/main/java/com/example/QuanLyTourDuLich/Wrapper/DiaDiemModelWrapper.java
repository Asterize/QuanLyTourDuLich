package com.example.QuanLyTourDuLich.Wrapper;

import java.util.ArrayList;
import Model.DiaDiemModel;


public class DiaDiemModelWrapper {
	private ArrayList<DiaDiemModel> diaDiemList;

	public ArrayList<DiaDiemModel> getDiaDiemList() {
		return diaDiemList;
	}

	public void setDiaDiemList(ArrayList<DiaDiemModel> listDD) {
		this.diaDiemList = new ArrayList<>();
		for (DiaDiemModel diadiem : listDD) {
			if (diadiem.getMaDiaDiem() != null)
				this.diaDiemList.add(diadiem);
		}
	}
	
}
