package ca.etsmtl.log430.lab3.mod;

import java.util.Observable;

public class ListDeliveriesMadeByDriver extends Communication {
    
    public ListDeliveriesMadeByDriver(Integer registrationNumber, String componentName) {
        super(registrationNumber, componentName);
    }

    @Override
    public void update(Observable thing, Object notificationNumber) {
        
        if (this.registrationNumber.equals(notificationNumber)) {
            Menus menu = new Menus();
            Displays display = new Displays();
        
            this.addToReceiverList("ListDriversComponent");
            
            this.signalReceivers("ListDriversComponent");
            
            Driver selectedDriver = menu.pickDriver(CommonData.theListOfDrivers.getListOfDrivers());
            if (selectedDriver != null) {
                display.displayDeliveriesMadeByDriver(selectedDriver);
            } else {
                System.out.println("\n\n *** Driver not found ***");
            }
            
            this.removeFromReceiverList("ListDriversComponent");
        }
    }
}
