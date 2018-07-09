# ventsislav-penev-employees

Introduction:
The goal of this project is to find the employee pair(emp1, emp2) worked longest on common projects.

Input data:
Input data come from a text file. Eeach line of the file represents a given employee record.
The format of the employee record is: "EmpID, ProjectID, DateFrom, DateTO". 
For example: "143, 12, 2013-11-01, 2014-01-05".

Output:
The output of the application is the details about the employee pair worked longest on common projects.
The results are displayed in a table. The columns of the table are: Emp1Id, Emp2Id, ProjectId, StartDate, EndDate, DaysWorked

Project structure:
Different logical modules are divided in different packages. The main functionality is inside "src/main/java" folder.
The packets are: commons, servicces, exceptions, configuration, ui, utils.
Unit tests and Integration tests are also put in packages inside "src/test/java" folder.

UI:
UI is built as a Swing application with one main screen

Technologies used: Java 1.8, Spring, JUnit, Mockito.

How to run:
Import the project as a maven project in the IDE.
Build the project: type "mvn clean install" in the root directory.
Open /src/main/java/employees/ui/ApplicationDemo.java file and run it as a Java Application.

How to use:
There are two buttons. When you click the first one(for choosing file) it will open a window
for choosing a file from the file system(file must be .txt). When you click the second button
(for showing the employee worked longest), details about the employee pair are displayed in a table below.

Documentaion and tests:
Source code has documentation. Main modules have unit tests and integration tests.
