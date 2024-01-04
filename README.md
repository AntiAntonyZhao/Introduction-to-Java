# Introduction to Computer Science

- [Assignment 1: Modeling a University Course Registration System](#assignment-1-modeling-a-university-course-registration-system)
- [Assignment 2: Chess Game Simulation](#assignment-2-chess-game-simulation)
- [Assignment 3: Recursive Solutions for Antenna Pairing and Maze Solver](#assignment-3-recursive-solutions-for-antenna-pairing-and-maze-solver)
- [Assignment 4: Collision Detection in 2D Physics Simulation](#assignment-4-collision-detection-in-2d-physics-simulation)

## Assignment 1: Modeling a University Course Registration System

### Overview
This assignment requires the creation of a Java-based simulation of a university course registration system. It focuses on modeling courses, students, and their interactions within a course registration environment.

### Objectives
- Gain experience with arrays and linked lists.
- Implement a Java class using an array of singly linked lists.
- Develop skills in working with multiple classes and in object-oriented programming in Java.

### Key Components
- **SLinkedList**: A fully implemented linked list class provided for use.
- **Student**: A fully implemented class representing a student. It includes fields such as ID, name, and a list of registered/waitlisted course codes. Key methods include constructor, isRegisteredOrWaitlisted, addCourse, and dropCourse.
- **Course**: A class to be implemented by students, tracking registered students. Key attributes include course code, capacity, a table of students (array of linked lists), size, and a waitlist. Methods to be implemented are changeArrayLength, put, get, remove, getCourseSize, getRegisteredIDs, getRegisteredStudents, getWaitlistedIDs, and getWaitlistedStudents.

### Tasks
- Implement specific methods in the Course class focusing on managing course registrations and waitlists.
- Ensure correct handling of student registrations, waitlists, and course capacity adjustments.

## Assignment 2: Chess Game Simulation

### Overview
This assignment involves creating a digital chess game in Java, acting as a "referee" to ensure valid moves are made and to announce a winner. It concentrates on implementing basic game rules and object-oriented programming without advanced chess concepts.

### Objectives
- Develop an API for a digital chess game.
- Implement game logic in Java, adhering to chess rules.
- Practice object-oriented programming and class implementation.

### Key Components
- **Chess Game Mechanics**: Simplified rules of chess for an 8x8 grid game with six types of pieces (King, Queen, Pawn, Bishop, Knight, Rook). The objective is to capture the opponent's King.
- **Piece Classes**: Creation and implementation of classes extending the Piece class, with each type having specific movement rules.
- **Game Class**: Implementation of game logic and rule enforcement.
- **Board and GameApp Classes**: Provided classes handling game state and the graphical user interface.

### Tasks
- Implement canMove and getSymbol methods for each Piece class.
- Develop the Game class to manage game logic and rules.
- Ensure accurate tracking of moves and game history.

## Assignment 3: Recursive Solutions for Antenna Pairing and Maze Solver

### Overview
This assignment, divided into two parts, emphasizes recursion in solving complex problems: antenna pairing and maze solving.

#### Part I: Finding the Closest Antenna Pair
##### Objective
Find the closest pair of antenna towers for two organizations (A and B) to minimize signal loss.

##### Task
- Implement the ClosestAntennaPair class using recursion, modifying the provided ClosestPair.java starter code.

##### Key Components
- **closestDistance**: A private double value storing the closest distance.
- A template for the constructor and closest() method.

#### Part II: Maze Solver
##### Objective
Implement a recursive solution to a maze problem.

##### Task
- Write a recursive method, solveMazeUtil, to find a path in a maze from the start position to a key, then to the final position.

##### Key Components
- The class Maze with several predefined methods.

## Assignment 4: Collision Detection in 2D Physics Simulation

### Overview
This assignment focuses on collision detection in a 2D physics simulation, resembling a chaotic billiards game with multiple balls.

### Objectives
- Implement collision detection in a real-time 2D physics simulation with multiple moving balls.
- Compare the efficiency and effectiveness of different algorithms (naive and divide-and-conquer) for collision detection.

### Key Components
- **Naive Approach**: Implement basic collision detection with nested loops to check every pair of circles.
- **Divide-and-Conquer Approach**: Use a Bounding Volume Hierarchy (BVH) for more efficient collision detection.
- **Classes and Methods**: Includes Visualizer, Box, Circle, BVH, ContactFinder, ContactResult, and BVHIterator.

### Tasks
- Implement collision detection using both naive and BVH-based approaches.
- Test and optimize algorithms for handling a large number of moving balls.
