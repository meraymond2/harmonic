package models.keys


import models.chords.Chord
import models.chords.ChordClasses._
import models.notes.PitchClasses._

/**
  * Created by michael on 28/12/16.
  */

case class Key private (i: Chord, ii: Chord, iii: Chord, iv: Chord, v: Chord, vi: Chord, vii: Chord)

object KeyDao {

//  val Amin = Key(Chord(A, minorTriad), Chord(B, diminishedTriad), Chord(C, augmentedTriad), Chord(D, minorTriad), Chord(E, majorTriad))

}
