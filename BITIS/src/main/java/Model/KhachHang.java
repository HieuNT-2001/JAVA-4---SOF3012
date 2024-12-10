package Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="KhachHang")
public class KhachHang {
	@Id
	String MaKH;
	String Ho;
	String Ten;
	boolean GioiTinh;
	
	@Temporal(TemporalType.DATE)
	Date NgaySinh;
	
	String Email;
	String SDT;
	String MatKhau;
	
	public KhachHang() {
		super();
	}

	public KhachHang(String maKH, String ho, String ten, boolean gioiTinh, Date ngaySinh, String email, String sDT,
			String matKhau) {
		super();
		MaKH = maKH;
		Ho = ho;
		Ten = ten;
		GioiTinh = gioiTinh;
		NgaySinh = ngaySinh;
		Email = email;
		SDT = sDT;
		MatKhau = matKhau;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String maKH) {
		MaKH = maKH;
	}

	public String getHo() {
		return Ho;
	}

	public void setHo(String ho) {
		Ho = ho;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public boolean isGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	
	
	
}
