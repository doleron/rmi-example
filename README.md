# rmi-example

This repository is an example of java RMI communication. It consists in three sub projects

- server: the server folder contains the server maven project 
- client: the client folder contains the client maven project 
- commons: the commons folder consists of the maven project containing the commons classes used by both client and server

## How to run this example

To run this example it is necessary to run two application: the server and the client. The following steps demonstrate how to build and run them.

### Running the server

On the SERVER machine, you may perform the following steps:

1 - clone the repository

    git clone https://github.com/doleron/rmi-example.git
    
2 - build the commons project

    cd rmi-example\commons
    mvn clean install
    
3 - build the server project

    cd ..\server
    mvn clean package assembly:single
    
4 - run the server

    cd target 
    java -jar server-0.0.1-SNAPSHOT-jar-with-dependencies.jar
    
The application should output something like:

    MessageService running on port 1043
    
Note that if you call the application twice without finished the previous one you ended up with a port in use error like:

    Exception in thread "main" java.rmi.server.ExportException: Port already in use: 1043; ...
    
### Running the client

Since we (probably) want run the example in two different machines, some steps must be replicated in both server and client. Said that, follow the stebs below in order to run the client 

1 - clone the repository

    git clone https://github.com/doleron/rmi-example.git
    
2 - build the commons project

    cd rmi-example\commons
    mvn clean install
    
3 - build the client project

    cd ..\client
    mvn clean package assembly:single
    
4 - run the server

    cd target 
    java -jar client-0.0.1-SNAPSHOT-jar-with-dependencies.jar
    
The application should output something like:

    MessageService running on port 1043