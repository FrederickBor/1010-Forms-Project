# 1010 Forms

This repository contains the source code for a video game that I made at the Universidad Católica Andrés Bello, as part of my evaluations on the subject "Algoritmos y Programación 3".

## How is structured

The code is easily structured in one folder called `Screens` an outside this folder all the logic parts of the game.

You will find one class per object needed at screens, the main functionality is given by the following classes:

|Class|Definition|
|-----|----------|
|Box|All pieces and the board are made of this. <br> Basically this is the more important class.|
|Piece|This implements the functionality to generate and build in a random way all the possible pieces which are declared in the `shape` file.|
|PieceBar|This implements the functionality to handle the 3 pieces generated by turns.|
|Board|On this you can put the pieces randomly generated by the game.|

## Final results

<center>

![Gameplay](sources/1010FormsGameplay.gif)

</center>