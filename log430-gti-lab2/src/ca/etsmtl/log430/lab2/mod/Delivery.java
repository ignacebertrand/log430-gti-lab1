package ca.etsmtl.log430.lab2.mod;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

        private Driver assignedDriver = null;

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
	public boolean assignDriver(Driver driver) {
            if (this.isAssigned()) {
                return false;
            }
            this.assignedDriver = driver;
            return true;
	}

	public Driver getDriverAssigned() {
		return this.assignedDriver;
	}
        
        public void unassignDriver() {
            this.assignedDriver = null;
        }
        
        public boolean isAssigned() {
            return (this.assignedDriver != null);
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
        
        public boolean hasConflictWith(Delivery delivery) {
            Date curEndDate, curStartDate;
            Date newEndDate, newStartDate;
            
            Calendar calendar = new GregorianCalendar();
            calendar.set(GregorianCalendar.SECOND, 0);
            
            calendar.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(this.getDesiredDeliveryTime().substring(0, 1)));
            calendar.set(GregorianCalendar.MINUTE, Integer.parseInt(this.getDesiredDeliveryTime().substring(2, 3)));
            curEndDate = calendar.getTime();
            
            calendar.add(GregorianCalendar.HOUR_OF_DAY, -Integer.parseInt(this.getEstimatedDeliveryDuration().substring(0, 1)));
            calendar.add(GregorianCalendar.MINUTE, -Integer.parseInt(this.getEstimatedDeliveryDuration().substring(2, 3)));
            curStartDate = calendar.getTime();
            
            calendar.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(delivery.getDesiredDeliveryTime().substring(0, 1)));
            calendar.set(GregorianCalendar.MINUTE, Integer.parseInt(delivery.getDesiredDeliveryTime().substring(2, 3)));
            newEndDate = calendar.getTime();
            
            calendar.add(GregorianCalendar.HOUR_OF_DAY, -Integer.parseInt(delivery.getEstimatedDeliveryDuration().substring(0, 1)));
            calendar.add(GregorianCalendar.MINUTE, -Integer.parseInt(delivery.getEstimatedDeliveryDuration().substring(2, 3)));
            newStartDate = calendar.getTime();
            
            boolean checkStart = (newStartDate.compareTo(curStartDate) >= 0 && newStartDate.compareTo(curEndDate) <= 0);
            boolean checkEnd = (newEndDate.compareTo(curStartDate) >= 0 && newEndDate.compareTo(curEndDate) <= 0);
            
            return (checkStart | checkEnd);
        }

} // Delivery class