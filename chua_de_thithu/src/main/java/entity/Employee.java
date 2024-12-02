package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e") })

@Entity
@Table(name = "NhanVien")
public class Employee {

	@Id
	@Column(name = "MaNV")
	private String id;

	@Column(name = "TenNV")
	private String name;

	@Column(name = "DiaChi")
	private String address;

	@Column(name = "GioiTinh")
	private boolean gender;

	public Employee() {
		super();
	}

	public Employee(String id, String name, String address, boolean gender) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

}
