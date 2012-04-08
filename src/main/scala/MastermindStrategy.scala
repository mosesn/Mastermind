trait MastermindStrategy {
  def getNextMove: Array[Int]
  def observeResult(numColorsCorrect: Int, positionsCorrect: Int)
  def continuePlaying: Boolean
}
