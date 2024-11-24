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

@NamedQueries({ @NamedQuery(name = "ProductDetail.findAll", query = "SELECT pd FROM ProductDetail pd") })

@Entity
@Table(name = "BienTheSanPham")
public class ProductDetail {
	@Id
	@Column(name = "MaBienThe")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "TenBienThe")
	private String productDetailName;

	@Column(name = "MoTa")
	private String description;

	@Column(name = "Gia")
	private double price;

	@Column(name = "TrangThai")
	private int status;

	@OneToMany(mappedBy = "detail")
	private List<Product> products;

	public ProductDetail() {
		super();
	}

	public ProductDetail(int id, String productDetailName, String description, double price, int status,
			List<Product> products) {
		super();
		this.id = id;
		this.productDetailName = productDetailName;
		this.description = description;
		this.price = price;
		this.status = status;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductDetailName() {
		return productDetailName;
	}

	public void setProductDetailName(String productDetailName) {
		this.productDetailName = productDetailName;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
