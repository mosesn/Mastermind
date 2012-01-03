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

object MastermindStateGenerator {
  def generate(beads: Int, colors: Int): MastermindState = {
    val beadState = new Array[Int](beads)
    val colorState = new Array[Int](colors)
    val random = new Random
    for (elt <-  0 to beads - 1) {
      val color = random.nextInt(colors)
      beadState(elt) = color
      colorState(color) += 1
    }
    MastermindState(beads, colors, beadState, colorState)
  }
}

trait MastermindStrategy {
  def getNextMove: Array[Int]
  def observeResult(numColorsCorrect: Int, numPositionsCorrect: Int)
  def continuePlaying: Boolean
}

class ColorStrategy(beads: Int, colors: Int) extends MastermindStrategy {
  var curColor = -1
  var curCorrect = 0
  val correctColors = Array.fill[Int](beads)(-1)


  override def getNextMove: Array[Int] = {
    val curGuess = new Array[Int](beads)

    curColor += 1
    for ((value, index) <- correctColors.zipWithIndex) {
      if (value != -1) {
        curGuess(index) = value
      }
      else {
        curGuess(index) = curColor
      }
    }
    curGuess
  }

  override def observeResult(numColorsCorrect: Int, numPositionsCorrect: Int) = {
    for (index <- curCorrect to (numColorsCorrect - 1)) {
      correctColors(index) = curColor
    }
    curCorrect = numColorsCorrect
  }

  override def continuePlaying: Boolean = (curCorrect != beads)

}

class BruteForceStrategy(beads: Int, colors: Int) extends MastermindStrategy {
  val curStrat = new Array[Int](beads)

  override def getNextMove: Array[Int] = {
    next
    curStrat
  }

  override def observeResult(numColorsCorrect: Int, numPositionsCorrect: Int) = null

  override def continuePlaying = true

  def next {
    var continue = true
    for ((value, index) <- (curStrat zipWithIndex) reverse) {
      if (continue) {
        if (value + 1 == colors) {
          curStrat(index) = 0
        }
        else {
          curStrat(index) += 1
          continue = false
        }
      }
    }
  }
}

case class MastermindState(beads: Int, colors: Int, beadState: Array[Int],
                           colorState: Array[Int])

class MastermindSimulator(state: MastermindState) {
  var attempts = 0

  def this(b: Int, c: Int) = this(MastermindStateGenerator generate (b, c))

  override def toString() :String = {
    state.beadState.deep.toString()
  }

  def getState = state

  def evaluateGuess(guess: Array[Int]) :Tuple2[Int, Int] = {
    attempts += 1
    val colorsSeen = new Array[Int](state.colors)
    var numColorsCorrect = 0
    var numPositionsCorrect = 0

    for ((value, index) <- guess zipWithIndex) {
      colorsSeen(value) += 1
      if (state.beadState(index) == value) {
        numPositionsCorrect += 1
      }
      if (colorsSeen(value) <= state.colorState(value)) {
        numColorsCorrect += 1
      }
    }

    new Tuple2(numColorsCorrect, numPositionsCorrect)
  }

}
