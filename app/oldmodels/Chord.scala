package oldmodels

import ChordNames._
import ChordNames.ChordName
import PitchClasses._

/**
  * Created by michael on 17/12/16.
  */

case class Chord(bass: Tone, tenor: Tone, alto: Tone, soprano: Tone) {

  def classify: Option[(ChordName, Option[PitchClass])] = {
    val tones = Seq(this.bass, this.tenor, this.alto, this.soprano)

    // Chord Pattern
    val pitchClasses = tones.map(_.pitchClass).toSet
    println(pitchClasses)

    // Duplicated Note
    val byPitchClass = tones.groupBy(_.pitchClass)
    val duplicated = byPitchClass.keys.size == 3
    val duplicatedTone = if (duplicated) Some(byPitchClass.maxBy( pair => pair._2.size )._1) else None

    // Return
    Some( minorTriad, duplicatedTone )
  }

}

