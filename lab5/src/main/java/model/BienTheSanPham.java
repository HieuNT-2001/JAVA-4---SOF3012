package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "BienTheSanPham.findAll", query = "SELECT o FROM BienTheSanPham o") })

@Entity
@Table(name = "BienTheSanPham")
public class BienTheSanPham {
	@Id
	@Column(name = "MaBienThe")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maBienThe;

	@Column(name = "TenBienThe")
	private String tenBienThe;

	@Column(name = "MoTa")
	private String moTa;

	@Column(name = "Gia")
	private double gia;

	@Column(name = "TrangThai")
	private int trangThai;

	@OneToMany(mappedBy = "bienThe")
	private List<SanPham> listSanPham;

	public BienTheSanPham() {
		super();
	}

	public BienTheSanPham(int maBienThe, String tenBienThe, String moTa, double gia, int trangThai,
			List<SanPham> listSanPham) {
		super();
		this.maBienThe = maBienThe;
		this.tenBienThe = tenBienThe;
		this.moTa = moTa;
		this.gia = gia;
		this.trangThai = trangThai;
		this.listSanPham = listSanPham;
	}

	public int getMaBienThe() {
		return maBienThe;
	}

	public void setMaBienThe(int maBienThe) {
		this.maBienThe = maBienThe;
	}

	public String getTenBienThe() {
		return tenBienThe;
	}

	public void setTenBienThe(String tenBienThe) {
		this.tenBienThe = tenBienThe;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public List<SanPham> getListSanPham() {
		return listSanPham;
	}

	public void setListSanPham(List<SanPham> listSanPham) {
		this.listSanPham = listSanPham;
	}

}
