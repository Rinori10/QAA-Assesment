Feature: Get Weather Information

  @debug
  Scenario: Check if city name is returned correctly
    When Sent request to openweathermap for London - GET
    Then Server returns correct weather information for 7 days for London

  @debug
  Scenario: Check if city name is returned correctly
    When Sent request to openweathermap for Pristina - GET
    Then Server returns correct weather information for 7 days for Pristina

#      | cityReq  |
#      | “London" |