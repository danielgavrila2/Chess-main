Client-Side Chess Game built with Java and Sockets.

1. Server-Side : 
  - Server is built with Java utities, using Serializable objects to assure the communication between Players;
  - It uses a TCP Protocol communication broadcast between players;
  - It is responsible for matching players available in the queue, to create a new game sesssion, and to handle each interaction;
  - Each type of message has its own category and is handled by a thread which listens to the client input;
  - Processes each movement or chat message request, according to the chess logic, and sends the information to the client via GUI;
  - It implementens color assignation, matching players and all the functionalities during a match.

2. Client-Side : 
  - Each client has its own GUI Page, where it can interact with the other client;
  - It is a classic Chess GUI, but it has also the feature to send messages or to give information about the match status;
  - Built with Java Swing and Apache Netbeans IDE;
  - When its launched, it opens a connection with the Server via the TCP IP and the Port, each GUI Page represents a different player;
  - Is build using Multi-Threading Architecture, each client represents a Thread;
  - The path of each selected piece on the chesstable will be highlighted using Blue color;
  - Each message or information will be represented as a notification.

3. Chess Logic :
  - Each Move is analyzed and checks for its valadity;
  - Each Piece is treated independently using custom paths;
  - It is responsible for checking the Check Condition and the Winner Condition;
  - It has also Promotion feature;
  - It works as a service for the server.

This project incorporates the main OOP Principles, and strongly encourages the OOP Thinking for the Data Abstraction.

There are also another features under work, for example implementation using Bitboards and developing of an efficient algorithm to determine the next position (maybe a neural network).

More information is available in the file "Documentatie_OOP.pdf" which representens the main documentation of this project.
