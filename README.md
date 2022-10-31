# customers-awarding-points

Sample Request:

{
      "customerTransactions": [
            {
                  "transactionDate": "10/20/2022",
                  "transactionAmount": 130,
                  "transactionDescription": "Kitty"
            },
            {
                  "transactionDate": "12/20/2022",
                  "transactionAmount": 70,
                  "transactionDescription": "Kitty"
            },
            {
                  "transactionDate": "12/21/2022",
                  "transactionAmount": 70,
                  "transactionDescription": "Kitty"
            },
            {
                  "transactionDate": "05/20/2022",
                  "transactionAmount": 40,
                  "transactionDescription": "Kitty"
            },
            {
                  "transactionDate": "11/20/2022",
                  "transactionAmount": 60,
                  "transactionDescription": "Kitty"
            },
            {
                  "transactionDate": "01/20/2022",
                  "transactionAmount": 100,
                  "transactionDescription": "Kitty"
            },
            {
                  "transactionDate": "01/21/2022",
                  "transactionAmount": 100,
                  "transactionDescription": "Kitty"
            },
            {
                  "transactionDate": "06/21/2022",
                  "transactionAmount": 101,
                  "transactionDescription": "Kitty"
            }
      ]
}

Sample Response:

{
      "customerAwardingMonthlyPoints": [
            {
                  "month": "JANUARY",
                  "totalPoints": 100
            },
            {
                  "month": "MAY",
                  "totalPoints": 0
            },
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
            },
            {
                  "month": "DECEMBER",
                  "totalPoints": 40
            }
      ],
      "consolidatedPoints": 312
}
