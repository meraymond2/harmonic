package models

import PitchClasses._
import PitchClasses.PitchClass

case class Tone(pitchClass: PitchClass, spn: String, keyN: Int)

object Tone {

  val tones = Map[Int, Tone](
    1 -> Tone(A, "A0", 1)
  )

}
