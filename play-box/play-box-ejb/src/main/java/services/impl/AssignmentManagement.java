package services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.AssignmentManagementLocal;
import services.interfaces.AssignmentManagementRemote;
import domain.Game;
import domain.Play;
import domain.Player;
import domain.Room;
import domain.Subscription;

/**
 * Session Bean implementation class AssignmentManagement
 */
@Stateless
public class AssignmentManagement implements AssignmentManagementRemote,
		AssignmentManagementLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AssignmentManagement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean assignGameToRoom(Integer idGame, Integer idRoom) {
		Boolean b = false;
		try {
			Game gameSelected = entityManager.find(Game.class, idGame);
			Room roomSelected = entityManager.find(Room.class, idRoom);

			gameSelected.getRooms().add(roomSelected);
			entityManager.merge(gameSelected);
			b = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> findAllGamesByRoomId(Integer id) {
		List<Game> games = null;
		Room roomSelected = entityManager.find(Room.class, id);
		String jpql = "select g from Game g ,Room r where r=:param1 and r member of g.rooms";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", roomSelected);
		try {
			games = query.getResultList();
		} catch (Exception e) {
			System.err.println("empty room ...");
		}
		return games;
	}

	@Override
	public Boolean subscribePlayerToRoom(Integer idPlayer, Integer idRoom) {
		Boolean b = false;
		try {
			Player playerSelected = entityManager.find(Player.class, idPlayer);
			Room roomSelected = entityManager.find(Room.class, idRoom);
			Subscription subscription = new Subscription(playerSelected,
					roomSelected);
			entityManager.persist(subscription);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean playGame(Subscription subscription, Game game) {
		Boolean b = false;
		try {
			Play play = new Play(subscription, game);
			entityManager.persist(play);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public List<Room> findRooms() {
		return entityManager.createQuery("select r from Room r", Room.class)
				.getResultList();
	}

	@Override
	public Boolean addRoom(Room room) {
		Boolean b = false;
		try {
			entityManager.merge(room);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean deleteRoom(Room room) {
		Boolean b = false;
		try {
			entityManager.remove(entityManager.merge(room));
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Room findRoomByName(String name) {
		return entityManager
				.createQuery("select r from Room r where r.name=:param1",
						Room.class).setParameter("param1", name)
				.getSingleResult();
	}

	@Override
	public List<Game> findAllGames() {
		return entityManager.createQuery("select g from Game g", Game.class)
				.getResultList();
	}

	@Override
	public Game findGameByName(String name) {
		return entityManager
				.createQuery("select g from Game g where g.name=:param1",
						Game.class).setParameter("param1", name)
				.getSingleResult();

	}

	@Override
	public List<Room> findRoomsByGameId(Integer id) {
		Game game = entityManager.find(Game.class, id);
		return entityManager
				.createQuery(
						"select r from Room r where :param1 member of r.games",
						Room.class).setParameter("param1", game)
				.getResultList();
	}

}
