# Oracle coding challenge

This is a Java application that would consume the multiline string as the input
and produce the following report as the output

	• The number of unique customerId for each contractId
	• The number of unique customerId for each geozone
	• The average buildduration for each geozone
	• The list of unique customerId for each geozone

### Input

Example String: 

	2343225,2345,us_east,RedTeam,ProjectApple,3445s
	1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
	3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
	1233456,2345,us_west,BlueTeam,ProjectDate,2221s
	3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s

The data in each line is interpreted as: customerId,contractId,geozone,teamcode,projectcode,buildduration


### Output

Output is a report that looks like below.

	The number of unique customerIds for each contractId:
                                       [2346, 2]
                                       [2345, 3]
	The number of unique customerIds for each geozone:
                                       [eu_west, 2]
                                       [us_west, 2]
                                       [us_east, 1]
	The average buildduration for each geozone:
                                       [eu_west, 4222.0]
                                       [us_west, 2216.0]
                                       [us_east, 3445.0]
	The list of unique customerIds for each geozone:
                                       [eu_west, [3244132, 3244332]]
                                       [us_west, [1233456, 1223456]]
                                       [us_east, [2343225]]


### Build and Execution

**Note:** For the convenience of running this application, the current code expects input as a file. Copy the input string in a text file and provide that as input to the program

#####From Maven : 

- To build the application:

	mvn clean package

- To run the tests:

	mvn clean test

- To run the application:

	mvn exec:java -Dexec.mainClass="com.oracle.App" -Dexec.args="<filepath of file that contains multiline string>"

#####From Eclipse/IDE :

	Run: public static void main(String[] args) in "com.oracle.App" and set the arguments to input file

### Approach

- Each line of the input multiline string is mapped to a Java object(Record) and list of those records is loaded into memory.
- All the queries are then run on the in-memory data to generate report.

### Assumptions
- The input here is being read by pasting the multiline string in text file. But it can be extended to read from console, csv, json or any other kind of sources.
- The input string will only have 6 columns as of now, but can be extended to support more columns.
- The code can be used to read data from database.
- Only the queries mentioned above are required as of now.
