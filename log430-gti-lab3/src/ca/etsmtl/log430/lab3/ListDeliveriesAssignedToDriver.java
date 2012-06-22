package ca.etsmtl.log430.lab3;

import java.util.Observable;

/**
 * Upon notification, first lists the drivers and asks the user to pick one by
 * specifying its ID. If the driver's ID is valid, then the deliveries assigned
 * to that driver are listed.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.4, 2012-Jun-19
 */

/*
 * Modification Log **********************************************************
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

public class ListDeliveriesAssignedToDriver extends Communication {

	public ListDeliveriesAssignedToDriver(Integer registrationNumber,
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
		Driver myDriver = new Driver();

		if (registrationNumber.compareTo((Integer) notificationNumber) == 0) {
			/*
			 * First we use a Displays object to list all of the drivers. Then
			 * we ask the user to pick a driver using a Menus object.
			 */
			addToReceiverList("ListDriversComponent");
			signalReceivers("ListDriversComponent");
			myDriver = menu.pickDriver(CommonData.theListOfDrivers
					.getListOfDrivers());

			/*
			 * If the user selected an invalid driver, then a message is
			 * printed to the terminal.
			 */
			if (myDriver != null) {
				display.displayDeliveriesAssignedToDriver(myDriver);
			} else {
				System.out.println("\n\n *** Driver not found ***");
			}
		}
		removeFromReceiverList("ListDriversComponent");
	}
}