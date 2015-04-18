package mBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.interfaces.AssignmentManagementLocal;
import domain.Game;
import domain.Room;

@ManagedBean
@SessionScoped
public class AdministrationBean {
	private List<Room> rooms;
	private Room roomSelected = new Room();
	private boolean visibility = false;
	private List<Game> games;
	private List<Game> selectedGames;

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

	public String doAssignGamesToRoom() {
		for (Game g : selectedGames) {
			assignmentManagementLocal.assignGameToRoom(g.getId(),
					roomSelected.getId());
		}
		return "";
	}

	public String doDelete() {
		assignmentManagementLocal.deleteRoom(roomSelected);
		roomSelected = new Room();
		return "";
	}

	public Room doFindRoomByName(String name) {
		return assignmentManagementLocal.findRoomByName(name);
	}

	public Game doFindGameByName(String name) {
		return assignmentManagementLocal.findGameByName(name);
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

	public List<Game> getGames() {
		games = assignmentManagementLocal.findAllGames();
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Game> getSelectedGames() {
		return selectedGames;
	}

	public void setSelectedGames(List<Game> selectedGames) {
		this.selectedGames = selectedGames;
	}

}
