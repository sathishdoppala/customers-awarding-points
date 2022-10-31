package com.customer.awarding.service;

import com.customer.awarding.model.CustomerAwardingMonthlyPoints;
import com.customer.awarding.model.CustomerAwardingPoints;
import com.customer.awarding.model.CustomerTransaction;
import com.customer.awarding.model.CustomerTransactions;

import java.text.ParseException;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Doppala Satish
 */

public class CustomersAwardingPointsServiceImpl implements CustomersAwardingPointsService {

    int consolidatedPoints = 0;
    private CustomerAwardingPoints customerAwardingPoints = CustomerAwardingPoints.build();

    /**
     *
     * @param customerTransactions
     * @return
     * @throws ParseException
     */
    @Override
    public CustomerAwardingPoints calculateCustomerAwardingPoints(CustomerTransactions customerTransactions)
            throws ParseException {
        int counter = 0;
        int totalPoints = 0;
        int previousMonth = 0;

        List<CustomerTransaction> customerTransactionList = customerTransactions.getCustomerTransactions();

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

        if(null != customerTransactionList && !customerTransactionList.isEmpty()){

            for(CustomerTransaction customerTransaction : customerTransactionList) {
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

                if(customerTransactionList.size() == counter){
                    addNewCustomerAwardingMonthlyPoints(String.valueOf(currentMonth), totalPoints);
                }

                previousMonth = currentMonth;

            }
            customerAwardingPoints.setConsolidatedPoints(consolidatedPoints);
        }
        return customerAwardingPoints;
    }

    /**
     *
     * @param amount
     * @return
     */
    private int getAwardPoints(int amount) {
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
     *
     * @param month
     * @param points
     */
    private void addNewCustomerAwardingMonthlyPoints(String month, int points) {
        int intMonth = Integer.parseInt(month);
        CustomerAwardingMonthlyPoints customerAwardingMonthlyPoints = new CustomerAwardingMonthlyPoints();
        customerAwardingMonthlyPoints.setMonth(String.valueOf(Month.of(intMonth)));
        customerAwardingMonthlyPoints.setTotalPoints(points);
        customerAwardingPoints.getCustomerAwardingMonthlyPoints().add(customerAwardingMonthlyPoints);
    }
}
