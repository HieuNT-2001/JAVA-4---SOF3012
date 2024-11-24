
package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email LIKE :email"),
		@NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.admin = :role") })

// Annotation cho biết class User đại diện cho một bảng trong csdl
@Entity
// Annotation cho biết đây là tên bảng trong csdl
@Table(name = "Users")
public class User {
	// khóa chính
	@Id
	// tên trường
	@Column(name = "id")
	// String -> nvarchar
	String id;

	@Column(name = "password")
	String password;

	@Column(name = "fullname")
	String fullname;

	@Column(name = "email")

	String email;

	@Column(name = "admin")
	// Boolean -> bit
	boolean admin = false;

	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;

	public User() {
		super();
	}

	public User(String id, String password, String fullname, String email, boolean admin, List<Favorite> favorites) {
		super();
		this.id = id;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.admin = admin;
		this.favorites = favorites;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

}
