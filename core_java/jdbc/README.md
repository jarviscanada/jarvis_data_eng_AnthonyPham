# Introduction
The Java Database Connectivity (JDBC) application is used to create a connection to a PostgreSQL database. The application allows the user to perform CRUD (create, read, update and delete) operations on the database. The project uses the following technologies: PostgreSQL, SQL, Docker, Maven, Java JDBC

# Implementation
## ER Diagram
![erdiagram](assets/er_diagram.png?raw=true)

# Design Paterns 

Data Access Object (DAO) 

The Data Access Object pattern is a design pattern that facilitates the isolation of the application (business layer) from the relational database (persistence layer). The DAO is implemented used an abstract API. It's purpose is to hide the complexities involved in performing CRUD operations in the program. This allows the two layers to be developed independantly from each other.

Repository

The Repository pattern consists of classes or compnents that encapsulate the operations used to access specific aspects of the database. This makes it so that it is able to access one table per class and performing join operations in the java code rather than the database. The pattern is similar to DAO patterns in that it also hides data access functionality such as CRUD to provide easier maintainability. This allows the focus to be emphasized more on data persistence logic rather than data access plumbing. The main difference between the two patterns is that DAO focuses on the abstraction of data persistence, whereas Repository is an abstraction of a collection of objects. 

# Test
The database was tested by creating and populating tables with sample data from SQL scripts. In order to test the database, queries were performed after any new data was inserted. The JDBCExecutor was used to run and test functionality. CRUD and join operations were tested by creating, reading, updating and deleting data as well as being printed on the command line to see if the expected output resulted.
