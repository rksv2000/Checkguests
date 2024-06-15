package cg;
import jakarta.persistence.*;
import java.util.*;


@Entity
public class CheckIn {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String checkintime,roomno,leavingtime;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="checkin")
	private Set<Guest> guests;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCheckintime() {
		return checkintime;
	}
	public void setCheckintime(String checkintime) {
		this.checkintime = checkintime;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getLeavingtime() {
		return leavingtime;
	}
	public void setLeavingtime(String leavingtime) {
		this.leavingtime = leavingtime;
	}
	public Set<Guest> getGuests() {
		return guests;
	}
	public void setGuests(Set<Guest> guests) {
		this.guests = guests;
		if(guests != null) {
			for(Guest g : guests) {
				g.setCheckin(this);
			}
		}
	}
	@Override
	public String toString() {
		return "CheckIn [id=" + id + ", checkintime=" + checkintime + ", roomno=" + roomno + ", leavingtime="
				+ leavingtime + ", guests=" + guests + "]";
	}
	
}
