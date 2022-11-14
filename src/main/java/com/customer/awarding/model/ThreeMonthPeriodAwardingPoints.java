package com.customer.awarding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doppala Satish
 */

@JsonPropertyOrder({ "customerAwardingMonthlyPoints", "threeMonthsTotal",})
public class ThreeMonthPeriodAwardingPoints {

    @JsonProperty("customerAwardingMonthlyPoints")
    private List<CustomerAwardingMonthlyPoints> customerAwardingMonthlyPoints;
    @JsonProperty("threeMonthsTotal")
    private int threeMonthsTotal;

    public static ThreeMonthPeriodAwardingPoints build() {

        ThreeMonthPeriodAwardingPoints obj = new ThreeMonthPeriodAwardingPoints();
        obj.setCustomerAwardingMonthlyPoints(new ArrayList<CustomerAwardingMonthlyPoints>());
        return obj;
    }

    public List<com.customer.awarding.model.CustomerAwardingMonthlyPoints> getCustomerAwardingMonthlyPoints() {
        return customerAwardingMonthlyPoints;
    }

    public void setCustomerAwardingMonthlyPoints(List<com.customer.awarding.model.CustomerAwardingMonthlyPoints> customerAwardingMonthlyPoints) {
        this.customerAwardingMonthlyPoints = customerAwardingMonthlyPoints;
    }

    public int getThreeMonthsTotal() {
        return threeMonthsTotal;
    }

    public void setThreeMonthsTotal(int threeMonthsTotal) {
        this.threeMonthsTotal = threeMonthsTotal;
    }
}
