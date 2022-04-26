Train -- A Refactoring Example Project
======================================

This program reads in a CSV of train accidents in Britain.
It groups the numbers by decade and prints the total sum and the average.
Also, this program is in need of refactoring and code deduplication.

The CSV also contains NA (null) values. They make handling the CSV substantially harder.
If you'd like to make this exercise easier, just delete the last line in the CSV that
contains NA values and assume that the CSV never contains NA values, but that all fields
and rows always contain numbers.

Possible refactoring steps:

1) Currently, stats() is doing too much: opening the file, reading it, parsing it, 
    calculating the numbers, and printing them.
    Start by passing an InputStream object into the function instead of opening the file inside stats().
    Hint: You can get an InputStream from a File by using a FileInputStream.
2) Next, extract a method called readCSV() from stats(): this is the function that reads from
    the InputStream, parses the CSV, and populates the variables.
3) Move readCSV() and its assorted variables into its own class called TrainData.
    We'd like to have a class that represents the data and has a method to populate the variables
    from a CSV InputStream using readCSV(). Write a unit test that checks that readCSV()
    is working properly.
4) Instead of multiple ArrayList variables, create a new class YearStats that represents a single
    row. Then have one single ArrayList of that object inside TrainData. Take this opportunity to
    give the variables proper names without underscores. Also, add methods TrainData::getRow() and
    TrainData::numRows() so that the internal properties of TrainData can be private.
5) Remove the loop that calculates the decade and the decade variable. Instead, just add
    a getDecade() method to the YearStats class.
6) Move stats() into its own class DecadeStats. It should accept a TrainData object in its constructor.
7) Add a method addStats() to YearStats that takes another YearStats object and adds all its values to
    the current one. Then use this function in DecadeStats::stats() as accumulator. This should get
    rid of many helper variables.
    If you are dealing with NA values: add a function YearStats::countStats() that adds 1 to a field
    if it is non-null, and does nothing if the value is null. That way we can use a YearStats object
    to keep track of how many fields are non-null for calculating the average.
8) Split stats() into four methods: total(), printTotal(), average(), and printAverage()
9) Unit testing total() and average() in their current form is difficult. They are doing two things at
    once: calculating and printing (calling the print functions). Untangle the functionality: have
    each method return a List<YearStats> that hold the statistics for each decade. Then write
    a print method that just prints the entire list. Change YearStats values from Integer to float
    (or double). That way you can use YearStats to hold the average and don't need two separate result
    classes. Eventually, you should just have a single print() or toString() method.
10) If you're handling NA values: extract a method readInt() (or readFloat()) that correctly handles NA
     values, and use it for all CSV fields, not just the last two (as in the CSV). Make sure to write
     some test cases that ensure that you are correctly handling NA values in fields, and as last value
     in a row. Test whether your method correctly handles NA values, if other rows follow.
     (Not just NA values in the last row as in the CSV).
11) Calculating total() and average() has some code duplication. Use the strategy pattern in order
    to resolve this. Add two classes: StatsSum and StatsAvg. Define an abstract class or interface
    StatsOperator (with methods reset(), addYear(), and getStats()). Then add a method 
    DecadeStats::calculate() that takes an operator and calculates to appropriate statistic and
    prints it. DecadeStats::total() and DecadeStats::average() should just contain a few lines then.
12) Add a factory method to TrainData so that the setup logic is moved out of main()
