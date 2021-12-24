package com.example.QuanLyTourDuLich.Wrapper;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Model.LoaiChiPhiModel;


public class LoaiChiPhiModelWrapper {
	private ArrayList<LoaiChiPhiModel> loaiChiPhiList;

	public ArrayList<LoaiChiPhiModel> getLoaiChiPhiList() {
		return loaiChiPhiList;
	}

	public void setLoaiChiPhiList(ArrayList<LoaiChiPhiModel> listCP) {
		this.loaiChiPhiList = new ArrayList<>();
		for (LoaiChiPhiModel lcp : listCP) {
			if (lcp.getMaLoaiChiPhi() != null)
				this.loaiChiPhiList.add(lcp);
		}
	}
	
	public String formatDoanhThu(double dt) {
        DecimalFormat currency = new DecimalFormat("#,###,###,### VND");
        String luongFormat = currency.format(dt);
        return luongFormat;
    }
}
