# BowlingGameKata

 Bowling Game Kata, which is a programming exercise that helps developers practice object-oriented design, test-driven development, and refactoring. The objective of the kata is to create a program that calculates the score of a bowling game based on the rolls and frames of the game.

The project is written in Java and can be run as a standalone application with command line inputs. The main class of the application is BowlingGame, which contains the main method that accepts command line arguments and calculates the score of the game.

To run the application, you need to compile the source code and run the compiled class file with the command line arguments. The command line arguments should be a string of characters that represent the rolls of the game. The characters are:

X: a strike (knocking down all 10 pins on the first roll of a frame) <br>
/: a spare (knocking down all remaining pins on the second roll of a frame)<br>
-: a miss (failing to knock down any pins on a roll)<br>
a number between 1 and 9: the number of pins knocked down on a roll


Here's an example command line input:
````
java BowlingGame "X X X X X X X X X X X X"
````
This input represents a game with 12 strikes, which is a perfect game, and the output should be:
```` 
300
````
Here are a few more example command line inputs and their expected outputs:

````
java BowlingGame "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"
````
This input represents a game with 10 pairs of 9 and miss, and the output should be:

```` 
90
````
java BowlingGame "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5"

This input represents a game with 10 pairs of 5 and spare, and a final roll of 5, and the output should be
```` 
150
```` 
