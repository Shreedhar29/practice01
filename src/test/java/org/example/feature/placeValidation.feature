Feature: validating place API's
  Scenario: Verify is place is being successfully added using addPlace api
    Given Add place payload
    When user calls "AddPlaceApi" with post http request
    Then the api call got success with status code 200
    And "token_type" in response body is "Bearer"
    And "scope" in response body is "create"

