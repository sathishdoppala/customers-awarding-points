package com.customer.awarding.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doppala Satish
 */

public class CustomerAwardingPoints {


    private List<CustomerAwardingMonthlyPoints> customerAwardingMonthlyPoints;
    private int consolidatedPoints;

    public static CustomerAwardingPoints build() {
        List<CustomerAwardingMonthlyPoints> customerAwardingMonthlyPointsList =
                new ArrayList<CustomerAwardingMonthlyPoints>();
        CustomerAwardingPoints customerAwardingPoints = new CustomerAwardingPoints();
        customerAwardingPoints.setCustomerAwardingMonthlyPoints(customerAwardingMonthlyPointsList);
        customerAwardingPoints.setConsolidatedPoints(0);
        return customerAwardingPoints;
    }
    public List<CustomerAwardingMonthlyPoints> getCustomerAwardingMonthlyPoints() {
        return customerAwardingMonthlyPoints;
    }

    public void setCustomerAwardingMonthlyPoints(List<CustomerAwardingMonthlyPoints> customerAwardingMonthlyPoints) {
        this.customerAwardingMonthlyPoints = customerAwardingMonthlyPoints;
    }

    public int getConsolidatedPoints() {
        return consolidatedPoints;
    }

    public void setConsolidatedPoints(int consolidatedPoints) {
        this.consolidatedPoints = consolidatedPoints;
    }
}
