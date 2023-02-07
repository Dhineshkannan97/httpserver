# httpserver

## **Overview**

### **Server Class**

A Java class to handle incoming connections on a server socket.

#### Features

Accepts incoming connections through a server socket
Starts a new thread to handle each incoming connection with the HttpHandler class

## HTTPHandler class

This repository contains a simple HTTP server written in Java. The server accepts incoming HTTP requests and returns files from the specified root directory.

### Features

Supports only GET and HEAD methods.
Returns 200 OK status code if the requested file is found, 404 Not Found if the file is not found and 501 Not Implemented if an unsupported method is requested.
Returns the appropriate content-type header for the requested file.
Uses a logger to log information and error messages.
Automatically closes all open streams and sockets after each request.
How to use
Clone the repository to your local machine.
Navigate to the directory where the cloned repository is located.
Compile the Java code using the following command: javac Server.java
Run the compiled code using the following command: java Server
Open a web browser and navigate to http://localhost:8070 to test the server.

## Note

The default root directory is set to C:\\Users\\Dhinesh Kannan\\Documents\\Streams\\httpserver\\src\\main\\resources but you can change it by modifying the value of the WEB_ROOT constant in the HttpHandler class.
The logger configuration is set to log information and error messages to the console. If you want to change the logger configuration, you can modify the code in the Server class.
use this dependency for logger
<dependencies>
<dependency>
<groupId>org.slf4j</groupId>
<artifactId>slf4j-simple</artifactId>
<version>2.0.0-alpha5</version>
</dependency>
</dependencies>
Step 1 : Run the Main class
step 2 : open the web browser
step 3 : http://localhost:8070/punchline.html

