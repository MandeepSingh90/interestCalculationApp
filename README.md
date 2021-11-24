
# interestCalculationApplication
This is a Spring boot application for processing the an account and calculating the interest. The Interest can be calculated on monthely and daily basis.
Restfull end point has been introduced to integarate this application with UI

I have integrated the inherently blocking JDBC API to the world of reactive programming.

The point is to have a dedicated, well-tuned thread pool and isolate the blocking code there.

# functionality
A simple `account` table which consists of `id`(auto-generated), `bsb` (branch identification)  `identification` (account Number as pk), `opening_date` and `balance` columns is used.

## Non Blocking apis
|------------------------------------------------------------------------------------------------------------------------------------------------------------
| URL            :   Post- http://localhost:8080/account/open                   
| functionality  :   opening an account                                                                                      
| Request Body   :     {
|                          "bsb": "182182",
|                         "identification": 1172223333,
|                         "openingDate" : "2021-09-13"
|                       }     
|
|  Response       :
|                     {
|                       "bsb": "182182",
|                       "identification": 11722233331,
|                       "openingDate": "2021-09-13T00:00:00.000+0000",
|                       "updatedAt": "2021-11-24T23:02:34.866+0000"
|                   }
-------------------------------------------------------------------------------------------------------------------------------------------------------------
 
 |------------------------------------------------------------------------------------------------------------------------------------------------------------
| URL            :   Post- http://localhost:8080/account/daily-interest                  
| functionality  :   Responsible for calculating the interest on daily basis                                                                                  
| Request Body   :     {
                           "balanceDate" : "2021-09-19",
                          "balances": [
                               {
                               "bsb": "182182",
                               "identification": 111222333,
                               "balance": 123.34
                               },
                               {
                               "bsb": "182182",
                               "identification": 222000111,
                               "balance": 12.34
                               },
                               {
                               "bsb": "182182",
                               "identification": 222000999,
                               "balance": 0.00
                               }
                           ]
                       }    
|
|  Response       :
|                     {
                       "balanceDate": "2021-11-24T23:11:34.084+0000",
                       "balances": [
                           {
                               "bsb": "182182",
                               "identification": 111222333,
                               "openingDate": "2021-09-13T00:00:00.000+0000",
                               "updatedAt": "2021-11-24T23:11:34.084+0000",
                               "balance": 1670.14
                           },
                           {
                               "bsb": "182182",
                               "identification": 1112223333,
                               "openingDate": "2021-09-13T00:00:00.000+0000",
                               "updatedAt": "2021-11-24T23:11:34.084+0000",
                               "balance": 4461.87
                           },
                           {
                               "bsb": "182182",
                               "identification": 1162223333,
                               "openingDate": "2021-09-13T00:00:00.000+0000",
                               "updatedAt": "2021-11-24T23:11:34.084+0000",
                               "balance": 44568.34
                           },
                           {
                               "bsb": "182182",
                               "identification": 1172223333,
                               "openingDate": "2021-09-13T00:00:00.000+0000",
                               "updatedAt": "2021-11-24T23:11:34.084+0000",
                               "balance": 345101
                           },
-------------------------------------------------------------------------------------------------------------------------------------------------------------

|------------------------------------------------------------------------------------------------------------------------------------------------------------
| URL            :   Post- http://localhost:8080/account/monthly-interest                  
| functionality  :   This will get monthely interest of each user                                                                               
| Request Body   :     {
|                           "balanceDate" : "2021-09-19"
|                      }    
|
|  Response       :
                  |                     {
                      "balanceDate": "2021-11-24T21:28:06.550+0000",
                      "balances": [
                          {
                              "bsb": "182182",
                              "identification": 111222333,
                              "monthlyInterest": 33.4028
                          },
                          {
                              "bsb": "182182",
                              "identification": 1112223333,
                              "monthlyInterest": 89.2374
                          },
                          {
                              "bsb": "182182",
                              "identification": 1162223333,
                              "monthlyInterest": 891.3667999999999
                          },
                          {
                              "bsb": "182182",
                              "identification": 1172223333,
                              "monthlyInterest": 6902.02
                          }
                      ]
                  }
-------------------------------------------------------------------------------------------------------------------------------------------------------------
