package ca.etsmtl.log430.lab3;

import java.util.Observable;

/**
 * Assigns drivers to deliveries.
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
public class AssignDriverToDelivery extends Communication
{
	public AssignDriverToDelivery(Integer registrationNumber, String componentName) {
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
		Driver myDriver = new Driver();
		Delivery myDelivery = new Delivery();

		if (registrationNumber.compareTo((Integer)notificationNumber) == 0) {
			addToReceiverList("ListDriversComponent");
			addToReceiverList("ListDeliveriesComponent");

			// Display the drivers and prompt the user to pick a driver

			signalReceivers("ListDriversComponent");

			myDriver = menu.pickDriver(CommonData.theListOfDrivers.getListOfDrivers());

			if (myDriver != null) {
				/*
				 * Display the deliveries that are available and ask the user to
				 * pick a delivery to register for
				 */
				signalReceivers("ListDeliveriesComponent");

				myDelivery = menu.pickDelivery(CommonData.theListOfDeliveries.getListOfDeliveries());

				if (myDelivery != null)	{	
					/*
					 * If the selected delivery and driver exist, then complete
					 * the assignment process.
					 */
					myDelivery.assignDriver(myDriver);
					myDriver.assignDelivery(myDelivery);
				} else {
					System.out.println("\n\n *** Delivery not found ***");
				} 
			} else {
				System.out.println("\n\n *** Driver not found ***");
			}
		}
	}
}