=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections; Yes; I will have an ArrayList of distinct Asteroid 
  objects, and will remove asteroids from the set, given that 
  the player destroys an asteroid. The ArrayList will also be added to
  if an asteroid is split up into more asteroids.  
  
  2. JUnit Testing; I tested the main functionality of the game, such as 
   ensuring that collisions have been detected to their full extent, so that the 
   remaining conditions of the game can be fulfilled (ex: adding to score 
   given collision, winning condition depending on collision condition), as well 
   as functions such as deleting an asteroid.

  3. Dynamic dispatch; I created a new interface for the projectiles that the player
  must avoid, which includes the one invincible rogue asteroid and the rest of the 
  normal asteroids; each has a distinct "attack" method, as the rogue asteroid 
  changes direction and speed every few seconds, while the normal asteroids keep going
  in one direction. Each asteroid type also has a different look.

  4. Advanced topics: collisions. My main player object is a complex polygon with 
  multiple coordinates (the corners), and the collisions between the player and asteroid
  projectiles is complex and beyond normal bounding boxes; I used the distance formula
  between the approximate radius of the player to approximate an exact colision between 
  the complex polygon shape and the asteroids. 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  My asteroid class is dedicated to an asteroid object, which are the normal
  asteroids that go in a singular direction at a randomly generated speed;
  Each has a radius, speed, health, and initial position; the players must avoid these, 
  and destroy them. If one is destroyed, it may split into more asteroids; there
  is thus a function to create split asteroids. The enemy class (rogue asteroid) 
  is like the asteroid,but without the health parameter, and a different attack method; 
  this brings us to the Obstacle interface, which describes the rogue asteroid and normal 
  asteroids. GameCourt holds the primary game logic and interactions between objects 
  of the game. The laser class is related to the Player class in the way that a laser
  shoots out of the player, through a shoot function; the player has a reload time as 
  well, which the laser must check for before releasing. The Player class, of course,
  is for controlling the player, the main object that the user controls (moves and shoots).
  The Game clsas is for running the actual game and tying everything together.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  The hardest part of the game was creating the player, as Java Swing has 
  no specific function to create the shape I desired; I thus had to hard-code
  the approximate coordinates to create the polygon. Deriving the function for
  player-asteroid collisions was also quite difficiult, and it didn't occur to me
  to use the distance formula until later on. 


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  There is a fair separation of functionality between classes, and each class has a 
  different role and purpose, and interactions between objects are organized intuitively. 
  I would try to refactor my tick method in Game, so that it is easier to read,
  although everything works as it should. The private state of the game is generally 
  encapsulated well with private fields and methods, and only certain objects can access
  data from other certain objects.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  https://en.wikipedia.org/wiki/Asteroids_(video_game)
  
  https://www.baeldung.com/java-distance-between-two-points
