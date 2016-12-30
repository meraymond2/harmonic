package models.keys

import dao.IntervalDb._
import models.chords.Chord
import models.chords.ChordClasses.{diminishedTriad, dominantSeventh, halfDimSeventh, majorSeventh, majorTriad, minorSeventh, minorTriad}
import models.notes.PitchClasses.PitchClass

/**
  * Created by michael on 29/12/16.
  */

class MajorKey(root: PitchClass) extends Key {

  val name: String = root.toString + " major"

  // Diatonic Notes
  val tonic: PitchClass            = root
  lazy val supertonic: PitchClass  = tonic ↑ maj2nd
  lazy val mediant: PitchClass     = tonic ↑ maj3rd
  lazy val subdominant: PitchClass = tonic ↑ per4th
  lazy val dominant: PitchClass    = tonic ↑ per5th
  lazy val submediant: PitchClass  = tonic ↑ maj6th
  lazy val leadingTone: PitchClass = tonic ↑ maj7th

  // Diatonic Chords
  lazy val chords: Set[Chord] = Set(
    (majorTriad.andInversions ++ majorSeventh.andInversions).map(chordClass => Chord(tonic, chordClass)),
    (minorTriad.andInversions ++ minorSeventh.andInversions).map(chordClass => Chord(supertonic, chordClass)),
    (minorTriad.andInversions ++ minorSeventh.andInversions).map(chordClass => Chord(mediant, chordClass)),
    (majorTriad.andInversions ++ majorSeventh.andInversions).map(chordClass => Chord(subdominant, chordClass)),
    (majorTriad.andInversions ++ dominantSeventh.andInversions).map(chordClass => Chord(dominant, chordClass)),
    (minorTriad.andInversions ++ minorSeventh.andInversions).map(chordClass => Chord(submediant, chordClass)),
    (diminishedTriad.andInversions ++ halfDimSeventh.andInversions).map(chordClass => Chord(leadingTone, chordClass))
  ).flatten

}
