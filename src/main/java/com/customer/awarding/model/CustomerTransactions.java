package com.customer.awarding.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Doppala Satish
 */

public class CustomerTransactions {

    private List<CustomerTransaction> customerTransactions;

    public List<CustomerTransaction> build(){
        List<CustomerTransaction> customerTransactions = new ArrayList<CustomerTransaction>();
        return customerTransactions;
    }

    public List<CustomerTransaction> getCustomerTransactions() {
        return customerTransactions;
    }

    public void setCustomerTransactions(List<CustomerTransaction> customerTransactions) {
        this.customerTransactions = customerTransactions;
    }

}
