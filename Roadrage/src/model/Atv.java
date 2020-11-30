package model;

/**
 * ATV subclass of Vehicle for assignment 2 for TCSS 305.
 * ATV has specific behavior for a vehicle.
 * 
 * @author Kenneth Ahrens
 * @version Fall 2020
 */

import java.util.Map;

public class Atv extends AbstractVehicle {
	/** Constant death time of this vehicle. */
	private static final int DEATH_TIME = 25;

	/**
	 * Constructor to initialize all ATV fields. Passes its info to
	 * AbstractVehicle to eliminate redundancy.
	 * 
	 */
	public Atv(final int theX, final int theY, final Direction theDir) {
		super(theX, theY, theDir, DEATH_TIME);
	}

	/**
	 * Returns whether or not this ATV may move onto the given type of terrain.
	 * ATV ignores all lights and can pass any terrain that is not a wall.
	 * 
	 * @param theTerrain The terrain.
	 * @param theLight   The light color.
	 * @return whether or not this ATV may move onto the given type of terrain
	 *         when the street lights are the given color.
	 */
	@Override
	public boolean canPass(final Terrain theTerrain, final Light theLight) {

		boolean result = false;
		if (theTerrain != Terrain.WALL) {
			result = true;
		}
		return result;
	}

	/**
	 * Randomly returns a legal direction (straight, left or right) this ATV
	 * would like to move, based on the given map of the neighboring terrain.
	 * They will never reverse because they don't need to - they can go
	 * anywhere.
	 * 
	 * 
	 * @param theNeighbors The map of neighboring terrain.
	 * @return The direction this ATV would like to move.
	 */
	@Override
	public Direction chooseDirection(
			final Map<Direction, Terrain> theNeighbors) {

		Direction atvDir = Direction.random();

		// random direction until terrain is not a wall or trying to reverse
		// direction
		while ((theNeighbors.get(atvDir) == Terrain.WALL)
				|| atvDir == getDirection().reverse()) {
			atvDir = Direction.random();
		}

		return atvDir;
	}

}
