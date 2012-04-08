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
