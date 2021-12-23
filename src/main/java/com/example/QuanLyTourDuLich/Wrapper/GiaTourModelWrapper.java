package com.example.QuanLyTourDuLich.Wrapper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Model.GiaTourModel;

public class GiaTourModelWrapper {
	private ArrayList<GiaTourModel> giaTourList;

	public ArrayList<GiaTourModel> getGiaTourList() {
		return giaTourList;
	}

	public void setGiaTourList(ArrayList<GiaTourModel> giaList) {
		this.giaTourList = new ArrayList<>();
		for (GiaTourModel giaModel : giaList) {
			if (giaModel.getMaGiaTour() != null)
				this.giaTourList.add(giaModel);
		}
	}
	
	public String formatGiaVietNam(int money) {
		String giaTien = "";
		DecimalFormat currency = new DecimalFormat("#,###,###,### VND");
        giaTien = currency.format(money);
        return giaTien;
	}
	
	public String formatNgayVietNam(String date) {
        String[] mang = new String[3];
        String kq = "";

        StringTokenizer namThangNgay = new StringTokenizer(date, "-");
        int i = 0;
        while (namThangNgay.hasMoreTokens()) {
            String temp = namThangNgay.nextToken();
            mang[i++] = temp;
        }

        for (int j = mang.length - 1; j != -1; j--) {
            kq += mang[j] + "-";

        }

        kq = kq.substring(0, kq.lastIndexOf("-"));

        return kq;
    }
}
