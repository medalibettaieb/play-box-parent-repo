package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.Game;
import domain.Room;
import domain.Subscription;

@Local
public interface AssignmentManagementLocal {
	Boolean assignGameToRoom(Integer idGame, Integer idRoom);

	List<Game> findAllGamesByRoomId(Integer id);

	Boolean subscribePlayerToRoom(Integer idPlayer, Integer idRoom);

	Boolean playGame(Subscription subscription, Game game);

	List<Room> findRooms();

	Boolean addRoom(Room room);

	Boolean deleteRoom(Room room);

	Room findRoomByName(String name);
}
