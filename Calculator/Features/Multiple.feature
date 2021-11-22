Feature: Calculator net
@tag1
Scenario Outline: Multiplication

Given user enter <num1> and <num2>
And click on multiple
Then verify <result>


Examples:
	| num1 | num2 | result |
	|423|525|222075|  


