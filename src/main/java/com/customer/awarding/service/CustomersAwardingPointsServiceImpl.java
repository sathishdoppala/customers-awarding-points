package com.customer.awarding.service;

import com.customer.awarding.model.*;

import java.text.ParseException;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * Note: Making some methods public, to get those accessed from Test class.
 * @author Doppala Satish
 */

public class CustomersAwardingPointsServiceImpl implements CustomersAwardingPointsService {

    private int consolidatedPoints = 0;
    private int threeMonthCounter = 0;
    private CustomerAwardingPoints customerAwardingPoints;
    private ThreeMonthPeriodAwardingPoints threeMonthPeriodAwardingPoints;

    public CustomersAwardingPointsServiceImpl() {
    }


    /**
     *
     * @param customerTransactions
     * @return
     * @throws ParseException
     */
    @Override
    public CustomerAwardingPoints calculateCustomerAwardingPoints(CustomerTransactions customerTransactions){
        int counter = 0;
        int totalPoints = 0;
        int previousMonth = 0;
        this.customerAwardingPoints = CustomerAwardingPoints.build();

        try {
            List<CustomerTransaction> customerTransactionList = customerTransactions.getCustomerTransactions();

            if(null != customerTransactionList && !customerTransactionList.isEmpty()) {

                // Sort the List to make the transactions in order.
                Collections.sort(customerTransactionList, new Comparator<CustomerTransaction>() {
                    @Override
                    public int compare(CustomerTransaction c1, CustomerTransaction c2) {
                        return c1.getTransactionDate().compareTo(c2.getTransactionDate());
                    }
                });

                // Printing Sorted Dates.
                for(CustomerTransaction customerTransaction : customerTransactionList) {
                    System.out.println(customerTransaction.getTransactionDate());
                }

                // Loop through each transaction to calculate points.
                for(CustomerTransaction customerTransaction : customerTransactionList) {

                    // Increase the counter until it reaches the size of the list.
                    counter++;

                    // Get Month of the date to calculate the points for each month.
                    int currentMonth = customerTransaction.getTransactionDate().getMonth()+1;

                    // If the transaction belongs to the same month, keep adding the points.
                    if(previousMonth == 0 || previousMonth == currentMonth) {
                        totalPoints += getAwardPoints(customerTransaction.getTransactionAmount());
                    } else {
                        addNewCustomerAwardingMonthlyPoints(String.valueOf(previousMonth), totalPoints);
                        totalPoints = getAwardPoints(customerTransaction.getTransactionAmount());
                    }

                /* When counter reaches the size of the list,
                that means the transaction is the last one and add this object to result.
                If not the last object will be missed */
                    if(customerTransactionList.size() == counter){
                        addNewCustomerAwardingMonthlyPoints(String.valueOf(currentMonth), totalPoints);
                    }

                    previousMonth = currentMonth;

                }
                customerAwardingPoints.setConsolidatedPoints(consolidatedPoints);
            } else {
                customerAwardingPoints = CustomerAwardingPoints.build();
                customerAwardingPoints.setErrorCode("ER-000");
                customerAwardingPoints.setErrorType("INTERNAL");
                customerAwardingPoints.setMessage("Empty Request. Please try with valid Request.");
            }
        } catch(Exception ex) {
            customerAwardingPoints = CustomerAwardingPoints.build();
            customerAwardingPoints.setErrorCode("ER-111");
            customerAwardingPoints.setErrorType("SERVICE");
            customerAwardingPoints.setMessage("Exception occurred while calculating the Awarding Points."+"\n"+
                    " Exception Message: "+ex.getMessage());
        }

        return customerAwardingPoints;
    }

    /**
     * getAwardPoints calculates the awarding points.
     *
     * @param amount
     * @return
     */
    public int getAwardPoints(int amount) {
        int awardPoints = 0;
        // 1 point for each dollar spent over $50.
        if(amount > 50 && amount <= 100) {
            awardPoints = (1 * (amount - 50));
        } else if(amount > 100) {
            // 2 points for each dollar spent over $100 + 1 point for each dollar spent over $50.
            awardPoints = ((amount - 100) * 2) + 50;
        }
        consolidatedPoints += awardPoints;
        return awardPoints;
    }

    /**
     * addNewCustomerAwardingMonthlyPoints creates new Monthly and Three month objects and sets the values.
     * @param month
     * @param points
     */
    private void addNewCustomerAwardingMonthlyPoints(String month, int points) {

        //Create a new Object for every three months.
        if(threeMonthCounter == 0){
            threeMonthPeriodAwardingPoints = ThreeMonthPeriodAwardingPoints.build();
            customerAwardingPoints.getThreeMonthPeriodAwardingPoints().add(threeMonthPeriodAwardingPoints);
        }
        int intMonth = Integer.parseInt(month);
        CustomerAwardingMonthlyPoints customerAwardingMonthlyPoints = new CustomerAwardingMonthlyPoints();
        customerAwardingMonthlyPoints.setMonth(String.valueOf(Month.of(intMonth)));
        customerAwardingMonthlyPoints.setTotalPoints(points);
        threeMonthPeriodAwardingPoints.getCustomerAwardingMonthlyPoints().add(customerAwardingMonthlyPoints);
        int threeMonthsPoints = threeMonthPeriodAwardingPoints.getThreeMonthsTotal() + points;
        threeMonthPeriodAwardingPoints.setThreeMonthsTotal(threeMonthsPoints);

        // Reset three month counter to "0" for every three months.
        if(threeMonthCounter == 2){
            threeMonthCounter = 0;
        } else {
            threeMonthCounter++;
        }

    }
}
