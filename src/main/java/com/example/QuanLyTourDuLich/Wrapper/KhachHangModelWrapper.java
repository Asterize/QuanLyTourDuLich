package com.example.QuanLyTourDuLich.Wrapper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import Model.KhachHangModel;


public class KhachHangModelWrapper {
	private ArrayList<KhachHangModel> khachList;

	public ArrayList<KhachHangModel> getKhachHangList() {
		return khachList;
	}

	public void setKhachHangList(ArrayList<KhachHangModel> listKhachHang) {
		this.khachList = new ArrayList<>();
		for (KhachHangModel kh : listKhachHang) {
			if (kh.getMaKhachHang() != null)
				this.khachList.add(kh);
		}
	}
	
	public String formatDoanhThu(double dt) {
        DecimalFormat currency = new DecimalFormat("#,###,###,### VND");
        String luongFormat = currency.format(dt);
        return luongFormat;
    }
}
