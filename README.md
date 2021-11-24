
# interestCalculationApplication
This is a Spring boot application for processing the an account and calculating the interest. The Interest can be calculated on monthely and daily basis.
Restfull end point has been introduced to integarate this application with UI

I have integrated the inherently blocking JDBC API to the world of reactive programming.

The point is to have a dedicated, well-tuned thread pool and isolate the blocking code there.

# functionality
A simple `account` table which consists of `id`(auto-generated), `bsb` (branch identification)  `identification` (account Number as pk), `opening_date` and `balance` columns is used.

## Non Blocking apis
|-------------------------------------------------------------------------------|
| URL            :   Post- http://localhost:8080/account/open                   |
| functionality  :   opening an account                                         |       |                                       |
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
-----------------------------------------------------------------------------------
 
 | PUT - http://localhost:8080/account/daily-interest   | This will get end-of-day balances for accounts after calulating the daily interest       |
 | PUT - http://localhost:8080/account/monthly-interest | This will get monthely interest of each user                             |


# Reference:
1. https://www.changchao.me/?p=795
1. http://musigma.org/java/2016/11/21/reactor.html
1. https://stackoverflow.com/questions/42299455/spring-webflux-and-reading-from-database
1. https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-jpa
1. http://www.grahamlea.com/2014/07/rxjava-threading-examples/
1. https://spring.io/blog/2016/07/20/notes-on-reactive-programming-part-iii-a-simple-http-server-application

