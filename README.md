# brick-clicker
A very basic 2D game built using Java Swing

* The game is 2D and uses shape classes, event handlers, and score trackers. After the end of a round of the game, a score is allocated. The score is displayed to players after finishing a game along with their all-time ranking. An interface is available that allows the top 10 all-time best scores to be displayed. 
* The user interface runs as an application that can hold a grid of filled squares. The grid of squares is of length 20 and of height 20. Initially the grid is empty.
* Shapes are encapsulated in their own classes. An abstract class ‘Shape’ is created. This extends to create classes to encapsulate the following types of shape: Squares, Rectangles, Circles, Triangles, Pie shapes (portions of a circle). The shapes are all stored in a single collection. Each shape encapsulates methods to allow it to be drawn to the screen at arbitrary positions, rotations, and sizes.
* Two event handler classes respond to keyboard events (e.g. pressing the arrow keys) and mouse events (e.g. pressing the mouse buttons).
* The game keeps a record of scores. This is done via a text file. A class allows for new scores to be entered into this text file. Methods are used to return the top 10 scores of all time. These scores are displayed on-screen at the end of a round of the game.
