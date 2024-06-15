package cg;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


@Entity
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name,phonenumber,address;
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonBackReference
	private CheckIn checkin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CheckIn getCheckin() {
		return checkin;
	}
	public void setCheckin(CheckIn checkin) {
		this.checkin = checkin;
	}
	@Override
	public String toString() {
		return "Guest [id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", address=" + address
				+ ", checkin=" + checkin + "]";
	}
	
}
