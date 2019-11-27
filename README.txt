#Root- Driving Summary
Problem Statement:
The code will process an input file. You can either choose to accept the input via stdin (e.g. if you're using Ruby cat input.txt | ruby yourcode.rb), or as a file name given on the command line (e.g. ruby yourcode.rb input.txt). You can use any programming language that you want. Please choose a language that allows you to best demonstrate your programming ability.

Each line in the input file will start with a command. There are two possible commands.

The first command is Driver, which will register a new Driver in the app. Example:

Driver Dan

The second command is Trip, which will record a trip attributed to a driver. The line will be space delimited with the following fields: the command (Trip), driver name, start time, stop time, miles driven. Times will be given in the format of hours:minutes. We'll use a 24-hour clock and will assume that drivers never drive past midnight (the start time will always be before the end time). Example:

Trip Dan 07:15 07:45 17.3

Discard any trips that average a speed of less than 5 mph or greater than 100 mph.

Generate a report containing each driver with total miles driven and average speed. Sort the output by most miles driven to least. Round miles and miles per hour to the nearest integer.

Example input:

Driver Dan
Driver Lauren
Driver Kumi
Trip Dan 07:15 07:45 17.3
Trip Dan 06:12 06:32 21.8
Trip Lauren 12:01 13:16 42.0
Expected output:

Lauren: 42 miles @ 34 mph
Dan: 39 miles @ 47 mph
Kumi: 0 miles

Solution:

Tech specs: Java 8, Maven, Junit4, amazon-corretto-8.jdk

    1)  Given the input file is provided, ruled out the possibility of FileNotFound case and added the file into classpath.

    2)  Class: TripFileReader.java - Used Java text Scanner to read through the file. This breaks the line into tokens using the delimiter pattern.
        Java Stream is another option available in Java 8. There is no difference in performance between Scanner and Stream. So, proceeded with Scanner

    3)  Collected all the lines into an String array - Each array item is space delimited string

    4)  Class: RootUtil - The String array is parsed into 2 ArrayList - DriverList and TripSummaryList
        DriverList will have all the drivers name to register
        TripSummaryList will have all the Trip data for the drivers

        Scenarios Handled:
            1) IF the Trip command has the start time less than end time, then the trip will be ignored as its gone past into next day
                System.out.println("File validation required : Invalid trip data on line number: " + lineNumber);

            2) If the Trip command has the entry for unregistered Drivers ( Driver name not available in First command)
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

Run the application:
    This solution can be executed in 2 ways:

    1) mvn clean package, builds the root.jar in the target directory
    2) java -jar target/root.jar (loads and executes the main class).


