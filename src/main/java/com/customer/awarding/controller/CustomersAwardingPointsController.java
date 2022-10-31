package com.customer.awarding.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.awarding.model.CustomerAwardingPoints;
import com.customer.awarding.model.CustomerTransactions;
import com.customer.awarding.model.HealthCheck;
import com.customer.awarding.service.CustomersAwardingPointsService;
import com.customer.awarding.service.CustomersAwardingPointsServiceImpl;

/**
 * @author Doppala Satish
 */

@RestController
public class CustomersAwardingPointsController {

    @Value("${Environment}")
    String environment;

    /**
     * API Health check.
     * @return
     */
    @GetMapping(path="/awarding/healthcheck", produces = "application/json")
    public ResponseEntity<HealthCheck> healthCheck() {
        HealthCheck healthCheck = new HealthCheck(environment, "Hello there...! This is a health check for" +
                " customers-awarding-points API....!");
        return ResponseEntity.ok(healthCheck);
    }

    /**
     *
     * @return
     */
    @RequestMapping(path="/awarding/points", method = RequestMethod.POST, produces={"application/json"})
    public ResponseEntity<CustomerAwardingPoints> getCustomerAwardingPoints(
            @RequestBody CustomerTransactions customerTransactions) throws ParseException {

        CustomersAwardingPointsService service = new CustomersAwardingPointsServiceImpl();
        CustomerAwardingPoints customerAwardingPoints =
                service.calculateCustomerAwardingPoints(customerTransactions);

        return ResponseEntity.ok(customerAwardingPoints);
    }
}
