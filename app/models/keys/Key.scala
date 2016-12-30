package models.keys

import models.chords.Chord
import models.notes.PitchClasses.PitchClass

/**
  * Created by michael on 29/12/16.
  */

trait Key {

  val name: String
  val tonic: PitchClass
  val supertonic: PitchClass
  val mediant: PitchClass
  val subdominant: PitchClass
  val dominant: PitchClass
  val submediant: PitchClass
  val leadingTone: PitchClass

  val chords: Set[Chord]

  def contains(chord: Chord): Boolean = chords.contains(chord)

  /***
    * For a given pitch-class, returns it's position in the key, or None if
    * it's not a member of that key.
    * @param pitchClass a pitch-class to check
    * @return A string describing the scale degree.
    */
  def scaleDegree(pitchClass: PitchClass): Option[String] = pitchClass match {
    case `tonic` => Some("Tonic")
    case `supertonic` => Some("Supertonic")
    case `mediant` => Some("Mediant")
    case `subdominant` => Some("Subdominant")
    case `dominant` => Some("Dominant")
    case `submediant` => Some("Submediant")
    case `leadingTone` => Some("Leading Tone")
    case _ => None
  }
}
