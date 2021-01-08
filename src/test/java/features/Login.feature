Feature: serviceNow login functionality

Scenario: TC001_Login with positive credential 
Given open the chrome browser and maximize the window
And load the service now application
Given enter username as 'admin'
And enter password as 'India@123'
When click login button
Then homepage should be displayed
Given enter 'incident' in the filter navigator
And click All under Incident
And click New button
And read the incident number from Number and store it in variable for verification
And click on look up icon for Caller
And search "Abel" in the lookup window
And click "Abel Tuter" from the results
And enter the Short_description
When click on Submit
Then search for the newly created incident and verify