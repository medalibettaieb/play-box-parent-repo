package mBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.interfaces.AssignmentManagementLocal;
import domain.Room;

@ManagedBean
@SessionScoped
public class AdministrationBean {
	private List<Room> rooms;
	private Room room = new Room();
	private boolean visibility = false;

	@EJB
	private AssignmentManagementLocal assignmentManagementLocal;

	public String doAddRoom() {
		assignmentManagementLocal.addRoom(room);
		return "";
	}

	public String show() {
		visibility = true;
		System.out.println(visibility);
		return "";
	}

	public List<Room> getRooms() {
		rooms = assignmentManagementLocal.findRooms();
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

}
