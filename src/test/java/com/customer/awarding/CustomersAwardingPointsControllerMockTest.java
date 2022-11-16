package com.customer.awarding;

import com.customer.awarding.controller.CustomersAwardingPointsController;
import com.customer.awarding.model.CustomerAwardingMonthlyPoints;
import com.customer.awarding.model.CustomerAwardingPoints;
import com.customer.awarding.model.CustomerTransactions;
import com.customer.awarding.model.ThreeMonthPeriodAwardingPoints;
import com.customer.awarding.service.CustomersAwardingPointsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Doppala Satish
 */

public class CustomersAwardingPointsControllerMockTest {

    private CustomersAwardingPointsController classUnderTest;

    private ResponseEntity<CustomerAwardingPoints> responseEntity;

    @Mock
    private CustomerTransactions customerTransactions;

    @Mock
    private CustomersAwardingPointsService customersAwardingPointsService;


    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        classUnderTest = Mockito.spy(new CustomersAwardingPointsController(customersAwardingPointsService));
    }

    /********************************************************************************************************
     Note:- I did not see much scope for Mockito in our service, because the response is not been used by any other service
     OR We are not making any other service calls from this service. So I just made up something.
     ********************************************************************************************************/

    @Test
    public void getCustomerAwardingPointsHappyPath() throws ParseException {

        givenAnyMockRequestTheServiceResponseIs(prepareCustomerAwardingPointsResponse());

        whenGetCustomerAwardingPointsIsCalled();

        thenTheResponseReceivedFromTheServiceShouldMatchTheMockedResponse();
    }

    @Test
    public void getCustomerAwardingPointsWhenTheServiceResponseIsNull() throws ParseException {

        givenAnyMockRequestTheServiceResponseIs(null);

        whenGetCustomerAwardingPointsIsCalled();

        thenTheResponseReceivedFromTheServiceShouldMatchTheExceptionResponse();
    }


    private void givenAnyMockRequestTheServiceResponseIs(CustomerAwardingPoints serViceResponse) {
        Mockito.when(customersAwardingPointsService.calculateCustomerAwardingPoints(customerTransactions))
                .thenReturn(serViceResponse);
    }

    private void whenGetCustomerAwardingPointsIsCalled() throws ParseException {
        responseEntity = classUnderTest.getCustomerAwardingPoints(customerTransactions);
    }

    private void thenTheResponseReceivedFromTheServiceShouldMatchTheExceptionResponse() {
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

        CustomerAwardingPoints response = responseEntity.getBody();

        assertNotNull(response);
        assertEquals("ER-222", response.getErrorCode());
        assertEquals("SERVICE", response.getErrorType());
        assertEquals("Empty OR no response from service. Please try again.", response.getMessage());
    }


    private void thenTheResponseReceivedFromTheServiceShouldMatchTheMockedResponse() {

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        CustomerAwardingPoints response = responseEntity.getBody();

        assertNotNull(response);
        assertNotNull(response.getThreeMonthPeriodAwardingPoints());

        assertEquals("Expected size is 2", 2,
                response.getThreeMonthPeriodAwardingPoints().size());

        /*************************************************************************
         Check All the values in the first ThreeMonthPeriodAwardingPoints object.
         **************************************************************************/
        assertNotNull(response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints());
        assertEquals("Expected size is 3", 3,
                response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().size());

        assertEquals("JANUARY",
                response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(0).getMonth());
        assertEquals(20,
                response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(0).getTotalPoints());

        assertEquals("MARCH",
                response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(1).getMonth());
        assertEquals(20,
                response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(1).getTotalPoints());

        assertEquals("APRIL",
                response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(2).getMonth());
        assertEquals(10,
                response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(2).getTotalPoints());

        assertEquals("Three Months total points", 50,
                response.getThreeMonthPeriodAwardingPoints().get(0).getThreeMonthsTotal());

        /**************************************************************************
         Check All the values in the second ThreeMonthPeriodAwardingPoints object.
         *************************************************************************/
        assertNotNull(response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints());
        assertEquals("Expected size is 2", 2,
                response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().size());

        assertEquals("AUGUST",
                response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().get(0).getMonth());
        assertEquals(110,
                response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().get(0).getTotalPoints());

        assertEquals("OCTOBER",
                response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().get(1).getMonth());
        assertEquals(110,
                response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().get(1).getTotalPoints());


        assertEquals("Two Months total points", 220,
                response.getThreeMonthPeriodAwardingPoints().get(1).getThreeMonthsTotal());

        assertEquals("Total Points", 270, response.getConsolidatedPoints());
    }

    private CustomerAwardingPoints prepareCustomerAwardingPointsResponse() {

        /***********************************************************************************
         Note:  Refer TestCaseResponse1.json under
         customers-awarding-points/src/test/java/com/customer/awarding/data/
         **********************************************************************************/

        CustomerAwardingPoints expectedResponse = CustomerAwardingPoints.build();
        ThreeMonthPeriodAwardingPoints threeMonthsPoints1 = ThreeMonthPeriodAwardingPoints.build();
        ThreeMonthPeriodAwardingPoints threeMonthsPoints2 = ThreeMonthPeriodAwardingPoints.build();

        CustomerAwardingMonthlyPoints obj1 = new CustomerAwardingMonthlyPoints();
        obj1.setMonth("JANUARY");
        obj1.setTotalPoints(20);

        CustomerAwardingMonthlyPoints obj2 = new CustomerAwardingMonthlyPoints();
        obj2.setMonth("MARCH");
        obj2.setTotalPoints(20);

        CustomerAwardingMonthlyPoints obj3 = new CustomerAwardingMonthlyPoints();
        obj3.setMonth("APRIL");
        obj3.setTotalPoints(10);

        threeMonthsPoints1.getCustomerAwardingMonthlyPoints().add(0, obj1);
        threeMonthsPoints1.getCustomerAwardingMonthlyPoints().add(1, obj2);
        threeMonthsPoints1.getCustomerAwardingMonthlyPoints().add(2, obj3);
        threeMonthsPoints1.setThreeMonthsTotal(50);

        CustomerAwardingMonthlyPoints obj4 = new CustomerAwardingMonthlyPoints();
        obj4.setMonth("AUGUST");
        obj4.setTotalPoints(110);

        CustomerAwardingMonthlyPoints obj5 = new CustomerAwardingMonthlyPoints();
        obj5.setMonth("OCTOBER");
        obj5.setTotalPoints(110);

        threeMonthsPoints2.getCustomerAwardingMonthlyPoints().add(0, obj4);
        threeMonthsPoints2.getCustomerAwardingMonthlyPoints().add(1, obj5);
        threeMonthsPoints2.setThreeMonthsTotal(220);

        expectedResponse.getThreeMonthPeriodAwardingPoints().add(0, threeMonthsPoints1);
        expectedResponse.getThreeMonthPeriodAwardingPoints().add(1, threeMonthsPoints2);
        expectedResponse.setConsolidatedPoints(270);

        return expectedResponse;
    }

}
