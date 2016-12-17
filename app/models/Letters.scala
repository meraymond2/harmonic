package models

/**
  * Created by michael on 17/12/16.
  */

case class LetterIndex(id: Int) {

  def +(n: Int): Int = {
    val result = this.id + n
    if (result > 7) result - 7 else result
  }

  def -(n: Int): Int = {
    val result = this.id - n
    if (result < 1) result + 7 else result
  }
}

case class Letter private (name: String, index: LetterIndex)

object Letter {
  val a = Letter("A", LetterIndex(1))
  val b = Letter("B", LetterIndex(2))
  val c = Letter("C", LetterIndex(3))
  val d = Letter("D", LetterIndex(4))
  val e = Letter("E", LetterIndex(5))
  val f = Letter("F", LetterIndex(6))
  val g = Letter("G", LetterIndex(7))
}