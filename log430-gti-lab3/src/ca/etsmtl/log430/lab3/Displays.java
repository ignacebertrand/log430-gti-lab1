package ca.etsmtl.log430.lab3;

/**
 * This class displays various types of information on deliveries and drivers
 * (individually and as lists) to the screen.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.5, 2012-Jun-19
 */

/*
 * Modification Log
 * ************************************************************************
 * v1.5, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ************************************************************************
 */

public class Displays {

	private int lineCount = 0;
	private int maxLinesDisplayed = 18;

	/**
	 * Counts the number of lines that has been printed. One a set number of
	 * lines has been printed, the user is asked to press the enter key to
	 * continue. This prevents lines of text from scrolling off of the page
	 * 
	 * @param linesToAdd
	 */
	private void lineCheck(int linesToAdd) {

		Termio terminal = new Termio();

		if (lineCount >= maxLinesDisplayed) {

			lineCount = 0;
			System.out.print("\n*** Press Enter To Continue ***");
			terminal.keyboardReadChar();

		} else {

			lineCount += linesToAdd;

		} // if

	} // LineCheck

	/**
	 * Displays a driver object elements as follows: Driver's first name,
	 * Driver's last name, Driver's ID number, Driver's type (e.g. SNR, JNR)
	 * 
	 * Note that the deliveries previously made by the driver and the deliveries
	 * the driver has been assigned for the current day are not listed.
	 * 
	 * @param driver
	 */
	public void displayDriver(Driver driver) {

		System.out.print(driver.getDriverID() + " " + driver.getFirstName()
				+ " ");
		System.out.println(driver.getLastName() + " " + driver.getType());

	}

	/**
	 * Displays a delivery object elements as follows: Delivery ID, desired
	 * Delivery time, estimated Delivery duration, Delivery address. Note that
	 * the drivers assigned to the delivery are not listed by this method.
	 * 
	 * @param delivery
	 */
	public void displayDelivery(Delivery delivery) {
		System.out.print(delivery.getDeliveryID() + " "
				+ delivery.getDesiredDeliveryTime() + " ");
		System.out.println(delivery.getEstimatedDeliveryDuration() + " - "
				+ delivery.getAddress());
	}

	/**
	 * Lists the drivers that have been assigned to the delivery.
	 * 
	 * @param delivery
	 */
	public void displayDriversAssignedToDelivery(Delivery delivery) {

		boolean done;
		Driver driver;

		System.out.println("\nDrivers assigned to: " + " "
				+ delivery.getDeliveryID() + " " + delivery.getAddress() + " " + delivery.getDesiredDeliveryTime() + " :");
		lineCheck(1);

		System.out
				.println("===========================================================");
		lineCheck(1);

		delivery.getDriversAssigned().goToFrontOfList();
		done = false;

		while (!done) {

			driver = delivery.getDriversAssigned().getNextDriver();

			if (driver == null) {

				done = true;

			} else {

				displayDriver(driver);

			} // if

		} // while

	}

	/**
	 * Lists the deliveries currently assigned to a driver today.
	 * 
	 * @param driver
	 */
	public void displayDeliveriesAssignedToDriver(Driver driver) {

		boolean done;
		Delivery delivery;

		System.out.println("\nDeliveries assigned (today) to : "
				+ driver.getFirstName() + " " + driver.getLastName() + " "
				+ driver.getDriverID());
		lineCheck(2);
		System.out
				.println("========================================================= ");
		lineCheck(1);

		driver.getDeliveriesAssigned().goToFrontOfList();
		done = false;

		while (!done) {

			delivery = driver.getDeliveriesAssigned().getNextDelivery();

			if (delivery == null) {

				done = true;

			} else {

				displayDelivery(delivery);
				lineCheck(2);

			} // if

		} // while

	}

	/**
	 * Displays the drivers in the drivers list. Displays the same information
	 * that is listed in the displayDriver() method listed above.
	 * 
	 * @param list
	 */
	public void displayDriverList(DriverList list) {

		boolean done;
		Driver driver;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();

		done = false;

		while (!done) {

			driver = list.getNextDriver();

			if (driver == null) {

				done = true;

			} else {

				displayDriver(driver);
				lineCheck(1);

			} // if

		} // while

	}

	/**
	 * Displays the deliveries in the deliveries list. Displays the same
	 * information that is listed in the displayDelivery() method listed above.
	 * 
	 * @param list
	 */
	public void displayDeliveryList(DeliveryList list) {

		boolean done;
		Delivery delivery;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();
		done = false;

		while (!done) {

			delivery = list.getNextDelivery();

			if (delivery == null) {

				done = true;

			} else {

				displayDelivery(delivery);
				lineCheck(1);

			} // if

		} // while

	}

} // Display