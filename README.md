# Rolling Cube Demo

	This is a representation of a logical game which I have to implement for a project in my Softvare Engeneering class.

## Game rules
	You are playing with a cube which has 1 red side and 5 blue sides.
	You have to get to the goal field by rolling without touching the ground with the red side of the cube. 
	The map has blocked fields, where are other cubes sitting so the player has to bypass those fields.
	The player's cube spawns with the red side facing up.	
	
## Game represetation
	* The program reads the map from a txt (5x5).
	* Open fields are represented with 6.
	* Blocked fields are represented with 7.
	* Goal field('s) is(/are) represented with 8.
	* The player's cube is represented with [0..5]
		1. 0: The red side.
		2. 1..5: The blue sides.
	* In the matrix representation the player's cube can be found by the number of the current side facing up.
	* The scores of the players are represented with a map, so there can't be multiple players with the same name. The program overrides the score when the player reaches a better score.

## Getting started

Just compile Main and run.

```bash
$ javac Main.java
```

```bash
$ java Main
```

> NOTE This demo is displaying everything to the standard output.
