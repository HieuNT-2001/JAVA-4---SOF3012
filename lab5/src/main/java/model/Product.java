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

@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p") })

@Entity
@Table(name = "SanPham")
public class Product {
	@Id
	@Column(name = "MaSanPham")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "TenSanPham")
	private String productName;

	@Column(name = "NgaySanXuat")
	private Date date;

	@Column(name = "MoTa")
	private String description;

	@Column(name = "GiaBan")
	private double price;

	@Column(name = "SoLuong")
	private int quantity;

	@Column(name = "HangSanXuat")
	private String brand;

	@Column(name = "DanhMuc")
	private String category;

	@Column(name = "AnhDaiDien")
	private String image;

	@Column(name = "TrangThai")
	private int status;

	@ManyToOne
	@JoinColumn(name = "MaBienThe")
	private ProductDetail detail;

	public Product() {
		super();
	}

	public Product(long id, String productName, Date date, String description, double price, int quantity, String brand,
			String category, String image, int status, ProductDetail detailId) {
		super();
		this.id = id;
		this.productName = productName;
		this.date = date;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.brand = brand;
		this.category = category;
		this.image = image;
		this.status = status;
		this.detail = detailId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ProductDetail getDetail() {
		return detail;
	}

	public void setDetail(ProductDetail detail) {
		this.detail = detail;
	}

}
