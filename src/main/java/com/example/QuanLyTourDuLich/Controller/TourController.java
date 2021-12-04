package com.example.QuanLyTourDuLich.Controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.QuanLyTourDuLich.Wrapper.TourModelWrapper;

import Model.TourModel;

@Controller
public class TourController {
	private ArrayList<TourModel> allTours = new ArrayList<>();

	public TourController() {
		TourModel tourModel = new TourModel();
		try {
			this.allTours = tourModel.getAllTours();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/")
	String index(Model model) {
		TourModelWrapper wrapper = new TourModelWrapper();
		wrapper.setTourList(allTours);
		model.addAttribute("wrapper", wrapper);

		return "tour";
	}

	@RequestMapping(value = "/query/submitQuery", method = RequestMethod.POST)
	public String processQuery(@ModelAttribute TourModelWrapper wrapper, Model model) {

		System.out.println(wrapper.getTourList() != null ? wrapper.getTourList().size() : "null list");
		System.out.println("--");

		model.addAttribute("wrapper", wrapper);

		return "tour";
	}

}
