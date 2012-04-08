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
