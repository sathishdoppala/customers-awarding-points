package com.customer.awarding.service;

import com.customer.awarding.model.CustomerAwardingPoints;
import com.customer.awarding.model.CustomerTransactions;

import java.text.ParseException;

/**
 * @author Doppala Satish
 */

public interface CustomersAwardingPointsService {
    public CustomerAwardingPoints calculateCustomerAwardingPoints(CustomerTransactions customerTransactions) throws ParseException;
}
