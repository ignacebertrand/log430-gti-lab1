package ca.etsmtl.log430.lab3.mod;

import java.util.Observable;

public class ListUnassignedDeliveries extends Communication {
    
    public ListUnassignedDeliveries(Integer registrationNumber, String componentName) {
        super(registrationNumber, componentName);
    }
    
    @Override
    public void update(Observable thing, Object notificationNumber) {

        if (this.registrationNumber.equals(notificationNumber)) {
            Displays display = new Displays();
            display.displayUnassignedDeliveries(CommonData.theListOfDeliveries.getListOfDeliveries());
        }
    }
}
