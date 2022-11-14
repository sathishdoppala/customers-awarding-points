package com.customer.awarding.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doppala Satish
 */

public class CustomerTransactions {

    private List<CustomerTransaction> customerTransactions;

    public static CustomerTransactions build() {
        CustomerTransactions customerTransactions = new CustomerTransactions();
        List<CustomerTransaction> customerTransactionList = new ArrayList<CustomerTransaction>();
        customerTransactions.setCustomerTransactions(customerTransactionList);
        return customerTransactions;
    }

    public List<CustomerTransaction> getCustomerTransactions() {
        return customerTransactions;
    }

    public void setCustomerTransactions(List<CustomerTransaction> customerTransactions) {
        this.customerTransactions = customerTransactions;
    }

}
