# PSL ASSESSMENT: Minesweeper 
## Description  
This project has been developed in Java. It's a classic game of Minesweeper that is played using your terminal. When the game is begining you will see this:  
```
WELCOME TO THE GAME: MINESWEEPER
First, type the board height, width and amount of mines. Each number separed by a blank space (For example:8 15 10)
```  
You will have to type the height, width and number of mines. Something like this:  
```
8 15 10
```  
Then the game request a movement:  
```  
ROUND #1

Look the board: 
	1	2	3	4	5	6	7	8	
	________________________________________________________________
1	|.	.	.	.	.	.	.	.	|
2	|.	.	.	.	.	.	.	.	|
3	|.	.	.	.	.	.	.	.	|
4	|.	.	.	.	.	.	.	.	|
5	|.	.	.	.	.	.	.	.	|
6	|.	.	.	.	.	.	.	.	|
7	|.	.	.	.	.	.	.	.	|
8	|.	.	.	.	.	.	.	.	|
9	|.	.	.	.	.	.	.	.	|
10	|.	.	.	.	.	.	.	.	|
11	|.	.	.	.	.	.	.	.	|
12	|.	.	.	.	.	.	.	.	|
13	|.	.	.	.	.	.	.	.	|
14	|.	.	.	.	.	.	.	.	|
15	|.	.	.	.	.	.	.	.	|
	|________________________________________________________________


Type the row, col and the action. Each value separed by a blank space (For example:1 1 U)
```  
We have to choose the position of your action. For example: I want to mark the cell located at 1,1:  
```  
1 1 M

Look the board: 
	1	2	3	4	5	6	7	8	
	________________________________________________________________
1	|P	.	.	.	.	.	.	.	|
2	|.	.	.	.	.	.	.	.	|
3	|.	.	.	.	.	.	.	.	|
4	|.	.	.	.	.	.	.	.	|
5	|.	.	.	.	.	.	.	.	|
6	|.	.	.	.	.	.	.	.	|
7	|.	.	.	.	.	.	.	.	|
8	|.	.	.	.	.	.	.	.	|
9	|.	.	.	.	.	.	.	.	|
10	|.	.	.	.	.	.	.	.	|
11	|.	.	.	.	.	.	.	.	|
12	|.	.	.	.	.	.	.	.	|
13	|.	.	.	.	.	.	.	.	|
14	|.	.	.	.	.	.	.	.	|
15	|.	.	.	.	.	.	.	.	|
	|________________________________________________________________

Type the row, col and the action. Each value separed by a blank space (For example:1 1 U)

```  
Also, you can uncover a cell like this:  
```  
8 15 U

Look the board: 

	1	2	3	4	5	6	7	8	
	________________________________________________________________
1	|P	.	.	.	.	.	.	.	|
2	|.	.	.	.	.	.	.	.	|
3	|.	.	.	.	.	.	.	.	|
4	|.	.	.	.	.	.	.	.	|
5	|.	.	.	.	.	.	.	.	|
6	|.	.	.	.	.	.	.	.	|
7	|.	.	.	.	.	.	.	.	|
8	|.	.	.	.	.	.	.	.	|
9	|.	.	.	.	.	.	.	.	|
10	|.	.	.	.	.	.	.	.	|
11	|.	.	.	.	.	1	1	1	|
12	|.	.	.	.	.	1	-	-	|
13	|.	.	1	1	1	1	-	-	|
14	|.	.	1	-	-	-	-	-	|
15	|.	.	1	-	-	-	-	-	|
	|________________________________________________________________

Type the row, col and the action. Each value separed by a blank space (For example:1 1 U)
```  


Now you just have to enjoy the game :)

## Instructions to play for Linux  

### Easy way  
If you are a Linux user, you have a shortcut :) ...  
1. You have to be inside the project folder.  
2. (optional) You execute the following command in your terminal:  
```
chmod +x run.sh
```  
This step is optional if the third step doesn't work.
3. Finally, run:  
```
./run.sh
```  

### Hard way
If you want to play a game you have to:  

1. You have to be inside the project folder.  

2. Open terminal and paste:  
```
cd src/main/java/co/edu/icesi/minesweeper/logic/
```  

3. Compile the java class using:  
```
javac Board.java Cell.java Minesweeper.java 
```  

4. Execute the game! To do this run:  
```
java Minesweeper
```  

## Instructions to play for Windows  
### Easy way  
1. You have to be inside the project folder.  
2. run:  
```
run.bat
```  
### Hard way

If you want to play a game you have to:  

1. You have to be inside the project folder.  

2. Open terminal and paste:  
```
cd src\main\java\co\edu\icesi\minesweeper\logic\                                       
```  

3. Compile the java class using:  
```
javac Board.java Cell.java Minesweeper.java 
```  

4. Execute the game! To do this run:  
```
java Minesweeper
```  
