package models.notes

import com.fasterxml.jackson.annotation.JsonFormat
import dao.{IntervalDb, NoteDb}
import models.intervals.HarmonicInterval
import models.notes.PitchClasses.PitchClass
import models.notes.PitchLetters.PitchLetter
import play.api.libs.json.{Json, OFormat}

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
    * Calls the IntervalDb.harmonicInterval method with the self as the first note.
    * @param otherNote Another note to compare against.
    * @return The interval between the two notes.
    */
  def diff(otherNote: Note): HarmonicInterval = IntervalDb.harmonicInterval(this, otherNote)

  /***
    * Given another note and a static interval, finds the note which is above the
    * starting note (the note the method is invoked upon) by that interval.
    * @param interval A static interval.
    * @return The incremented note, or the highest note if the note's not found.
    */
  def ↑(interval: HarmonicInterval): Note = NoteDb.noteAbove(this, interval)


  /***
    * Given another note and a static interval, finds the note which is below the
    * starting note (the note the method is invoked upon) by that interval.
    * @param interval A static interval.
    * @return The incremented note, or the lowest note if the note's not found.
    */
  def ↓(interval: HarmonicInterval): Note = NoteDb.noteBelow(this, interval)

  override def toString: String = this.spn
}

object Note {

  implicit val noteFormat: OFormat[Note] = Json.format[Note]

}