import scala.util.Random

object Driver {
  def main(args: Array[String]) {
    if (args.length == 2) {
      val beads: Int = args(0).toInt
      val colors: Int = args(1).toInt
      if (beads > 1 && colors > 1) {
	val sim: MastermindSimulator = new MastermindSimulator(beads, colors)
	println(sim)
      }
      else {
	println("Number of beads should be more than one, and number of " +
		"colors should be more than one.")
      }
    }
    else {
      println("Please provide two integer arguments, one for the number of" +
	      " beads and the other for the number of colors.")
    }
  }
}

object MastermindStateGenerator {
  def generate(beads :Int, colors: Int) :Tuple2[Int, Array[Int]] = {
    val tmp: Array[Int] = new Array[Int](beads)
    val rand: Random = new Random
    for (elt <-  0 to beads - 1) {
      tmp(elt) = rand.nextInt(colors)
    }
    new Tuple2(0, tmp)
  }
}

class MastermindSimulator(b: Int, c: Int, a: Tuple2[Int, Array[Int]]) {
  val beads: Int = b
  val colors: Int = c
  val attempts: Int = a._1
  val state: Array[Int] = a._2

  def this(b: Int, c: Int) = this(b, c, MastermindStateGenerator generate (b, c))

  override def toString() :String = {
    state.deep.toString
  }

}
