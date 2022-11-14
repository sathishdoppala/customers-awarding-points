# customer-awarding-points

***

### Introduction

This is a basic  JAVA REST API developed using Springboot. The purpose of the API is to offer rewards program to its customers. Awarding points will be calculated for each transaction.

**Example:** A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction.

For $120 purchase = (2 X $20)+(1 X 50) = **90 Points**.

**Note:-** This is just a basic vanilla version, I have not complicated the logic much.

***
#### Postman Collection: [HERE](src/test/java/com/customer/awarding/postman/collection/CustomerPoints.postman_collection.json)

***
**API Links:**

1. Health Check Endpoint : [GET] http://localhost:8080/awarding/healthcheck

2. getCustomerAwardingPoints : [POST] http://localhost:8080/awarding/points

***

#### Example Request:

Example request can be found [HERE](/src/test/java/com/customer/awarding/data/Request.json)

```json
{
  "customerTransactions": [
    {
      "transactionDate": "10/20/2022",
      "transactionAmount": 130,
      "transactionDescription": "Transaction6"
    },
    {
      "transactionDate": "02/20/2022",
      "transactionAmount": 130,
      "transactionDescription": "Transaction3"
    },
    {
      "transactionDate": "12/20/2022",
      "transactionAmount": 70,
      "transactionDescription": "Transaction8"
    },
    {
      "transactionDate": "12/21/2022",
      "transactionAmount": 70,
      "transactionDescription": "Transaction"
    },
    {
      "transactionDate": "05/20/2022",
      "transactionAmount": 40,
      "transactionDescription": "Transaction4"
    },
    {
      "transactionDate": "11/20/2022",
      "transactionAmount": 60,
      "transactionDescription": "Transaction7"
    },
    {
      "transactionDate": "01/20/2022",
      "transactionAmount": 100,
      "transactionDescription": "Transaction1"
    },
    {
      "transactionDate": "01/21/2022",
      "transactionAmount": 100,
      "transactionDescription": "Transaction2"
    },
    {
      "transactionDate": "06/21/2022",
      "transactionAmount": 101,
      "transactionDescription": "Transaction5"
    }
  ]
}
```

#### Example Response:

Example response can be found [HERE](/src/test/java/com/customer/awarding/data/Response.json)

```json
{
  "threeMonthPeriodAwardingPoints": [
    {
      "customerAwardingMonthlyPoints": [
        {
          "month": "JANUARY",
          "totalPoints": 100
        },
        {
          "month": "FEBRUARY",
          "totalPoints": 110
        },
        {
          "month": "MAY",
          "totalPoints": 0
        }
      ],
      "threeMonthsTotal": 210
    },
    {
      "customerAwardingMonthlyPoints": [
        {
          "month": "JUNE",
          "totalPoints": 52
        },
        {
          "month": "OCTOBER",
          "totalPoints": 110
        },
        {
          "month": "NOVEMBER",
          "totalPoints": 10
        }
      ],
      "threeMonthsTotal": 172
    },
    {
      "customerAwardingMonthlyPoints": [
        {
          "month": "DECEMBER",
          "totalPoints": 40
        }
      ],
      "threeMonthsTotal": 40
    }
  ],
  "consolidatedPoints": 422
}
```
