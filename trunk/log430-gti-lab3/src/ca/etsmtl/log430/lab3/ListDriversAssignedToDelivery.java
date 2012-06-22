package ca.etsmtl.log430.lab3;

import java.util.Observable;

/**
 * Upon notification, the user is prompted to enter a delivery ID.
 * Drivers assigned to the delivery are then listed.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.4, 2012-Jun-19
 */


/*
 * Modification Log **********************************************************
 * 
 * v1.4, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-14 - Various refactorings for new lab.
 * 
 * v1.2, R. Champagne, 2011-Feb-24 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.1, R. Champagne, 2002-Jun-19 - Adapted for use at ETS.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class ListDriversAssignedToDelivery extends Communication {

	public ListDriversAssignedToDelivery(Integer registrationNumber,
			String componentName) {
		super(registrationNumber, componentName);
	}

	/**
	 * The update() method is an abstract method that is called whenever the
	 * notifyObservers() method is called by the Observable class. First we
	 * check to see if the NotificationNumber is equal to this thread's
	 * RegistrationNumber. If it is, then we execute.
	 * 
	 * @see ca.etsmtl.log430.lab3.Communication#update(java.util.Observable,
	 *      java.lang.Object)
	 */
	public void update(Observable thing, Object notificationNumber) {
		Menus menu = new Menus();
		Displays display = new Displays();
		Delivery myDelivery = new Delivery();

		if (registrationNumber.compareTo((Integer) notificationNumber) == 0) {
			addToReceiverList("ListDeliveriesComponent");
			signalReceivers("ListDeliveriesComponent");

			// Next we ask them to pick a delivery
			myDelivery = menu.pickDelivery(CommonData.theListOfDeliveries
					.getListOfDeliveries());

			if (myDelivery != null) {
				/*
				 * If the delivery is valid (exists in the list), them we display
				 * the drivers that are assigned to it.
				 */
				display.displayDriversAssignedToDelivery(myDelivery);
			} else {
				System.out.println("\n\n *** Delivery not found ***");
			}
		}
		removeFromReceiverList("ListDeliveriesComponent");
	}
}