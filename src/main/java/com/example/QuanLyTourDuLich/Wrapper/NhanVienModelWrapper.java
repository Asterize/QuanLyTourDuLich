package com.example.QuanLyTourDuLich.Wrapper;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.NhanVienModel;

public class NhanVienModelWrapper {
	private ArrayList<NhanVienModel> nhanVienList;

	public ArrayList<NhanVienModel> getNhanVienList() {
		return nhanVienList;
	}

	public void setNhanVienList(ArrayList<NhanVienModel> nhanViens) {
		this.nhanVienList = new ArrayList<>();
		for (NhanVienModel nvModel : nhanViens) {
			if (nvModel.getMaNhanVien() != null)
				this.nhanVienList.add(nvModel);
		}
	}
	
	public String formatNgaySinh(LocalDate input) {
		String output = input.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return output;
	}
	
	public String formatLuongVND(String luongNV) throws NumberFormatException {
        DecimalFormat currency = new DecimalFormat("#,###,###,### VND");
        String luongFormat = currency.format(Long.parseLong(luongNV));
        return luongFormat;
    }
}
