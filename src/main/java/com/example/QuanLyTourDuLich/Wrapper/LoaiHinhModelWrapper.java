package com.example.QuanLyTourDuLich.Wrapper;

import java.util.ArrayList;
import Model.LoaiHinhModel;

public class LoaiHinhModelWrapper {
	private ArrayList<LoaiHinhModel> loaiHinhTourList;

	public ArrayList<LoaiHinhModel> getLoaiHinhList() {
		return loaiHinhTourList;
	}

	public void setLoaiHinhList(ArrayList<LoaiHinhModel> loaiHinhList) {
		this.loaiHinhTourList = new ArrayList<>();
		for (LoaiHinhModel loaiHinhModel : loaiHinhList) {
			if (loaiHinhModel.getMaLoaiHinh() != null)
				this.loaiHinhTourList.add(loaiHinhModel);
		}
	}
	
}
