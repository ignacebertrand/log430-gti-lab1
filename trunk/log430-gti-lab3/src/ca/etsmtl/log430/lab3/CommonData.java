package ca.etsmtl.log430.lab3;

/**
 * Contains data that is used (directly or indirectly) by all
 * classes.
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
 * 
 * ***************************************************************************
 */

public class CommonData {
	/** The file that contains the information for deliveries. */
	private String defaultDeliveryFile = "deliveries.txt";

	/** The file that contains the information for drivers. */
	private String defaultDriverFile = "drivers.txt";
	
	/** Variable used to determine if initialization has been realized. */
	private static boolean initialized = false;

	/** Object that reads the deliveries from a file. */
	static DeliveryReader theListOfDeliveries;

	/** Object that reads the drivers from a file. */
	static DriverReader theListOfDrivers;

	/** A list of components */
	private static ComponentList systemComponents;

	/**
	 * Initializes the driver and delivery list using the default lists
	 */
	public CommonData() {
		if (!initialized) {
			theListOfDeliveries = new DeliveryReader(defaultDeliveryFile);
			theListOfDrivers = new DriverReader(defaultDriverFile);
			systemComponents = new ComponentList();
			initialized = true;
		} // if

		// If either list is null, you are in trouble.
		if (theListOfDeliveries.getListOfDeliveries() == null) {
			System.out.println("\n\n *** The delivery list is empty ***");
		}
		
		if (theListOfDrivers.getListOfDrivers() == null) {
			System.out.println("\n\n *** The driver list is empty ***");
		}
	}

	/**
	 * Updates an internal list of system components. This lets system
	 * components know the identity of other components in the system
	 * 
	 * @param component
	 */
	public void registerComponent(Communication component) {
		systemComponents.addComponent(component);
	}

	/**
	 * @param componentName
	 * @return object that corresponds to the object's instance name
	 */
	public Communication getComponent(String componentName) {
		return (systemComponents.getComponent(componentName));
	}

	/**
	 * @param componentName
	 * @return registration ID that corresponds to the object's instance name
	 */
	public Integer getComponentID(String componentName) {
		return (systemComponents.getComponentID(componentName));
	}
}