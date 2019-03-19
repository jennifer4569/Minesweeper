# MINESWEEPER
### Team Yuzuru Hanyu
#### Jennifer Zhang and Bermet Kalmakova

## Introduction
This project is a single-player strategy game based off of Minesweeper. The goal of the game is to reveal all the spaces with no bombs on them. If you reveal a space with a bomb, you lose. Revealing a space without a bomb will show you a number, which tells you the number of bombs immediately surrounding that space.

## Setup Instructions 
   1. Go to terminal/command prompt
   2. Change directory (cd) to MinesweeperFinal
   3. Compile the Minesweeper.java file (javac Minesweeper.java)
   4. Run the Minesweeper file (java Minesweeper)

## Directions
  - When you open run the program, you will see a window that will have 3 buttons for easy, medium, and hard. Clicking on those buttons will create a puzzle in the specified size.
   - Below those buttons, there is a box that you can input a number from 4 to 20. Clicking on Start Custom Game will start a custom game with the specified number as the length and width of the grid.
  - Play the Minesweeper game the way you normally play Minesweeper.
   - Left click reveals the space, and right click flags the space.
  - On the top of the window, there is a drop-down menu for new game, a how to play button, and a flag count. The drop-down menu has the standard 3 versions (easy, medium, hard), and when you click it, it creates a new game in the specified size. The how to play button, when clicked, will have a window pop up, and the window will have all the directions on how to play minesweeper. Flag count shows the number of flags you have on the grid right now over the total number of bombs in the grid.
  - When you win/lose, there will be a window that pops up, prompting you to start a new game. Clicking that will go back to the original window seen at the start of running the program.

## Working Features
  - Different size games
  - Winning/losing
  - Flag count
  - New game from in game
  
## Unresolved Bugs
  - If you don't input a number or if you put in a non-integer in the input box for custom game, there's an error.
  - Depending on the screen's resolution, the bigger grids' buttons are sometimes are too small for the numbers to fit in.
  - Clicking on a blank block doesn't reveal all the blank blocks around it.
