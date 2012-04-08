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
