# customers-awarding-points

Date Format : 

Sample Request: "MM/dd/yyyy"

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

Sample Response:

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
