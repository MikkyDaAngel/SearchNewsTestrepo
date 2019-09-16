@complete
Feature: Example feature

  In order to ensure authenticity of a news
  As a news agent
  I want to ensure is being broadcast by reputable news media company


  Scenario: Searching Google for Guidance Tone Article
    Given I navigate to "https://www.theguardian.com/tone/news"
    When I select the first news article
    And I navigate to second site "https://www.google.co.uk"
    And I search for the article
    Then I should see the search result on "the article"