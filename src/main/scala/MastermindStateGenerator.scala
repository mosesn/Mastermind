import scala.util.Random

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
