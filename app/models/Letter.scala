package models

/**
  * Created by michael on 17/12/16.
  */

case class Letter private (name: String, index: Int) {

  def above(letter: Letter): Int = {
    this.index - letter.index match {
      case n if n < 0 => n + 7
      case n if n > 7 => n - 7
      case n          => n
    }
  }

}

object Letter {
  val a = Letter("A", 1)
  val b = Letter("B", 2)
  val c = Letter("C", 3)
  val d = Letter("D", 4)
  val e = Letter("E", 5)
  val f = Letter("F", 6)
  val g = Letter("G", 7)
}

