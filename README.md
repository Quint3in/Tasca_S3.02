# Task S3.02

## Level 1 Exercise 1
**Description**: 
Implements an `Undo` manager using the Singleton pattern. The
`Undo` class stores a history of commands, allows adding new
commands, shows the stored history, and removes the last command
with an undo operation. The `Main` class includes a simple example
of adding commands, listing them, and undoing the latest action.

## Level 2 Exercise 1
**Description**:
Implements a contact generation system using the Abstract Factory
pattern. The `ContactFactory` abstraction creates related `Address`
and `Phone` objects, while the concrete factories provide country-
specific implementations for Spain and the USA. The `Contact` class
uses the selected factory to compose a full contact, and the `Main`
class includes a simple example of generating contacts for both
countries.

## Level 3 Exercise 1
**Description**:
Implements a report generation service using the Strategy pattern.
The `ReportGeneratorService` accepts any `ReportStrategy`
implementation and delegates the report generation behavior to the
selected strategy. The `ReportStrategy` interface defines different
output formats such as HTML, JSON, XML, PDF, CSV, Excel, and Word,
and the strategy usage is demonstrated through unit tests.

## Technologies
- Backend: Java - Maven

## Installation and Execution
1. Clone the repository: `git clone https://github.com/Quint3in/Tasca_S3.02.git`
2. Go into the project folder: `cd Tasca_S3.02`
3. Build the module from the root:
   `mvn -pl n1exercici1 -am package`
4. Run it (from the root):
   `java -cp n1exercici1/target/classes org.example.Main`
