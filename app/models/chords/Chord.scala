package models.chords

import models.chords.ChordClasses.ChordClass
import models.notes.PitchClasses.PitchClass
import play.api.libs.json._

/**
  * Created by michael on 19/12/16.
  */


/***
  * I'm not sure what else this should have for now. Maybe the notes that make it up?
  * @param root Not the same as the bass.
  * @param chordClass The type of chord.
  */
case class Chord(root: PitchClass, chordClass: ChordClass)
object Chord {
  implicit val chordFormat: OFormat[Chord] = Json.format[Chord]
}