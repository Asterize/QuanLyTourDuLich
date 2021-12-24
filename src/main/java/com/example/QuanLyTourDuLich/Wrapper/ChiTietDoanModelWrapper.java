package com.example.QuanLyTourDuLich.Wrapper;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Model.ChiTietDoanModel;


public class ChiTietDoanModelWrapper {
	private ArrayList<ChiTietDoanModel> ctDoanList;

	public ArrayList<ChiTietDoanModel> getChiTietDoanList() {
		return ctDoanList;
	}

	public void setChiTietDoanList(ArrayList<ChiTietDoanModel> listCTD) {
		this.ctDoanList = new ArrayList<>();
		for (ChiTietDoanModel doan : listCTD) {
			if (doan.getMaDoan() != null)
				this.ctDoanList.add(doan);
		}
	}
	
	public String formatDoanhThu(double dt) {
        DecimalFormat currency = new DecimalFormat("#,###,###,### VND");
        String luongFormat = currency.format(dt);
        return luongFormat;
    }
}
