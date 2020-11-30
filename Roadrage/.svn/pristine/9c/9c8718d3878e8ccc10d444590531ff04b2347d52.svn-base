package model;

/**
 * Bicycle subclass of Vehicle for assignment 2 for TCSS 305.
 * Bike has specific behavior for a vehicle.
 * 
 * @author Kenneth Ahrens
 * @version Fall 2020
 */

import java.util.Map;
import java.util.TreeMap;

public class Bicycle extends AbstractVehicle {

	/** Constant death time of this vehicle. */
	private static final int DEATH_TIME = 35;

	/**
	 * Constructor to initialize all bike fields. Passes its info to
	 * AbstractVehicle to eliminate redundancy.
	 * 
	 */
	public Bicycle(final int theX, final int theY, final Direction theDir) {
		super(theX, theY, theDir, DEATH_TIME);

	}

	/**
	 * Returns whether or not this bicycle may move onto the given type of
	 * terrain, and considers if it can pass depending on the light.
	 * 
	 * Bike can only pass on green light for valid terrain.
	 * 
	 * @param theTerrain The terrain.
	 * @param theLight   The light color.
	 * @return whether or not this bike may move onto the given type of
	 *         terrain when the street lights are the given color.
	 */
	@Override
	public boolean canPass(final Terrain theTerrain, final Light theLight) {
		boolean result = false;

		// check if terrain can be moved to
		if (validTerrain(theTerrain)) {
			result = true;

			// bikes ignore green lights but stop for yellow and red
			Boolean stopAtLight = (theTerrain == Terrain.LIGHT
					&& theLight != Light.GREEN);
			
			//when true that bike stops then can't pass
			if (stopAtLight) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Returns the direction this bicycle would like to move, based on the given
	 * map of the neighboring terrain. Bike prioritizes moving towards a trail
	 * whenever possible. If not go straight, else left, else right, else reverse.
	 * 
	 * @param theNeighbors The map of neighboring terrain.
	 * @return The direction this bicycle would like to move.
	 */
	@Override
	public Direction chooseDirection(
			final Map<Direction, Terrain> theNeighbors) {

		// copy of original direction
		Direction bikeDir = getDirection();

		// surrounding terrain for each direction
		final Terrain straightAhead = theNeighbors.get(bikeDir);
		final Terrain toTheLeft = theNeighbors.get(bikeDir.left());
		final Terrain toTheRight = theNeighbors.get(bikeDir.right());

		// prioritize going straight if possible
		if (validTerrain(straightAhead)) {

			// if you can go straight check if its possible to move to a trail
			// instead
			boolean hasNearbyTrail = (straightAhead == Terrain.TRAIL
					|| toTheLeft == Terrain.TRAIL
					|| toTheRight == Terrain.TRAIL);

			//if there is a nearby trail move to it
			if (hasNearbyTrail) {
				if (straightAhead == Terrain.STREET
						&& toTheLeft == Terrain.TRAIL) {
					bikeDir = bikeDir.left();
				} else if (straightAhead == Terrain.STREET
						&& toTheRight == Terrain.TRAIL) {
					bikeDir = bikeDir.right();
				}
				// if it doesn't pass these statements it just keeps going the
				// same direction that was set at the start of the method
			}

		//go left otherwise
		} else if (validTerrain(toTheLeft)) {
			bikeDir = bikeDir.left();
		//go right otherwise
		} else if (validTerrain(toTheRight)) {
			bikeDir = bikeDir.right();
		//last resort reverse the orignal direction
		} else {
			bikeDir = bikeDir.reverse();
		}
		return bikeDir;
	}

	/**
	 * Helper method for chooseDirections and canPass. Checks the passed Terrain
	 * to see if it is a valid terrain type the bicycle is allowed to move
	 * through. Allowed to move through street, light, crosswalk or trail.
	 * 
	 * @param theTerrain The terrain.
	 * @return True if terrain is valid, false if otherwise.
	 */
	private boolean validTerrain(final Terrain theTerrain) {
		// bike can travel only on streets, lights, crosswalks and trails

		boolean result = false;
		if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT
				|| theTerrain == Terrain.CROSSWALK
				|| theTerrain == Terrain.TRAIL) {
			result = true;
		}

		return result;
	}

}
