package ca.etsmtl.log430.lab2;

/**
 * This class defines the Delivery object for the system.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.5, 2012-May-31
 */

/*
 * Modification Log **********************************************************
 * 
 * v1.5, R. Champagne, 2012-May-31 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2011-Feb-02 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.2, R. Champagne, 2002-May-21 - Adapted for use at ETS.
 * 
 * v1.1, G.A.Lewis, 01/25/2001 - Bug in second constructor. Removed null
 * assignment to deliveryID after being assigned a value.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class Delivery {

	/**
	 * Delivery ID
	 */
	private String deliveryID;

	/**
	 * Delivery address
	 */
	private String address;

	/**
	 * Time at which the delivery should be planned
	 */
	private String desiredDeliveryTime;

	/**
	 * Start time of the course
	 */
	private String estimatedDeliveryDuration;

	/**
	 * List of teachers assigned to the course 
	 */
	private DriverList driversAssigned = new DriverList();

	public Delivery() {
		this(null);
	}

	public Delivery(String deliveryID) {
		this(deliveryID, null);
	}

	public Delivery(String deliveryID, String estimatedDuration) {
		this.setDeliveryID(deliveryID);
		this.setDesiredDeliveryTime(null);
		this.setEstimatedDeliveryDuration(estimatedDuration);
	}

	/**
	 * Assign a teacher to a class.
	 * 
	 * @param driver
	 */
	public void assignDriver(Driver driver) {
		driversAssigned.addDriver(driver);
	}

	public void setDeliveryID(String deliveryID) {
		this.deliveryID = deliveryID;
	}

	public String getDeliveryID() {
		return deliveryID;
	}

	public void setDesiredDeliveryTime(String time) {
		this.desiredDeliveryTime = time;
	}

	public String getDesiredDeliveryTime() {
		return desiredDeliveryTime;
	}

	public void setEstimatedDeliveryDuration(String duration) {
		this.estimatedDeliveryDuration = duration;
	}

	public String getEstimatedDeliveryDuration() {
		return estimatedDeliveryDuration;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DriverList getDriversAssigned() {
		return driversAssigned;
	}

	public void setDriversAssigned(DriverList driversAssigned) {
		this.driversAssigned = driversAssigned;
	}

} // Delivery class