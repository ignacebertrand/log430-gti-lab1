package ca.etsmtl.log430.lab3;

/**
 * Acts as the system executive. It provides the primary user interface.
 * 
 * Pseudo Code:
 * 
 * <pre>
 * 	add components that I want to receive my signals to the receiver list.
 * 	while !done
 * 		Present Menu
 * 	    if user choice = 1, signal "ListDriversComponent"
 *    	if user choice = 2, signal "ListDeliveriesComponent" 
 * 	    if user choice = 3, signal "ListDeliveriesAssignedToDriverComponent"
 * 	    if user choice = 4, signal "ListDriversAssignedToDeliveryComponent"
 * 	    if user choice = 5, signal "AssignDriverToDelivery" 
 * 	    if user choice = x, you are done
 * 	end while
 * </pre>
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.3, 2012-Jun-19
 */

/*
 * Modification Log **********************************************************
 * 
 * v1.3, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.2, R. Champagne, 2011-Feb-24 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.1, R. Champagne, 2002-Jun-19 - Adapted for use at ETS.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class Executive extends Communication {

	public Executive(Integer registrationNumber, String componentName) {
		super(registrationNumber, componentName);
	}

	/**
	 * The main execution loop.
	 */
	public void execute() {
		char userChoice;
		boolean done = false;
		Menus menu = new Menus();

		addToReceiverList("ListDriversComponent");
		addToReceiverList("ListDeliveriesComponent");
		addToReceiverList("ListDeliveriesAssignedToDriverComponent");
		addToReceiverList("ListDriversAssignedToDeliveryComponent");
		addToReceiverList("AssignDriverToDelivery");

		while (!done) {
			userChoice = menu.mainMenu();

			switch (userChoice) {
			case '1':
				signalReceivers("ListDriversComponent");
				break;

			case '2':
				signalReceivers("ListDeliveriesComponent");
				break;

			case '3':
				signalReceivers("ListDeliveriesAssignedToDriverComponent");
				break;

			case '4':
				signalReceivers("ListDriversAssignedToDeliveryComponent");
				break;

			case '5':
				signalReceivers("AssignDriverToDelivery");
				break;

			case 'X':
			case 'x':
				done = true;
				break;

			}
		}
	}
}