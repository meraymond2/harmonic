package models.notes

import dao.IntervalDb.unknownInterval
import dao.{IntervalDb, NoteDb}
import dao.NoteDb.{A0, C8}
import models.intervals.Interval
import models.notes.PitchClasses.PitchClass
import models.notes.PitchLetters.PitchLetter
import models.notes.PitchLetters.wrap

/**
  * Created by michael on 19/12/16.
  */

/***
  * Notes
  * @param spn:  The scientific pitch notation, e.g. C4, Cs4, Db4. This is unique to each note.
  * @param absPitch: The specific pitch, corresponding to piano key. Multiple notes may have the same absolute pitch (enharmonically).
  * val pitchClass:  The note's pitch class (relative pitch), e.g. A, Bb, G#. This repeats every octave.
  * val pitchLetter: The note's letter. These are used for working out intervals.
  */

case class Note (spn: String, absPitch: Int) {

  lazy val pitchClass: PitchClass = PitchClasses.withName(spn.init)
  lazy val pitchLetter: PitchLetter = PitchLetters.withName(spn.head.toString)

  /***
    * Returns the difference between two notes in place, so it doesn't consider direction.
    * @param otherNote The second note to compare; it doesn't matter if it's higher or lower.
    * @return A static interval. It's a bit strange to return a placeholder unknown interval instead of an
    *         option, but it works for the chord-matching, and if it needs to be returned to a user, it's
    *         as good to display an unknown interval as it is to display no interval.
    */
  def diff(otherNote: Note): Interval = {
    val Seq(lower, upper) = Seq(this, otherNote).sortBy(_.absPitch)
    IntervalDb.intervals.find( interval =>
      (interval.letterDiff == wrap(upper.pitchLetter.id - lower.pitchLetter.id))
        &&
      (interval.pitchDiff == upper.absPitch - lower.absPitch)
    ).getOrElse(unknownInterval)
  }

  /***
    * Given a note and a static interval, find the note which is above the
    * starting note by that interval.
    * @param interval A static interval.
    * @return The incremented note, or the highest note if the note's not found.
    */
  def ↑(interval: Interval): Note = {
    NoteDb.notes.find( note =>
      (note.absPitch == this.absPitch + interval.pitchDiff) &&
      (note.pitchLetter.id == wrap(this.pitchLetter.id + interval.letterDiff))
    ).getOrElse(C8)
  }

  def ↓(interval: Interval): Note = {
    NoteDb.notes.find( note =>
      (note.absPitch == this.absPitch - interval.pitchDiff) &&
      (note.pitchLetter.id == wrap(this.pitchLetter.id - interval.letterDiff))
    ).getOrElse(A0)
  }

  override def toString: String = this.spn
}
