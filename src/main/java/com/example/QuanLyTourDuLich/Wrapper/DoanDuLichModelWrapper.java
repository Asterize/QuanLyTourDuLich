package com.example.QuanLyTourDuLich.Wrapper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import Model.DoanDuLichModel;


public class DoanDuLichModelWrapper {
	private ArrayList<DoanDuLichModel> doanDuLichList;

	public ArrayList<DoanDuLichModel> getDoanDuLichList() {
		return doanDuLichList;
	}

	public void setDoanDuLichList(ArrayList<DoanDuLichModel> listDD) {
		this.doanDuLichList = new ArrayList<>();
		for (DoanDuLichModel doan : listDD) {
			if (doan.getMaDoan() != null)
				this.doanDuLichList.add(doan);
		}
	}
	
	public String formatDoanhThu(double dt) {
        DecimalFormat currency = new DecimalFormat("#,###,###,### VND");
        String luongFormat = currency.format(dt);
        return luongFormat;
    }
}
