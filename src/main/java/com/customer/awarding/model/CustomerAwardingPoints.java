package com.customer.awarding.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doppala Satish
 */

public class CustomerAwardingPoints {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ThreeMonthPeriodAwardingPoints> threeMonthPeriodAwardingPoints;
    private int consolidatedPoints;

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public static CustomerAwardingPoints build() {
        List<ThreeMonthPeriodAwardingPoints> threeMonthPeriodAwardingPoints =
                new ArrayList<ThreeMonthPeriodAwardingPoints>();
        CustomerAwardingPoints customerAwardingPoints = new CustomerAwardingPoints();
        customerAwardingPoints.setThreeMonthPeriodAwardingPoints(threeMonthPeriodAwardingPoints);
        customerAwardingPoints.setConsolidatedPoints(0);
        return customerAwardingPoints;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ThreeMonthPeriodAwardingPoints> getThreeMonthPeriodAwardingPoints() {
        return threeMonthPeriodAwardingPoints;
    }

    public void setThreeMonthPeriodAwardingPoints(List<ThreeMonthPeriodAwardingPoints> threeMonthPeriodAwardingPoints) {
        this.threeMonthPeriodAwardingPoints = threeMonthPeriodAwardingPoints;
    }

    public int getConsolidatedPoints() {
        return consolidatedPoints;
    }

    public void setConsolidatedPoints(int consolidatedPoints) {
        this.consolidatedPoints = consolidatedPoints;
    }
}
