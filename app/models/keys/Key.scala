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
    case pC if pC == tonic => Some("Tonic")
    case pC if pC == supertonic => Some("Supertonic")
    case pC if pC == mediant => Some("Mediant")
    case pC if pC == subdominant => Some("Subdominant")
    case pC if pC == dominant => Some("Dominant")
    case pC if pC == submediant => Some("Submediant")
    case pC if pC == leadingTone => Some("Leading Tone")
    case _ => None
  }
}
