package com.example.QuanLyTourDuLich.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import com.example.QuanLyTourDuLich.Wrapper.NhanVienModelWrapper;
import DAL.NhanVienDAL;
import Model.NhanVienModel;

@Controller
public class NhanVienController {
	ArrayList<NhanVienModel> allNhanViens = new ArrayList<>();
//    ArrayList<NhanVienModel> listNhanVienTemp = new ArrayList<>();  // dùng cho việc lưu kết quả tìm kiếm nhân viên
	private NhanVienModel nhanVienHienTai = new NhanVienModel();

	public NhanVienController() {
		NhanVienModel nhanVienModel = new NhanVienModel();
		try {
			this.allNhanViens = nhanVienModel.getAllNhanVien();
			for (Iterator<NhanVienModel> iterator = allNhanViens.iterator(); iterator.hasNext();) {
				NhanVienModel nv = (NhanVienModel) iterator.next();
				if (nv.getTrangThai() == null) {
					nv.setTrangThai("Đang làm việc");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/QL_NhanVien")
	public String nhanviens(Model model) {
		NhanVienModelWrapper wrapperNV = new NhanVienModelWrapper();
		wrapperNV.setNhanVienList(allNhanViens);
		model.addAttribute("wrapperNV", wrapperNV);
		return "nhanvien";
	}

	@RequestMapping(value = "/QL_NhanVien/{idNV}", method = RequestMethod.GET)
	public String chiTietNhanVien(Model model, @PathVariable("idNV") String maNV) {
		try {
			NhanVienDAL nhanVienDAL = new NhanVienDAL();
			nhanVienHienTai = nhanVienDAL.getNhanVienByMaNV(maNV);
			// Nhân viên hiện tại
			NhanVienModelWrapper wrapper_NhanVien = new NhanVienModelWrapper();
			wrapper_NhanVien.setNhanVienList(allNhanViens);

			String formatNgaySinh = wrapper_NhanVien.formatNgaySinh(nhanVienHienTai.getNgaySinh());

			// set model
			model.addAttribute("nhanVienHienTai", nhanVienHienTai);
			model.addAttribute("wrapperNV", wrapper_NhanVien);
			model.addAttribute("formatNgaySinh", formatNgaySinh);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "edit_nhanvien";
	}

	@RequestMapping(value = "/QL_NhanVien/AddNhanVien", method = RequestMethod.GET)
	public String addNhanVienForm(Model model) {
		try {
			int newBlood = allNhanViens.size() + 1;

			// Model nhân viên mới mặc định ??
			NhanVienModel newNhanVien = new NhanVienModel();
			if (newBlood < 10) {
				newNhanVien.setMaNhanVien("NV0" + newBlood);
			} else {
				newNhanVien.setMaNhanVien("NV" + newBlood);
			}
			newNhanVien.setGioiTinh("Nam");
			newNhanVien.setDiaChi("273, An Dương Vương, Phường 3, Quận 5, Hồ Chí Minh");
			newNhanVien.setLuong("3000000");
			newNhanVien.setNgaySinh(LocalDate.of(2000, 1, 1));
			newNhanVien.setSDT("0123456789");
			newNhanVien.setTenNhanVien("Nguyễn Văn A");
			newNhanVien.setTrangThai("Đang làm việc");

			// Danh sách nhân viên
			NhanVienModelWrapper wrapper_NhanVien = new NhanVienModelWrapper();
			wrapper_NhanVien.setNhanVienList(allNhanViens);
			String formatNgaySinh = wrapper_NhanVien.formatNgaySinh(newNhanVien.getNgaySinh());

			// set model
			model.addAttribute("nhanVienMoi", newNhanVien);
			model.addAttribute("wrapperNV", wrapper_NhanVien);
			model.addAttribute("formatNgaySinh", formatNgaySinh);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "add_nhanvien";
	}

	@RequestMapping(value ="/QL_NhanVien/confirm_AddNhanVien", method = RequestMethod.POST)
	public RedirectView confirmAddNhanVien(String maNV, String name, String gender, String phone, String ngaySinh,
			String oldGender, String diaChi, String trangThai, String luong) {
		try {
			int size = allNhanViens.size() + 1;
			String maNVLatest = size > 9 ? "NV" + size : "NV0" + size;
			if (maNV.equals(maNVLatest)) {
				NhanVienModel nhanVienMoi = new NhanVienModel();
				nhanVienMoi.setMaNhanVien(maNV);
				nhanVienMoi.setTenNhanVien(name);
				nhanVienMoi.setGioiTinh(gender);
				nhanVienMoi.setSDT(phone);

				String[] date = ngaySinh.split("-");
				if (date.length == 3) {
					int year = Integer.parseInt(date[0]);
					int month = Integer.parseInt(date[1]);
					int day = Integer.parseInt(date[2]);
					LocalDate newDate = LocalDate.of(year, month, day);
					nhanVienMoi.setNgaySinh(newDate);
				}

				nhanVienMoi.setDiaChi(diaChi);
				nhanVienMoi.setTrangThai(trangThai);
				nhanVienMoi.setLuong(luong);

				nhanVienMoi.insert(nhanVienMoi);

				allNhanViens = nhanVienMoi.getAllNhanVien();
				for (Iterator<NhanVienModel> iterator = allNhanViens.iterator(); iterator.hasNext();) {
					NhanVienModel nv = (NhanVienModel) iterator.next();
					if (nv.getTrangThai() == null) {
						nv.setTrangThai("Đang làm việc");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new RedirectView("/QL_NhanVien");
	}

	@RequestMapping(value = "/QL_NhanVien/changeNhanVien", method = RequestMethod.POST)
	public RedirectView changeInfoNhanVien(String maNV, String name, String gender, String phone, String ngaySinh,
			String oldGender, String diaChi, String trangThai, String luong) {
		try {
			if (nhanVienHienTai.getMaNhanVien().equals(maNV)) {
				nhanVienHienTai.setTenNhanVien(name);
				nhanVienHienTai.setGioiTinh(gender);
				nhanVienHienTai.setSDT(phone);

				String[] date = ngaySinh.split("-");
				if (date.length == 3) {
					int year = Integer.parseInt(date[0]);
					int month = Integer.parseInt(date[1]);
					int day = Integer.parseInt(date[2]);
					LocalDate newDate = LocalDate.of(year, month, day);
					nhanVienHienTai.setNgaySinh(newDate);
				}

				nhanVienHienTai.setDiaChi(diaChi);
				nhanVienHienTai.setTrangThai(trangThai);
				nhanVienHienTai.setLuong(luong);

				nhanVienHienTai.update(nhanVienHienTai);

				allNhanViens = nhanVienHienTai.getAllNhanVien();
				for (Iterator<NhanVienModel> iterator = allNhanViens.iterator(); iterator.hasNext();) {
					NhanVienModel nv = (NhanVienModel) iterator.next();
					if (nv.getTrangThai() == null) {
						nv.setTrangThai("Đang làm việc");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new RedirectView("/QL_NhanVien");
	}
}
