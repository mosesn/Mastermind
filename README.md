#Mastermind AI in Scala

##Running
Start sbt, and type run.

##On Scala
We are using this project to learn Scala.  If you see something that seems wrong
to you, please tell us!  We will not be offended, we want to learn.

##People
Moses (mmn2104 on github)  
Matt (dannenberg on github)

##Progress
State generation works

##Next Steps
Make a common Strategy trait that can be passed to the simulator  
Have simulating a turn return the result (correct, correct position)
and the next simulator

##Refactoring Idea
Have the state, number of beads, and number of colors all be part of an immutable
object.