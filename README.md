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
    
If everything goes fine you will get an output like:

    Server replied: "I've received: Hello Server, I'm the client."
    
If connection refused exceptions or other connection issues are raised, it is likely you have a different network setting than the code is configured out with. If this is your case, please check the following sections.

## Setting IP address, port and object name

RMI runs over TCP/IP. Hence, to the two (probably) remote process to communicate the client program must know the IP and port parameters in order to find the server.

In real world application these kind of setting is provided by a configuration file. For the sake of simplicity, in this example I have hard coded these parameters in both server and client. Be aware that this is never done in a production application for obvious reasons.

### Changing the server settings

In the beginning of main method of class `rmipoc.engine.BootstrapServer` one can find the following code:

    final String resolutionIp = "192.168.1.80";
    final int port = 1043;
    final String objectName = "MessageService";

Change these parameters to match your environment.

NOTE that in real production solutions these settings are not hardcoded in the program code itself. Very often these parameters are informed by a text file or as system properties in the application call.

## Using RMI through a firewall.

RMI uses a multi port strategy to allow several concurrent connections to the server object. This can cause connection issues if there is a firewall or other port policy denying TCP connections between your RMI client and server. Considering that there is a single port to allow the communication, you must to indicate to RMI to use this single port instead of the multi port approach. This can be done by changing?

    final int replyPort = 0;
    
The `replyPort` variable is used in the stub creation:

    MessageService stub = (MessageService) UnicastRemoteObject.exportObject(serverObject, replyPort);

`replyPort = 0` indicate to RMI to use the multiport strategy. However, if your firewall allows connections only on a single port, for instance port 1043, change the line to:

    final int replyPort = 1043;


