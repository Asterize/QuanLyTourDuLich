package com.example.QuanLyTourDuLich.Controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import com.example.QuanLyTourDuLich.Wrapper.ChiPhiModelWrapper;
import com.example.QuanLyTourDuLich.Wrapper.DoanDuLichModelWrapper;
import com.example.QuanLyTourDuLich.Wrapper.KhachHangModelWrapper;
import com.example.QuanLyTourDuLich.Wrapper.LoaiChiPhiModelWrapper;
import com.example.QuanLyTourDuLich.Wrapper.NhanVienModelWrapper;
import com.example.QuanLyTourDuLich.Wrapper.PhanBoNVDoanModelWrapper;
import DAL.ChiPhiDAL;
import DAL.ChiTietDoanDAL;
import DAL.PhanBoNVDoanDAL;
import Model.ChiPhiModel;
import Model.ChiTietDoanModel;
import Model.DoanDuLichModel;
import Model.KhachHangModel;
import Model.LoaiChiPhiModel;
import Model.NhanVienModel;
import Model.PhanBoNVDoanModel;

@Controller
public class DoanController {
	private DoanDuLichModel doandulich = new DoanDuLichModel();
	private ArrayList<DoanDuLichModel> doandulichList = new ArrayList<>();
	private ChiTietDoanModel chiTietDoan = new ChiTietDoanModel();
	private KhachHangModel khachHang = new KhachHangModel();
	private ArrayList<KhachHangModel> listKhachHang = new ArrayList<>();
	private ArrayList<KhachHangModel> listKhachHangKhongTrongDoan = new ArrayList<>();
	private ArrayList<ChiTietDoanModel> listCTD = new ArrayList<>();
	private NhanVienModel nhanvien = new NhanVienModel();
	private PhanBoNVDoanModel phancongNhanvien = new PhanBoNVDoanModel();
	private ArrayList<NhanVienModel> nhanvienList = new ArrayList<>();
	private ArrayList<PhanBoNVDoanModel> nhanvienPCList = new ArrayList<>();
	private ChiPhiModel chiPhi = new ChiPhiModel();
	private LoaiChiPhiModel loaiChiPhi = new LoaiChiPhiModel();
	private ArrayList<ChiPhiModel> listCP = new ArrayList<>();
	private ArrayList<LoaiChiPhiModel> listLoaiCP = new ArrayList<>();

	public DoanController() {
		try {
			this.doandulichList = doandulich.getAllDoanDuLich();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/DoanDuLich")
	public String doanDuLich(Model model) {
		DoanDuLichModelWrapper wrapperDoan = new DoanDuLichModelWrapper();
		wrapperDoan.setDoanDuLichList(doandulichList);
		model.addAttribute("wrapperDoan", wrapperDoan);
		return "doandulich";
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}", method = RequestMethod.GET)
	public String Doan_KhachHang(Model model, @PathVariable("id") String maDoan, @PathVariable("tour") String maTour) {
		listCTD.clear();
		listKhachHang.clear();
		listKhachHangKhongTrongDoan.clear();
		String newMaKH = "KH";
		try {
			listCTD = chiTietDoan.getChiTietDoanByMaDoan(maDoan);
			listKhachHangKhongTrongDoan = khachHang.getAllKhachHang();
			int total = (khachHang.getAllKhachHang().size() + 1);
			newMaKH += total > 9 ? total : "0" + total;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (listCTD != null) {
			for (ChiTietDoanModel ctd : listCTD) {
				try {
					listKhachHang.add(khachHang.getKhachHang(ctd.getMaKhachHang()));
					listKhachHangKhongTrongDoan.removeIf(kh -> kh.getMaKhachHang().equals(ctd.getMaKhachHang()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		KhachHangModelWrapper wrapper_Khach = new KhachHangModelWrapper();
		wrapper_Khach.setKhachHangList(listKhachHang);

		KhachHangModelWrapper wrapper_KhachDeThem = new KhachHangModelWrapper();
		wrapper_KhachDeThem.setKhachHangList(listKhachHangKhongTrongDoan);

		model.addAttribute("wrapperKhach", wrapper_Khach);
		model.addAttribute("wrapperAddKhach", wrapper_KhachDeThem);
		model.addAttribute("maDoanHT", maDoan);
		model.addAttribute("maTourHT", maTour);
		model.addAttribute("maKHMoi", newMaKH);
		return "doandulich_khachhang";
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/AddNewKhach", method = RequestMethod.POST)
	public RedirectView AddNewKhach(Model model, @PathVariable("id") String maDoan, @PathVariable("tour") String maTour,
			String name, String gender, String cccd, String diaChi, String phone, String quocTich, String maKH) {
		KhachHangModel newKH = new KhachHangModel();
		newKH.setMaKhachHang(maKH);
		newKH.setHoTen(name);
		newKH.setGioiTinh(gender);
		newKH.setSoCMND(cccd);
		newKH.setDiaChi(diaChi);
		newKH.setSDT(phone);
		newKH.setQuocTich(quocTich);
		try {
			newKH.addKhachHang(newKH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listCTD.clear();
		listKhachHang.clear();
		listKhachHangKhongTrongDoan.clear();

		return new RedirectView("/DoanDuLich/" + maDoan + "/" + maTour);
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/AddToDoan", method = RequestMethod.POST)
	public RedirectView Doan_KhachHang_Add(Model model, @PathVariable("id") String maDoan,
			@PathVariable("tour") String maTour, String newKhach) {
		String[] split = newKhach.split(":");
		String maKHCanThem = split[0];
		try {
			chiTietDoan.addKhachHangIntoDoan(maDoan, maKHCanThem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listCTD.clear();
		listKhachHang.clear();
		listKhachHangKhongTrongDoan.clear();

		return new RedirectView("/DoanDuLich/" + maDoan + "/" + maTour);
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/{maKH}/Delete", method = RequestMethod.GET)
	public RedirectView Doan_KhachHang_Delete(Model model, @PathVariable("id") String maDoan,
			@PathVariable("tour") String maTour, @PathVariable("maKH") String maKH) {
		try {
			listCTD = chiTietDoan.getChiTietDoanByMaDoan(maDoan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ChiTietDoanDAL ctdDAL = new ChiTietDoanDAL();
		try {
			ctdDAL.deleteChiTietDoan(maKH, maDoan);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		listCTD.clear();
		listKhachHang.clear();
		listKhachHangKhongTrongDoan.clear();

		return new RedirectView("/DoanDuLich/" + maDoan + "/" + maTour);
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/{maKH}/Change", method = RequestMethod.GET)
	public String Doan_KhachHang_Change(Model model, @PathVariable("id") String maDoan,
			@PathVariable("tour") String maTour, @PathVariable("maKH") String maKH) {
		KhachHangModel khachHienTai = new KhachHangModel();
		try {
			khachHienTai = khachHang.getKhachHang(maKH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("khachHT", khachHienTai);
		return "edit_khachhang";
	}

	@RequestMapping(value = "/DoanDuLich/changeKhachHang", method = RequestMethod.POST)
	public RedirectView ChangeKhachHang(String maKH, String name, String gender, String phone, String oldGender,
			String diaChi, String quocTich, String cccd) {

		KhachHangModel khachMoi = new KhachHangModel(maKH, name, cccd, diaChi, gender, phone, quocTich);
		try {
			khachMoi.updateKhachHang(khachMoi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listCTD.clear();
		listKhachHang.clear();
		listKhachHangKhongTrongDoan.clear();

		return new RedirectView("/DoanDuLich/");
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/PhanCong", method = RequestMethod.GET)
	public String Doan_PhanCong(Model model, @PathVariable("id") String maDoan, @PathVariable("tour") String maTour) {
		nhanvienList.clear();
		nhanvienPCList.clear();
		try {
			nhanvienList = nhanvien.getAllNhanVien();
			nhanvienPCList = phancongNhanvien.getNhanVienByMaDoan(maDoan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// bỏ những người đã dc phân công
		for (PhanBoNVDoanModel nvPC : nhanvienPCList) {
			nhanvienList.removeIf(nv -> nv.getMaNhanVien().equals(nvPC.getMaNhanVien()));
		}

		PhanBoNVDoanModelWrapper wrapper_PC = new PhanBoNVDoanModelWrapper();
		wrapper_PC.setPhanBoNVList(nhanvienPCList);

		NhanVienModelWrapper wrapper_NV = new NhanVienModelWrapper();
		wrapper_NV.setNhanVienList(nhanvienList);

		model.addAttribute("wrapperPC", wrapper_PC);
		model.addAttribute("wrapperNV", wrapper_NV);
		model.addAttribute("maDoanHT", maDoan);
		model.addAttribute("maTourHT", maTour);
		return "doandulich_phancong";
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/{maNV_Huy}/CancelPhanCong", method = RequestMethod.GET)
	public RedirectView Doan_PhanCong_Cancel(Model model, @PathVariable("id") String maDoan,
			@PathVariable("tour") String maTour, @PathVariable("maNV_Huy") String maNV) {
		PhanBoNVDoanDAL pbDAL = new PhanBoNVDoanDAL();
		try {
			pbDAL.deletePhanCong(new PhanBoNVDoanModel(maNV, maDoan, "Hủy"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		nhanvienList.clear();
		nhanvienPCList.clear();

		return new RedirectView("/DoanDuLich/" + maDoan + "/" + maTour + "/PhanCong");
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/AddNVToDoan", method = RequestMethod.POST)
	public RedirectView Doan_PhanCong_AddNV(Model model, @PathVariable("id") String maDoan,
			@PathVariable("tour") String maTour, String newNV, String nhiemVu) {
		String[] split = newNV.split(":");
		String maNV = split[0];

		PhanBoNVDoanModel newPhanCong = new PhanBoNVDoanModel(maNV, maDoan, nhiemVu);
		try {
			newPhanCong.phancong();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nhanvienList.clear();
		nhanvienPCList.clear();

		return new RedirectView("/DoanDuLich/" + maDoan + "/" + maTour + "/PhanCong");
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/ChiPhi", method = RequestMethod.GET)
	public String Doan_ChiPhi(Model model, @PathVariable("id") String maDoan, @PathVariable("tour") String maTour) {
		listCP.clear();
		listLoaiCP.clear();

		try {
			listLoaiCP = loaiChiPhi.getAllLoaiCP();
			listCP = chiPhi.getChiPhiByMaDoan(maDoan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// bỏ những loại chi phí đã tồn tại
		for (ChiPhiModel cp : listCP) {
			listLoaiCP.removeIf(loai -> loai.getMaLoaiChiPhi().equals(cp.getMaLoaiChiPhi()));
		}

		LoaiChiPhiModelWrapper wrapper_LoaiCP = new LoaiChiPhiModelWrapper();
		wrapper_LoaiCP.setLoaiChiPhiList(listLoaiCP);

		ChiPhiModelWrapper wrapper_DoanCP = new ChiPhiModelWrapper();
		wrapper_DoanCP.setChiPhiDoanList(listCP);

		model.addAttribute("wrapperLoaiCP", wrapper_LoaiCP);
		model.addAttribute("wrapperChiPhiDoan", wrapper_DoanCP);
		model.addAttribute("maDoanHT", maDoan);
		model.addAttribute("maTourHT", maTour);
		return "doandulich_chiphi";
	}

	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/ThemChiPhi", method = RequestMethod.POST)
	public RedirectView Doan_ChiPhi_Add(Model model, @PathVariable("id") String maDoan,
			@PathVariable("tour") String maTour, String newCP, String soTien) {
		String[] split = newCP.split(":");
		String maLoaiCP = split[0];

		int soTienInt = Integer.parseInt(soTien);
		ChiPhiDAL dal = new ChiPhiDAL();
		ArrayList<ChiPhiModel> allCP;
		
		try {
			allCP = dal.getAllCP(null, null);
			int stt = 1;
			
			// reset ma chi phi khi insert database - tránh bị lủng mã
			ArrayList<ChiPhiModel> neoListCP = new ArrayList<>();
			for (ChiPhiModel chiPhiModel : allCP) {
				chiPhiModel.delete(chiPhiModel.getMaChiPhi());
				
				String maCP = stt> 9 ? "CP" + stt: "CP0" + stt;
				chiPhiModel.setMaChiPhi(maCP);
				neoListCP.add(chiPhiModel);
				stt++;
			}
			
			for (ChiPhiModel chiPhiModel : neoListCP) {
				chiPhiModel.insert(chiPhiModel);
			}
			int size = allCP.size() + 1;
			String newMaCP = size > 9 ? "CP" + size : "CP0" + size;
			
			ChiPhiModel newChiPhi = new ChiPhiModel(newMaCP, maDoan, maLoaiCP, soTienInt);
			newChiPhi.insert(newChiPhi);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		listCP.clear();
		listLoaiCP.clear();

		return new RedirectView("/DoanDuLich/" + maDoan + "/" + maTour + "/ChiPhi");
	}
	
	@RequestMapping(value = "/DoanDuLich/{id}/{tour}/{maCP}/ChiPhi/Delete", method = RequestMethod.GET)
	public RedirectView Doan_ChiPhi_Delete(Model model, @PathVariable("id") String maDoan,
			@PathVariable("tour") String maTour, @PathVariable("maCP") String maCP) {
		try {
//			ArrayList<ChiPhiModel> chiPhiByMaDoan = chiPhi.getChiPhiByMaDoan(maDoan);
			ChiPhiDAL dal = new ChiPhiDAL();
			dal.delete(maCP);
			listCP.clear();
			listLoaiCP.clear();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		listCP.clear();
		listLoaiCP.clear();

		return new RedirectView("/DoanDuLich/" + maDoan + "/" + maTour + "/ChiPhi");
	}
}
