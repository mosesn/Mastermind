import scala.util.Random

object Driver {
  def main(args: Array[String]) {
    if (args.length == 2) {
      val beads: Int = args(0).toInt
      val colors: Int = args(1).toInt
      if (beads <= 1 && colors <= 1) {
        println ("Number of beads should be more than one, and number of " +
                 "colors should be more than one.")
      }
      val sim = new MastermindSimulator(beads, colors)
      println(sim)
      println(runGame(sim, new ColorStrategy(beads, colors)))
    }
    else {
      println ("Please provide two integer arguments one for the number of " +
               "beads and the other for the number of colors.")
    }
  }

  def runGame(simulator :MastermindSimulator, strat :MastermindStrategy): Int = {
    var colorPosition = new Tuple2(0, 0)
    while ((strat continuePlaying) && (colorPosition._2 < (simulator getState).beads)) {
      colorPosition = (simulator evaluateGuess (strat getNextMove))
      strat observeResult (colorPosition._1, colorPosition._2)
    }
    if (colorPosition._2 == (simulator getState).beads) {
      simulator.attempts
    }
    else {
      println(simulator.attempts)
      -1
    }
  }
}

