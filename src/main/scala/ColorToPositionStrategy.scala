import scala.collection.mutable.MutableList

class ColorToPositionStrategy(beads: Int, colors: Int) extends MastermindStrategy {
  var curColor = -1
  var curCorrect = 0
  var mode = 0
  val correctColors = Array.fill[Int](beads)(-1)
  val history = new Array[Tuple2[Int, Int]](colors)
  val information = new MutableList[Info]()

  override def getNextMove: Array[Int] = {
    if (mode == 0) {
      val curGuess = new Array[Int](beads)

      curColor += 1
      for ((value, index) <- correctColors.zipWithIndex) {
        if (value != -1) {
          curGuess(index) = value
        } else {
          curGuess(index) = curColor
        }
      }
      curGuess
    } else if (mode == 1) {
      val possibilities = new Array[List[Int]](beads)
      val attempt = Array.fill[Int](beads)(-1)
      for (datum <- information) {
        if (datum.positions.length == datum.numberOfBeads) {
          for (position <- datum.positions) {
            attempt(position) = datum.color
          }
        } else {
          var count = 0
          for (position <- datum.positions) {
            if (attempt(position) != -1) {
              attempt(position) = datum.color
              count += 1
            }
          }
        }
      }
      null
      //TODO: Moses, build this the fuck out
      //TODO: what the fuck am I supposed to do?
    }
    else {
      null
    }
  }

  override def observeResult(colorsCorrect: Int, positionsCorrect: Int) = {
    history(curColor) = new Tuple2(colorsCorrect, positionsCorrect)
    if (mode == 0) {
      for (index <- curCorrect to (colorsCorrect - 1)) {
        correctColors(index) = curColor
      }
      curCorrect = colorsCorrect
      if (curCorrect == beads) {
        mode = 1
      }
    } else if (mode == 1) {
      //TODO: Moses, build this the fuck out
    }
  }

  override def continuePlaying: Boolean = true

}
