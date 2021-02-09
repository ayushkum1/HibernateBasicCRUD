package pojos;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.*;

@Entity //mandatory
@Table(name = "users_table")
public class User {
	
	private Integer userId;
	private String name, email, password;
	private Role role;
	private String confirmPassword;
	private Double regAmount;
	private LocalDate regDate;
	private Byte[] images;
	
	public User(String name, Double regAmount, LocalDate regDate) {
		super();
		this.name = name;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	public User() {
		super();
		System.out.println("in User default constructor");
	}

	public User(String name, String email, String password, Role role, String confirmPassword,
			Double regAmount, LocalDate regDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.confirmPassword = confirmPassword;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	@Id//mandatory
	@GeneratedValue(strategy = GenerationType.IDENTITY)//for mysql
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(length = 20)//varchar(20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 20, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 20, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Enumerated(EnumType.STRING)//varchar(constant name)
	@Column(length = 30, name = "user_role")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Transient //skip from persistence(do not save, if we don't give them a column will be created)
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Column(name = "registration_amount")
	public Double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(Double regAmount) {
		this.regAmount = regAmount;
	}

	@Column(name = "registration_date")
	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	@Lob //large object => Mysql - column type will be longblob
	public Byte[] getImages() {
		return images;
	}

	public void setImages(Byte[] images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", role=" + role + ", regAmount="
				+ regAmount + ", regDate=" + regDate + ", images=" + Arrays.toString(images) + "]";
	}

	
	
}
