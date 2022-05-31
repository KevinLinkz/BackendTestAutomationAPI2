# BackendTestAutomationAPI2
This is my Frameworks for test 1,


This framework uses the Singleton Pattern.
For the report it uses Extent Report.
For framework tests, it uses testNG.
To test the API it uses RestAssured.
The purpose of this framework is for performance testing, it can be used on jenkins. 

Steps:
1. Run testng.xml
2. RestAssured will hit api.
3. The response will be compared with Expected.
4. The datas taken from Data Provider.
5. In here i'm not using excel for data, because it's heavy. 
6. The extent report collects extendTest and flush it.
7. The report will be generate on folder Result
8. Every running this framework,  always a folder that uses this format (ddMMyyHHmm)
9. Every folder will be Report.html and log.txt
10. In testng.xml I use thread-count = 5 on test and on suit I use data-provider-thread-count = 5. (that can be change for total thread)

Dependencies :
1. ExtentAPI (Extend Report)
2. JSON in java
3. Rest Assured
4. Rest Assured - json-schema-validator
5. testNG

I make a framework for keeping the environment stable where the data doesn't change. 

Improvements would be needed:
1. Dynamic data, I can change this framework to get data, from hardcode to collect datas from Microsoft Excel.
2. This framework need more method for make it perfect.

Actually, I have the other frameworks, if you want to see, you can click this link.
https://github.com/KevinLinkz/BackendTestAutomationAPI
