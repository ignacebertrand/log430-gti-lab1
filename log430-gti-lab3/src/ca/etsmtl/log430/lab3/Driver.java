package ca.etsmtl.log430.lab3;

/**
 * This class defines the Driver object for the system. Besides the static
 * attributes, there are two lists maintained. deliveriesMadeList is a DeliveryList
 * object that maintains a list of deliveries that the driver has made previously.
 * deliveriesAssignedList is also a DeliveryList object that maintains a list of
 * deliveries assigned to the driver for the current day.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.5, 2012-Jun-19
 */

/* Modification Log
 ****************************************************************************
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

 ****************************************************************************/

public class Driver {

	/**
	 * Driver's last name
	 */
	private String lastName;
	
	/**
	 * Driver's first name
	 */
	private String firstName;
	
	/**
	 * Driver's identification number
	 */
	private String driverID;
	
	/**
	 * Driver type (JNR = junior, SNR = senior)
	 */
	private String driverType;

	/**
	 *  List of deliveries previously made (before today) by the driver
	 */
	private DeliveryList deliveriesMadeList = new DeliveryList();

	/**
	 *  List of deliveries assigned to the driver for the current day
	 */
	private DeliveryList deliveriesAssignedList = new DeliveryList();

	/**
	 * Assigns a delivery to a driver.
	 * 
	 * @param delivery
	 */
	public void assignDelivery(Delivery delivery) {

		getDeliveriesAssigned().addDelivery(delivery);

	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}

	public String getDriverID() {
		return driverID;
	}

	public void setType(String drivererType) {
		this.driverType = drivererType;
	}

	public String getType() {
		return driverType;
	}

	public void setDeliveriesMadeList(DeliveryList deliveriesMadeList) {
		this.deliveriesMadeList = deliveriesMadeList;
	}

	public DeliveryList getDeliveriesMadeList() {
		return deliveriesMadeList;
	}

	public void setDeliveriesAssigned(DeliveryList deliveriesAssigned) {
		this.deliveriesAssignedList = deliveriesAssigned;
	}

	public DeliveryList getDeliveriesAssigned() {
		return deliveriesAssignedList;
	}

} // Driver class