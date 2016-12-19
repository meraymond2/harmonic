package models.chords

import models.chords.ChordClasses.ChordClass
import models.notes.PitchClasses.PitchClass

/**
  * Created by michael on 19/12/16.
  */


/***
  * I'm not sure what else this should have for now. Maybe the notes that make it up?
  * @param root Not the same as the bass.
  * @param chordClass The type of chord.
  */
case class Chord(root: PitchClass, chordClass: ChordClass)
