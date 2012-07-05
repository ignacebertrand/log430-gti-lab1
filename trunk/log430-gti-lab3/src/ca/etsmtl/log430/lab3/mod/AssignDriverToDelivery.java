package ca.etsmtl.log430.lab3.mod;

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
public class AssignDriverToDelivery extends Communication {

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
     * java.lang.Object)
     */
//    public void update(Observable thing, Object notificationNumber) {
//        Menus menu = new Menus();
//        Driver myDriver = new Driver();
//        Delivery myDelivery = new Delivery();
//
//        if (registrationNumber.compareTo((Integer) notificationNumber) == 0) {
//            addToReceiverList("ListDriversComponent");
//            addToReceiverList("ListDeliveriesComponent");
//
//            // Display the drivers and prompt the user to pick a driver
//
//            signalReceivers("ListDriversComponent");
//
//            myDriver = menu.pickDriver(CommonData.theListOfDrivers.getListOfDrivers());
//
//            if (myDriver != null) {
//                /*
//                 * Display the deliveries that are available and ask the user to
//                 * pick a delivery to register for
//                 */
//                signalReceivers("ListDeliveriesComponent");
//
//                myDelivery = menu.pickDelivery(CommonData.theListOfDeliveries.getListOfDeliveries());
//
//                if (myDelivery != null) {
//                    /*
//                     * If the selected delivery and driver exist, then complete
//                     * the assignment process.
//                     */
//                    myDelivery.assignDriver(myDriver);
//                    myDriver.assignDelivery(myDelivery);
//                } else {
//                    System.out.println("\n\n *** Delivery not found ***");
//                }
//            } else {
//                System.out.println("\n\n *** Driver not found ***");
//            }
//        }
//    }
    @Override
    public void update(Observable thing, Object notificationNumber) {
        
        if (this.registrationNumber.equals(notificationNumber)) {
            this.addToReceiverList("ListDriversComponent");
            this.addToReceiverList("ListUnassignedDeliveries");

            this.signalReceivers("ListDriversComponent");            
            Menus menu = new Menus();
            Driver selectedDriver = menu.pickDriver(CommonData.theListOfDrivers.getListOfDrivers());
            
            if (selectedDriver == null) {
                System.out.println("\n\n *** Driver not found ***");
            } else {
                this.signalReceivers("ListUnassignedDeliveries"); 
                Delivery selectedDelivery = menu.pickDelivery(CommonData.theListOfDeliveries.getListOfDeliveries());
                
                if (selectedDelivery == null) {
                    System.out.println("\n\n *** Delivery not found ***");
                } else {
                    try {
                        this.assignDelivery(selectedDriver, selectedDelivery);
                    } catch (Exception ex) {
                        System.out.println("\n\n *** " + ex.getMessage() + " ***");
                    }
                }
            }
            
            this.removeFromReceiverList("ListDriversComponent");
            this.removeFromReceiverList("ListUnassignedDeliveries");
        }
    }
    
    private void assignDelivery(Driver driver, Delivery delivery) throws Exception {
        int hours = 0;
        int minutes = 0;
        int totalMinutes = 0;
        
        DeliveryList assignedDeliveries = driver.getDeliveriesAssigned();
        assignedDeliveries.goToFrontOfList();
        Delivery existing;
        while ((existing = assignedDeliveries.getNextDelivery()) != null) {
            hours = Integer.parseInt(existing.getEstimatedDeliveryDuration().substring(0, 2));
            minutes = Integer.parseInt(existing.getEstimatedDeliveryDuration().substring(2, 4));
            totalMinutes += (hours * 60) + minutes;
            
            if (this.isDeliveriesConflicting(existing, delivery)) {
                throw new Exception("This delivery creates a schedule conflict with another delivery assigned to this driver.");
            }
        }
        
        hours = Integer.parseInt(delivery.getEstimatedDeliveryDuration().substring(0, 2));
        minutes = Integer.parseInt(delivery.getEstimatedDeliveryDuration().substring(2, 4));
        totalMinutes += (hours * 60) + minutes;
        
        if (driver.getType().equalsIgnoreCase("JNR") && totalMinutes > (60 * 12)) {
            throw new Exception("A junior driver can only have a maximum of 12 hours of deliveries assigned per day.");
        } else if (driver.getType().equalsIgnoreCase("SNR") && totalMinutes > (60 * 8)) {
            throw new Exception("A senior driver can only have a maximum of 8 hours of deliveries assigned per day.");
        }
        
        driver.assignDelivery(delivery);
        delivery.assignDriver(driver);
    }
    
    private boolean isDeliveriesConflicting(Delivery existing, Delivery assigned) {
        int curStartTime, curEndTime;
        int newStartTime, newEndTime;

        curEndTime = Integer.parseInt(existing.getDesiredDeliveryTime().substring(0, 2)) * 60;
        curEndTime += Integer.parseInt(existing.getDesiredDeliveryTime().substring(2, 4));

        curStartTime = curEndTime;
        curStartTime -= Integer.parseInt(existing.getEstimatedDeliveryDuration().substring(0, 2)) * 60;
        curStartTime -= Integer.parseInt(existing.getEstimatedDeliveryDuration().substring(2, 4));

        newEndTime = Integer.parseInt(assigned.getDesiredDeliveryTime().substring(0, 2)) * 60;
        newEndTime += Integer.parseInt(assigned.getDesiredDeliveryTime().substring(2, 4));

        newStartTime = newEndTime;
        newStartTime -= Integer.parseInt(assigned.getEstimatedDeliveryDuration().substring(0, 2)) * 60;
        newStartTime -= Integer.parseInt(assigned.getEstimatedDeliveryDuration().substring(2, 4));

        boolean checkStart = (newStartTime >= curStartTime && newStartTime <= curEndTime);
        boolean checkEnd = (newEndTime >= curStartTime && newEndTime <= curEndTime);
        boolean checkOver = (newStartTime <= curStartTime && newEndTime >= curEndTime);

        return (checkStart | checkEnd | checkOver);
    }
}