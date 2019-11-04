#Root- Driver Summary
Problem Statement:
The code will process an input file. Each line in the input file will start with a command. There are two possible commands.

    1) The first command is Driver, which will register a new Driver in the app.
        Example: Driver Dan
    2) The second command is Trip, which will record a trip attributed to a driver. (Trip), driver name, start time, stop time, miles driven
        Example: Trip Dan 07:15 07:45 17.3

Solution:

Tech specs: Java 8, Maven, Junit

    1)  Given the input file is provided, ruled out the possibility of FileNotFound case and added the file into classpath.

    2)  Class: TripProcessor.java - Used Java text Scanner to read through the file. This breaks the line into tokens using the delimiter pattern.
        Java Stream is another option available in Java 8. There is no difference in performance between Scanner and Stream. So, proceeded with Scanner

    3)  Collected all the lines into an String array - Each array item is space delimited string

    4)  Class: RootUtil - The String array is parsed into 2 ArrayList - DriverList and TripSummaryList
        DriverList will have all the drivers name to register
        TripSummaryList will have all the Trip data for the drivers

        Scenarios Handled:
            1) If the line have no Driver name : The below info will be printed in the console with line number
                System.out.println("File validation required: Driver name not found on line number: " + lineNumber)

            2) If the line does not have required inputs for Trip command, it will be ignored and printed in console with line number
                System.out.println("File validation required : Invalid trip data on line number: " + lineNumber);

            3) IF the Trip command has the start time less than end time, then the trip will be ignored as its gone past into next day
                System.out.println("File validation required : Invalid trip data on line number: " + lineNumber);

            4) If the Trip command has the entry for unregistered Drivers ( Driver name not available in First command)
                System.out.println("File validation required: Trip data found for unregistered driver: "+driverName);

    5)  Class: PrepareDrivingSummary - Iterate through the remaining valid driverList to manipulate the trip summary :
            Total miles driven
            Average speed per hour

            This class also handles the scenario to ignore speed driven less than 5 miles/hr and greater than 100 miles/hr
            All these invalid trips are collected in a List - this can be used for additional validation

            This method returns the final driving summary for all the drivers.

     6)  Class: printTripSummary - Prints all the final summary into console.
            Since the output needs to be printed based on the most miles driven, Used Java Lambda to sort the final summary List by miles and
            then reverse sorted using collections to print the most miles first.

      7)  Used 3 Value objects Driver, TripSummary, FinalSummary for manipulating the data.

  Maven:
        Used maven to handle the dependencies.
        Since, the input file is in the classpath, mvn clean package will process this file and print the input while executing the test cases.

  Junit:
        Started off this solution as TTD. Since there is not much scope for computation, used Junit to call the main method and print the output
         during the mvn build.

        There are only 2 calculation involved, calculateSpeed and calculateDuration. Since these methods kept as private, ignored these methods in tests.


Run the application:
    This solution can be executed in 2 ways:

    1) mvn clean package (it will process the file as part of tests and prints the output in console) and builds a root.jar in the target directory
    2) java -jar target/root.jar (This is actual execution of code).


