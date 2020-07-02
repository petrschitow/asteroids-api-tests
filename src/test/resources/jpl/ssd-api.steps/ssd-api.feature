Feature: Tests for 'ssd-api.jpl.nasa.gov/cad.api' endpoint

  Scenario Outline: Get Earth close-approach data and check dates and sorting by 'dist'
    When I construct request where dist-max="<dist-max>", date-min="<date-min>", date-max="<date-max>", sort="<sort>"
    And I send request to the 'ssd-api.jpl.nasa.gov' url with parameters from Test Context
    Then I check that the status code is 200
    And I check signature
    And I check fields order
    And I check returned data formats
    And I check that cd > 'date-min' and cd < 'date-max'
    And I check that data sort by 'dist' field

    Examples: Request data
      | dist-max | date-min   | date-max   | sort |
      | 5LD      | 2020-05-01 | null       | dist |
      | 10LD     | 2020-01-01 | 2020-02-01 | dist |


  Scenario: Try to get Earth close-approach data were data-min > data-max
    When I construct request where dist-max='5LD', date-min='2020-06-01', date-max='2020-04-01', sort='dist'
    And I send request to the 'ssd-api.jpl.nasa.gov' url with parameters from Test Context
    Then I check that the status code is 200
    And I check signature
    And I check that count='0'

  Scenario Outline: Get all close-approach data for asteroid 433 Eros
    When I construct request where des='141P', date-min="<date-min>", date-max="<date-max>", dist-max="<dist-max>"
    And I send request to the 'ssd-api.jpl.nasa.gov' url with parameters from Test Context
    Then I check that the status code is 200
    And I check signature
    And I check returned data formats
    And I check that count="<expectedCount>"
    And I check that des='141P' and orbitId='11'

    Examples:
      | date-min   | date-max   | dist-max | expectedCount |
      | 1957-08-25 | 2036-12-19 | 0.2      | 2             |
      | 2000-08-26 | 2036-12-19 | 0.2      | 1             |
      | 1957-08-25 | 2036-12-19 | 0.15     | 1             |
