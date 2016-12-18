package models

import models.ChordNames.ChordName
import models.PitchClasses._

/**
  * Created by michael on 17/12/16.
  */

case class Chord(base: Tone, tenor: Tone, alto: Tone, soprano: Tone) {

  def classify: Option[(ChordName, Option[PitchClass])] = None

}

object ChordNames extends Enumeration {
  type ChordName = ChordNames.Value

  val majorTriad = Value("Major Triad")
  val minorTriad = Value("Minor Triad")
}