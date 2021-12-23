package com.example.QuanLyTourDuLich.Controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.QuanLyTourDuLich.Wrapper.DoanDuLichModelWrapper;
import Model.DoanDuLichModel;

@Controller
public class DoanController {
	private DoanDuLichModel doandulich = new DoanDuLichModel();
	private ArrayList<DoanDuLichModel> doandulichList = new ArrayList<>();

	public DoanController() {
		try {
			this.doandulichList = doandulich.getAllDoanDuLich();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/DoanDuLich")
	public String nhanviens(Model model) {
		DoanDuLichModelWrapper wrapperDoan = new DoanDuLichModelWrapper();
		wrapperDoan.setDoanDuLichList(doandulichList);
		model.addAttribute("wrapperDoan", wrapperDoan);
		return "doandulich";
	}
}
