Feature:Ensure the stability of the weather API

  Scenario: 01 Status of the weather Today and in the coming days
    When A JSON GET request is sent to the Metaweather server
    Then The weather in London on this date will appear with status
      | Index ID | Day         | Expected Status |
      | 0        | Today       | Showers         |
      | 1        | Tomorrow    | Showers    |
      | 2        | Tomorrow +1 | Showers     |
      | 3        | Tomorrow +2 | Heavy Cloud     |

  Scenario: 02 Ensuring Metaweather API can provide data by location
    When A JSON GET request is sent to the Metaweather server
    Then Data for the following cities is returned
      | City    | WOE ID  |
      | Cardiff | 15127   |
      | Paris   | 615702  |
      | Perth   | 1098081 |
      | Berlin  | 638242  |
      | Toronto | 4118    |

  Scenario: 03 Confirm server response is 200
    When A JSON GET request is sent to the Metaweather server
    Then server Status response is 200 meaning it Ok


    Scenario: 04 Assert at least one of the weather states is displayed
      When A JSON GET request is sent to the Metaweather server
      Then At least one weather state is displayed

