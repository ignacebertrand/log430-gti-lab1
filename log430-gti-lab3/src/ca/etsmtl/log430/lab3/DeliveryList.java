package ca.etsmtl.log430.lab3;

/**
 * This class is used by various other classes that need to keep a list of
 * deliveries. It extends the List class which provides the basic functionality for
 * storage and retrieval of the Delivery object from the list.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.5, 2012-Jun-19
 */

/*
 * Modification Log
 * ****************************************************************************
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
 * ***************************************************************************
 */

public class DeliveryList extends List {

	public DeliveryList() {
		super();
	}

	/**
	 * @param delivery
	 *            New delivery to be added to the list. All the issues of casting
	 *            are taken care of within this class.
	 */
	public void addDelivery(Delivery delivery) {
		appendItemToList((Object) delivery);
	}

	/**
	 * @return The delivery pointed at the current position pointed to by the
	 *         internal list pointer of the internal list. Subsequent calls will
	 *         return the next Delivery object in the list. A null object is
	 *         returned if list is empty or the end of list has been reached.
	 */
	public Delivery getNextDelivery() {
		return (Delivery) getItemFromList();
	}

	/**
	 * This method assumes that all deliveries have different identification
	 * numbers.
	 * 
	 * @param delivery
	 * @return A Delivery instance if found in the list based on specified
	 *         criteria, null otherwise.
	 */
	public boolean findDelivery(Delivery delivery) {

		Delivery currentObject;
		boolean done = false;
		boolean result = false;

		goToFrontOfList();

		while (!done) {

			currentObject = getNextDelivery();

			if (currentObject == null) {

				done = true;

			} else {
				if (delivery.getDeliveryID().compareToIgnoreCase(
						currentObject.getDeliveryID()) == 0) {

					result = true;

				} // if

			} // if

		} // while

		return (result);

	}

	public Delivery findDelivery(String deliveryID) {

		Delivery currentObject;
		Delivery returnValue = null;
		boolean done = false;

		goToFrontOfList();

		while (!done) {

			currentObject = getNextDelivery();

			if (currentObject == null) {

				done = true;

			} else {
				if (deliveryID.compareToIgnoreCase(currentObject.getDeliveryID()) == 0) {
					returnValue = currentObject;
					done = true;

				} // if

			} // if

		} // while
		return(returnValue);
	}
	
} // DeliveryList