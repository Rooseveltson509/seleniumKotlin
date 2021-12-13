@non-reg
Feature: As a user I want to do a experience for a stay and add it to my wishlist

  @Login
  Scenario Outline: Experience for a stay
    Given user is on the home page
    When user is doing to his experience
    #When  user is doing his research for the "<location>" with a stay between "<checkin date>" "<checkin month>" and the "<checkout date>" "<checkout month>" for "<adultsNbre>" "<childrenNbre>" "<infantsNbre>" "<petsNbre>"
    Then  he should be at the home page
    Examples:
      | location  | checkin date  | checkin month | checkout date | checkout month | adultsNbre | childrenNbre |infantsNbre |petsNbre |
      | Bordeaux  | 18            | décembre      | 22            | décembre       | 2          | 1            | 0          | 1       |

