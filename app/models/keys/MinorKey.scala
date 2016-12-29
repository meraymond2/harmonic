package models.keys

import dao.IntervalDb._
import models.chords.Chord
import models.chords.ChordClasses._
import models.notes.PitchClasses.PitchClass

/**
  * Created by michael on 28/12/16.
  */

class MinorKey(root: PitchClass) extends Key {

  // Diatonic Notes
  val tonic: PitchClass            = root
  lazy val supertonic: PitchClass  = tonic ↑ maj2nd
  lazy val mediant: PitchClass     = tonic ↑ min3rd
  lazy val subdominant: PitchClass = tonic ↑ per4th
  lazy val dominant: PitchClass    = tonic ↑ per5th
  lazy val submediant: PitchClass  = tonic ↑ min6th
  lazy val leadingTone: PitchClass = tonic ↑ maj7th

  // Diatonic Chords
  lazy val chords: Set[Chord] = Set(
    (minorTriad.andInversions ++ minorMajorSeventh.andInversions).map(chordClass => Chord(tonic, chordClass)),
    (diminishedTriad.andInversions ++ halfDimSeventh.andInversions).map(chordClass => Chord(supertonic, chordClass)),
    Set(Chord(mediant, augmentedTriad)), // Ignoring inversions and seventh chords.
    (minorTriad.andInversions ++ minorSeventh.andInversions).map(chordClass => Chord(subdominant, chordClass)),
    (majorTriad.andInversions ++ dominantSeventh.andInversions).map(chordClass => Chord(dominant, chordClass)),
    (majorTriad.andInversions ++ majorSeventh.andInversions).map(chordClass => Chord(submediant, chordClass)),
    (diminishedTriad.andInversions ++ diminishedSeventh.andInversions).map(chordClass => Chord(leadingTone, chordClass))
  ).flatten

}
