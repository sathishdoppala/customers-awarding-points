package com.customer.awarding.controller;

import com.customer.awarding.model.CustomerAwardingPoints;
import com.customer.awarding.model.CustomerTransactions;
import com.customer.awarding.model.HealthCheck;
import com.customer.awarding.service.CustomersAwardingPointsService;
import com.customer.awarding.service.CustomersAwardingPointsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author Doppala Satish
 */

@RestController
public class CustomersAwardingPointsController {

    @Value("${Environment}")
    private String environment;

    private CustomersAwardingPointsService service;

    public CustomersAwardingPointsController() {
        this.service = new CustomersAwardingPointsServiceImpl();
    }

    /**
     * Parameterized constructor created for Mock Testing purpose, to pass the Mocked service.
     * @param service
     */
    public CustomersAwardingPointsController(CustomersAwardingPointsService service) {
        this.service = service;
    }

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
    @PostMapping(path="/awarding/points", produces = "application/json")
    public ResponseEntity<CustomerAwardingPoints> getCustomerAwardingPoints(
            @RequestBody CustomerTransactions customerTransactions) throws ParseException {

        CustomerAwardingPoints customerAwardingPoints = null;

        customerAwardingPoints =
                service.calculateCustomerAwardingPoints(customerTransactions);

        if(null != customerAwardingPoints && !customerAwardingPoints.getThreeMonthPeriodAwardingPoints().isEmpty()) {

            return ResponseEntity.ok(customerAwardingPoints);

        } else if(null != customerAwardingPoints && null != customerAwardingPoints.getErrorCode()) {

            return new ResponseEntity(customerAwardingPoints, HttpStatus.INTERNAL_SERVER_ERROR);

        } else {

            customerAwardingPoints = CustomerAwardingPoints.build();
            customerAwardingPoints.setErrorCode("ER-222");
            customerAwardingPoints.setErrorType("SERVICE");
            customerAwardingPoints.setMessage("Empty OR no response from service. Please try again.");
            return new ResponseEntity(customerAwardingPoints, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
