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
	private Room roomSelected = new Room();
	private boolean visibility = false;

	@EJB
	private AssignmentManagementLocal assignmentManagementLocal;

	public String doAddRoom() {
		assignmentManagementLocal.addRoom(roomSelected);
		visibility = false;
		return "";
	}

	public String doSelect() {
		visibility = true;
		return "";
	}

	public String doDelete() {
		assignmentManagementLocal.deleteRoom(roomSelected);
		roomSelected = new Room();
		return "";
	}

	public String show() {
		System.out.println(visibility);
		visibility = true;

		return "";
	}

	public List<Room> getRooms() {
		rooms = assignmentManagementLocal.findRooms();
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public Room getRoomSelected() {
		return roomSelected;
	}

	public void setRoomSelected(Room roomSelected) {
		this.roomSelected = roomSelected;
	}

}
