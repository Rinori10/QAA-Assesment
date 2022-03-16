Feature: Get Whether Information

  @debug
  Scenario: Check if city name is returned correctly
    When Sent request to openweathermap - GET
    Then Server returns correct weather information for 7 days

#      | cityReq  |
#      | “London" |