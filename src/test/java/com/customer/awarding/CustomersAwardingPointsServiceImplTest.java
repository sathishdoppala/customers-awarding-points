package com.customer.awarding;


import com.customer.awarding.model.CustomerAwardingMonthlyPoints;
import com.customer.awarding.model.CustomerAwardingPoints;
import com.customer.awarding.model.CustomerTransaction;
import com.customer.awarding.model.CustomerTransactions;
import com.customer.awarding.model.ThreeMonthPeriodAwardingPoints;
import com.customer.awarding.service.CustomersAwardingPointsServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Doppala Satish
 */

public class CustomersAwardingPointsServiceImplTest {

    private int amount;
    private int points;
    private CustomerTransactions request;
    private CustomerAwardingPoints response;
    private CustomerTransaction transaction1;
    private CustomerTransaction transaction2;
    private CustomerTransaction transaction3;
    private CustomerTransaction transaction4;
    private CustomerTransaction transaction5;
    private CustomerTransaction transaction6;
    private CustomersAwardingPointsServiceImpl classUnderTest;

    @Before
    public void setup() {
        classUnderTest = new CustomersAwardingPointsServiceImpl();
    }


    @Test
    public void getAwardPointsWhenTheAmountIsBelowFifty() {

        givenDollarAmount(30);

        whenGetAwardPointsIsCalled();

        thenTheExpectedPointsAre(0);
    }

    @Test
    public void getAwardPointsWhenTheAmountIsFifty() {

        givenDollarAmount(50);

        whenGetAwardPointsIsCalled();

        thenTheExpectedPointsAre(0);
    }

    @Test
    public void getAwardPointsWhenTheAmountIsAboveFiftyAndBelowHundred() {

        givenDollarAmount(70);

        whenGetAwardPointsIsCalled();

        // (70 - 50) * 1
        thenTheExpectedPointsAre(20);
    }

    @Test
    public void getAwardPointsWhenTheAmountIsHundred() {

        givenDollarAmount(100);

        whenGetAwardPointsIsCalled();

        // (100 - 50) * 1
        thenTheExpectedPointsAre(50);
    }

    @Test
    public void getAwardPointsWhenTheAmountIsAboveHundred() {

        givenDollarAmount(120);

        whenGetAwardPointsIsCalled();

        // [(50) * 1] + [(120 - 100) * 2]
        thenTheExpectedPointsAre(90);
    }


    /**
     * @throws ParseException
     * @throws IOException
     */
    @Test
    public void calculateCustomerAwardingPoints_test1() throws ParseException {

        /***********************************************************************************
         Note:  Refer TestCaseRequest1.json  AND TestCaseResponse1.json under
         customers-awarding-points/src/test/java/com/customer/awarding/data/
         **********************************************************************************/

        givenAListOfCustomerTransactions();

        whencalCulateCustomerAwardingPointsCalled();

        // Prepare the expected Response.
        CustomerAwardingPoints expectedResponse = prepareCustomerAwardingPointsResponse();

        assertNotNull(response);
        assertNotNull(response.getThreeMonthPeriodAwardingPoints());

        assertEquals("Expected size is 2", expectedResponse.getThreeMonthPeriodAwardingPoints().size(),
                response.getThreeMonthPeriodAwardingPoints().size());

        /*************************************************************************
         Check All the values in the first ThreeMonthPeriodAwardingPoints object.
         **************************************************************************/
        assertNotNull(response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints());
        assertEquals("Expected size is 3",
                expectedResponse.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().size(),
                response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().size());

        // Loop through each Monthly result.
        for (int i = 0; i < 3; i++) {
            assertEquals(expectedResponse.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(i).getMonth(),
                    response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(i).getMonth());
            assertEquals(expectedResponse.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(i).getTotalPoints(),
                    response.getThreeMonthPeriodAwardingPoints().get(0).getCustomerAwardingMonthlyPoints().get(i).getTotalPoints());
        }
        assertEquals("Three Months total points",
                expectedResponse.getThreeMonthPeriodAwardingPoints().get(0).getThreeMonthsTotal(),
                response.getThreeMonthPeriodAwardingPoints().get(0).getThreeMonthsTotal());

        /**************************************************************************
         Check All the values in the second ThreeMonthPeriodAwardingPoints object.
         *************************************************************************/
        assertNotNull(response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints());
        assertEquals("Expected size is 2",
                expectedResponse.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().size(),
                response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().size());

        // Loop through each Monthly result.
        for (int i = 0; i < 2; i++) {
            assertEquals(expectedResponse.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().get(i).getMonth(),
                    response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().get(i).getMonth());
            assertEquals(expectedResponse.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().get(i).getTotalPoints(),
                    response.getThreeMonthPeriodAwardingPoints().get(1).getCustomerAwardingMonthlyPoints().get(i).getTotalPoints());
        }

        assertEquals("Two Months total points",
                expectedResponse.getThreeMonthPeriodAwardingPoints().get(1).getThreeMonthsTotal(),
                response.getThreeMonthPeriodAwardingPoints().get(1).getThreeMonthsTotal());

        assertEquals("Total Points",
                expectedResponse.getConsolidatedPoints(),
                response.getConsolidatedPoints());

    }

    private void givenDollarAmount(int amount) {
        this.amount = amount;
    }

    private void givenAListOfCustomerTransactions() throws ParseException {
        prepareCustomerTransactionsRequest();
    }

    private void whenGetAwardPointsIsCalled() {
        points = classUnderTest.getAwardPoints(amount);
    }

    private void whencalCulateCustomerAwardingPointsCalled() {
        response = classUnderTest.calculateCustomerAwardingPoints(request);
    }

    private void thenTheExpectedPointsAre(int expectedValue) {
        assertEquals(expectedValue, points);
    }


    private void prepareCustomerTransactionsRequest() throws ParseException {

        /***********************************************************************************
         Note:  Refer TestCaseRequest1.json under
         customers-awarding-points/src/test/java/com/customer/awarding/data/
         **********************************************************************************/

        transaction1 = new CustomerTransaction(
                new SimpleDateFormat("MM/dd/yyyy").parse("10/20/2022"),
                130,
                "Transaction1");
        transaction2 = new CustomerTransaction(
                new SimpleDateFormat("MM/dd/yyyy").parse("08/20/2022"),
                130,
                "Transaction2");
        transaction3 = new CustomerTransaction(
                new SimpleDateFormat("MM/dd/yyyy").parse("03/20/2022"),
                70,
                "Transaction3");
        transaction4 = new CustomerTransaction(
                new SimpleDateFormat("MM/dd/yyyy").parse("01/20/2022"),
                70,
                "Transaction4");
        transaction5 = new CustomerTransaction(
                new SimpleDateFormat("MM/dd/yyyy").parse("01/20/2022"),
                40,
                "Transaction5");
        transaction6 = new CustomerTransaction(
                new SimpleDateFormat("MM/dd/yyyy").parse("04/20/2022"),
                60,
                "Transaction6");

        request = CustomerTransactions.build();
        request.getCustomerTransactions().add(transaction1);
        request.getCustomerTransactions().add(transaction2);
        request.getCustomerTransactions().add(transaction3);
        request.getCustomerTransactions().add(transaction4);
        request.getCustomerTransactions().add(transaction5);
        request.getCustomerTransactions().add(transaction6);
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
