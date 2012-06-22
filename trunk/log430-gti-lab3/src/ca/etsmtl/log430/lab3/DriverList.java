package ca.etsmtl.log430.lab3;

/**
 * This class is used by various other classes that need to keep a list of
 * drivers on hand. It extends the List class which provides the basic
 * functionality for storage and retrieval of the Driver Object from the list.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.5, 2012-Jun-19
 */

/*
 * Modification Log
 * ***************************************************************************
 * v1.5, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2012-May-31 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * **************************************************************************
 */

public class DriverList extends List {

	/**
	 * Adds a new driver to the list. All the issues of casting are taken care
	 * of within this class.
	 * 
	 * @param driver
	 */
	public void addDriver(Driver driver) {
		appendItemToList((Object) driver);
	}

	/**
	 * @return The driver at the current position pointed to by the
	 *         internal list pointer. Subsequent calls will return the next
	 *         driver object in the list. A null object is returned if list is
	 *         empty or the end of list has been reached.
	 */
	public Driver getNextDriver() {
		return (Driver) getItemFromList();
	}

	/**
	 * Determines whether the Driver OBJ is currently in the list.
	 * 
	 * @param driver
	 * @return true if the driver is in the list, false otherwise.
	 */
	public boolean findDriver(Driver driver) {

		Driver currentObject;
		boolean done = false;
		boolean result = false;

		// Note that this method assumes that all drivers have different
		// identification numbers.

		goToFrontOfList();

		while (!done) {

			currentObject = getNextDriver();

			if (currentObject == null) {

				done = true;

			} else {

				if (driver.getDriverID().compareTo(
						currentObject.getDriverID()) == 0) {

					result = true;

				} // if

			} // if

		} // while

		return (result);

	} // findDriver

	/**
	 * Finds a driver in a list using the driverID as the search key.
	 * 
	 * @param driverID
	 * @return if the current list object's driverID matches the driverID
	 *         passed to the method, the Driver object is returned to the
	 *         caller. Otherwise, returns null.
	 */
	public Driver findDriverByID(String driverID) {

		Driver currentObject = null;
		boolean done = false;
		boolean found = false;

		// Note that this method assumes that all drivers have different
		// identification numbers.

		goToFrontOfList();

		while (!done) {

			currentObject = getNextDriver();

			if (currentObject == null) {

				done = true;

			} else {

				if (currentObject.getDriverID().compareTo(driverID) == 0) {

					found = true;
					done = true;

				} // if

			} // if

		} // while

		if (found) {

			return (currentObject);

		} else {

			return (null);

		} // if

	} // findDriverByID

} // DriverList