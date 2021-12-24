package com.example.QuanLyTourDuLich.Wrapper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import Model.ChiPhiModel;

public class ChiPhiModelWrapper {
	private ArrayList<ChiPhiModel> chiPhiList;

	public ArrayList<ChiPhiModel> getChiPhiDoanList() {
		return chiPhiList;
	}

	public void setChiPhiDoanList(ArrayList<ChiPhiModel> listCP) {
		this.chiPhiList = new ArrayList<>();
		for (ChiPhiModel cp : listCP) {
			if (cp.getMaLoaiChiPhi() != null)
				this.chiPhiList.add(cp);
		}
	}

	public String formatDoanhThu(int dt) {
		DecimalFormat currency = new DecimalFormat("#,###,###,### VND");
		String luongFormat = currency.format(dt);
		return luongFormat;
	}
}
