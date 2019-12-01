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
Java Doc: root-tripTracker/javadoc/index.html

Java Classes:

    1) DriverTripHandler.java :is the main class, which process the input file and prints the output in the console.

    2) TripFileReader.java : Used Java text Scanner to read through the file. This breaks the line into tokens using the delimiter pattern.
        Java Stream is another option available in Java 8. There is no difference in performance between Scanner and Stream. So, proceeded with Scanner

    3) TripProcessor.java : Process the Collected String array and loads data into Driver and Trip model.

    4) Driver.java : Driver model adds all the driver names and trip details in Trip.java model.

    5) TripDataHelper.java : All the validations and methods to build the model objects.

    6) DriverTripManager.java :  printAllDriverTrips method Prints all the results into console.
         Since the output needs to be printed based on the most miles driven, Used Java Lambda to sort the final summary List by miles and
         then reverse sorted using collections to print the most miles first.

    7) Test cases covers all the public methods, which has validation and calculation, in model, manager and helper classes.

Run the application:

    1) mvn clean package, runs all the tests and builds the root.jar in the target directory
    2) java -jar target/root.jar (loads and executes the main class and prints the output in console).


