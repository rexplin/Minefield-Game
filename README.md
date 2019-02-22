# Project 5: Minewalker

* Author: Alex Esplin
* Class: CS121 Section 3
* Semester: Fall 2017

## Overview

This program creates a game called Minewalker. You start at the bottom left corner and try to make it to the top right. On the
way you must avoid the mines. the color of the square you are in changes based on how many mines you are next to. You have the 
option to show and hide the mines, as well as a safe path through them. The game keeps score based on how many moves you've made
and how many mines you hit. You have five lives to get through.

## Compiling and Using

To compile the code: javac MineWalker.java
To run the code: java MineWalker

Once in the game you will have the option to start a new game by hitting the give up button
The show/hide mines button allows you to look at the minefield to see where the mines are
The show/hide path button will show you one safe path through the field, there may be others

## Discussion

One of the most challenging problems for me was determining the scope of the listeners and some variables. I originally put the 
listener in the class that I felt it fit in. Only to realize that some of the things I wanted that listener to do required
access to Objects that weren't available to that class. I had to move all my listeners to the MineWalkerPanel class to have 
access to the Objects that I needed for them to work correctly. Another challenge I ran into was in making sure you could only
click on a button within one space away. I had solved this on Windows by comparing the x and y values of the current JButton
with the x and y values of the new one I was clicking on. This worked fine until I resized the screen, increased the grid size
or ran it on Onyx. I realized that the x and y variables were to changeable and I needed something more permanent. So I 
changed my check to the array. I would just ensure that the new button was within one of the [i] or the [j]. This worked
except it caused out of bounds exceptions when I was on the edges. It took me a while to solve those exceptions while still
allowing movement in all directions.

To create the program I started at the basics. I wrote all the different panels I needed and added them. Then I wrote all the buttons
so I had the stuff all showing up, but not doing anything. Then I started the hard part of adding the functionality I wanted.
Once that was done I finished up by polishing the UI to make it look nicer and make more sense.

I think this project helped me understand how complicated GUI stuff is. Tiny changes were hard to make, and often they would
affect something that I hadn't expected that it would. I had to be methodical in testing to make sure no unexpected problems
came up. I also got a feel for the way you start a project, or how you can get started by doing the basics before coming back
and adding in complexity.

## Testing

I tested this project by running it many many times. I played through various scenarios, such as running out of lives,
reaching the end, or running down the score. I also tested movement along the boundaries to make sure there weren't any
array out of bounds errors.


## Sources used

I used some of the Java API's. The one for JButton and for JPanel.