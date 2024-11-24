package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "SanPham.findAll", query = "SELECT sp FROM SanPham sp") })

@Entity
@Table(name = "SanPham")
public class SanPham {
	@Id
	@Column(name = "MaSanPham")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long maSanPham;

	@Column(name = "TenSanPham")
	private String tenSanPham;

	@Column(name = "NgaySanXuat")
	private Date ngaySanXuat;

	@Column(name = "MoTa")
	private String moTa;

	@Column(name = "GiaBan")
	private double giaBan;

	@Column(name = "SoLuong")
	private int soLuong;

	@Column(name = "HangSanXuat")
	private String hangSanXuat;

	@Column(name = "DanhMuc")
	private String danhMuc;

	@Column(name = "AnhDaiDien")
	private String anhDaiDien;

	@Column(name = "TrangThai")
	private int trangThai;

	@ManyToOne
	@JoinColumn(name = "MaBienThe")
	private BienTheSanPham bienThe;

	public SanPham() {
		super();
	}

	public SanPham(long maSanPham, String tenSanPham, Date ngaySanXuat, String moTa, double giaBan, int soLuong,
			String hangSanXuat, String danhMuc, String anhDaiDien, int trangThai, BienTheSanPham bienThe) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.ngaySanXuat = ngaySanXuat;
		this.moTa = moTa;
		this.giaBan = giaBan;
		this.soLuong = soLuong;
		this.hangSanXuat = hangSanXuat;
		this.danhMuc = danhMuc;
		this.anhDaiDien = anhDaiDien;
		this.trangThai = trangThai;
		this.bienThe = bienThe;
	}

	public long getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(long maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public Date getNgaySanXuat() {
		return ngaySanXuat;
	}

	public void setNgaySanXuat(Date ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getHangSanXuat() {
		return hangSanXuat;
	}

	public void setHangSanXuat(String hangSanXuat) {
		this.hangSanXuat = hangSanXuat;
	}

	public String getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(String danhMuc) {
		this.danhMuc = danhMuc;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public BienTheSanPham getBienThe() {
		return bienThe;
	}

	public void setBienThe(BienTheSanPham bienThe) {
		this.bienThe = bienThe;
	}

}
