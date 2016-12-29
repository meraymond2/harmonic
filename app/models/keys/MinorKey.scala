package models.keys

import dao.IntervalDb._
import models.chords.Chord
import models.chords.ChordClasses._
import models.notes.Note
import models.notes.PitchClasses.PitchClass

/**
  * Created by michael on 28/12/16.
  */

class MinorKey (root: Note) extends Key {

  // Diatonic Notes
  val tonicNote: Note = root
  lazy val supertonicNote: Note = tonicNote ↑ maj2nd
  lazy val mediantNote: Note = tonicNote ↑ min3rd
  lazy val subdominantNote: Note = tonicNote ↑ per4th
  lazy val dominantNote: Note = tonicNote ↑ per5th
  lazy val submediantNote: Note = tonicNote ↑ min6th
  lazy val leadingNote: Note = tonicNote ↑ maj7th

  // Diatonic Chords
//  lazy val tonicTriad: Chord = Chord(tonicNote.pitchClass, minorTriad)
//  lazy val supertonicTriad: Chord = Chord(supertonicNote.pitchClass)
//  lazy val mediantTriad: Chord =
//  lazy val subdominantTriad: Chord =
//  lazy val dominantTriad: Chord =
//  lazy val submediantTriad: Chord =
//  lazy val leadingTriad: Chord =

}
