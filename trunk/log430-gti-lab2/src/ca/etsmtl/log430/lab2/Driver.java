package ca.etsmtl.log430.lab2;

/**
 * This class defines the Driver object for the system. Besides the static
 * attributes, there are two lists maintained. deliveriesMadeList is a DeliveryList
 * object that maintains a list of courses that the student has taken.
 * deliveriesAssignedList is also a DeliveryList object that maintains a list of
 * deliveries assigned to the driver for the current day.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.4, 2012-May-31
 */

/* Modification Log
 ****************************************************************************
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
	 * Driver's Last Name
	 */
	private String lastName;
	
	/**
	 * Driver's First Name
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
	 * Registers a student for a course by adding a student for a course.
	 * 
	 * @param delivery
	 */
	public void assignDelivery(Delivery delivery) {

		getDeliveriesAssigned().addDelivery(delivery);

	} // Register

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