package models.notes

import models.intervals.{Interval, IntervalsDao, unknownInterval}
import models.notes.NotesDao.{A0, C8}
import models.notes.PitchClasses.PitchClass
import models.notes.PitchLetters.PitchLetter
import models.notes.PitchLetters.wrap

/**
  * Created by michael on 19/12/16.
  */

/***
  * Notes
  * val spn:  The scientific pitch notation, e.g. C4, Cs4, Db4. This is unique to each note.
  * val absPitch: The specific pitch, corresponding to piano key. Multiple notes may have the same absolute pitch (enharmonically).
  * val pitchClass:  The note's pitch class (relative pitch), e.g. A, Bb, G#. This repeats every octave.
  * val pitchLetter: The note's letter. These are used for working out intervals.
  */

case class Note(spn: String, absPitch: Int) {

  lazy val pitchClass: PitchClass = PitchClasses.withName(spn.init)
  lazy val pitchLetter: PitchLetter = PitchLetters.withName(spn.head.toString)

  /***
    * Returns the difference between two notes in place, so it doesn't consider direction. It just compares
    * the difference between the lower and higher.
    * @param otherNote The second note to compare; it doesn't matter if it's higher or lower.
    * @return A static interval. It's a bit strange to return a placeholder unknown interval instead of an
    *         option, but it works for the chord-matching, and if it needs to be returned to a user, it's
    *         as good to display an unknown interval as it is to display no interval.
    */
  def diff(otherNote: Note): Interval = {
    val Seq(lower, upper) = Seq(this, otherNote).sortBy(_.absPitch)
    IntervalsDao.intervals.find( interval =>
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
    NotesDao.notes.find( note =>
      (note.absPitch == this.absPitch + interval.pitchDiff) &&
      (note.pitchLetter.id == wrap(this.pitchLetter.id + interval.letterDiff))
    ).getOrElse(C8)
  }

  def ↓(interval: Interval): Note = {
    NotesDao.notes.find( note =>
      (note.absPitch == this.absPitch - interval.pitchDiff) &&
      (note.pitchLetter.id == wrap(this.pitchLetter.id - interval.letterDiff))
    ).getOrElse(A0)
  }

  override def toString: String = this.spn
}

object NotesDao {

  val A0 = Note("A0", 1)
  val As0 = Note("A♯0", 2)
  val Bb0 = Note("B♭0", 2)
  val B0 = Note("B0", 3)
  val C1 = Note("C1", 4)
  val Cs1 = Note("C♯1", 5)
  val Db1 = Note("D♭1", 5)
  val D1 = Note("D1", 6)
  val Ds1 = Note("D♯1", 7)
  val Eb1 = Note("E♭1", 7)
  val E1 = Note("E1", 8)
  val F1 = Note("F1", 9)
  val Fs1 = Note("F♯1", 10)
  val Gb1 = Note("G♭1", 10)
  val G1 = Note("G1", 11)
  val Gs1 = Note("G♯1", 12)
  val Ab1 = Note("A♭1", 12)
  val A1 = Note("A1", 13)
  val As1 = Note("A♯1", 14)
  val Bb1 = Note("B♭1", 14)
  val B1 = Note("B1", 15)
  val C2 = Note("C2", 16)
  val Cs2 = Note("C♯2", 17)
  val Db2 = Note("D♭2", 17)
  val D2 = Note("D2", 18)
  val Ds2 = Note("D♯2", 19)
  val Eb2 = Note("E♭2", 19)
  val E2 = Note("E2", 20)
  val F2 = Note("F2", 21)
  val Fs2 = Note("F♯2", 22)
  val Gb2 = Note("G♭2", 22)
  val G2 = Note("G2", 23)
  val Gs2 = Note("G♯2", 24)
  val Ab2 = Note("A♭2", 24)
  val A2 = Note("A2", 25)
  val As2 = Note("A♯2", 26)
  val Bb2 = Note("B♭2", 26)
  val B2 = Note("B2", 27)
  val C3 = Note("C3", 28)
  val Cs3 = Note("C♯3", 29)
  val Db3 = Note("D♭3", 29)
  val D3 = Note("D3", 30)
  val Ds3 = Note("D♯3", 31)
  val Eb3 = Note("E♭3", 31)
  val E3 = Note("E3", 32)
  val F3 = Note("F3", 33)
  val Fs3 = Note("F♯3", 34)
  val Gb3 = Note("G♭3", 34)
  val G3 = Note("G3", 35)
  val Gs3 = Note("G♯3", 36)
  val Ab3 = Note("A♭3", 36)
  val A3 = Note("A3", 37)
  val As3 = Note("A♯3", 38)
  val Bb3 = Note("B♭3", 38)
  val B3 = Note("B3", 39)
  val C4 = Note("C4", 40)
  val Cs4 = Note("C♯4", 41)
  val Db4 = Note("D♭4", 41)
  val D4 = Note("D4", 42)
  val Ds4 = Note("D♯4", 43)
  val Eb4 = Note("E♭4", 43)
  val E4 = Note("E4", 44)
  val F4 = Note("F4", 45)
  val Fs4 = Note("F♯4", 46)
  val Gb4 = Note("G♭4", 46)
  val G4 = Note("G4", 47)
  val Gs4 = Note("G♯4", 48)
  val Ab4 = Note("A♭4", 48)
  val A4 = Note("A4", 49)
  val As4 = Note("A♯4", 50)
  val Bb4 = Note("B♭4", 50)
  val B4 = Note("B4", 51)
  val C5 = Note("C5", 52)
  val Cs5 = Note("C♯5", 53)
  val Db5 = Note("D♭5", 53)
  val D5 = Note("D5", 54)
  val Ds5 = Note("D♯5", 55)
  val Eb5 = Note("E♭5", 55)
  val E5 = Note("E5", 56)
  val F5 = Note("F5", 57)
  val Fs5 = Note("F♯5", 58)
  val Gb5 = Note("G♭5", 58)
  val G5 = Note("G5", 59)
  val Gs5 = Note("G♯5", 60)
  val Ab5 = Note("A♭5", 60)
  val A5 = Note("A5", 61)
  val As5 = Note("A♯5", 62)
  val Bb5 = Note("B♭5", 62)
  val B5 = Note("B5", 63)
  val C6 = Note("C6", 64)
  val Cs6 = Note("C♯6", 65)
  val Db6 = Note("D♭6", 65)
  val D6 = Note("D6", 66)
  val Ds6 = Note("D♯6", 67)
  val Eb6 = Note("E♭6", 67)
  val E6 = Note("E6", 68)
  val F6 = Note("F6", 69)
  val Fs6 = Note("F♯6", 70)
  val Gb6 = Note("G♭6", 70)
  val G6 = Note("G6", 71)
  val Gs6 = Note("G♯6", 72)
  val Ab6 = Note("A♭6", 72)
  val A6 = Note("A6", 73)
  val As6 = Note("A♯6", 74)
  val Bb6 = Note("B♭6", 74)
  val B6 = Note("B6", 75)
  val C7 = Note("C7", 76)
  val Cs7 = Note("C♯7", 77)
  val Db7 = Note("D♭7", 77)
  val D7 = Note("D7", 78)
  val Ds7 = Note("D♯7", 79)
  val Eb7 = Note("E♭7", 79)
  val E7 = Note("E7", 80)
  val F7 = Note("F7", 81)
  val Fs7 = Note("F♯7", 82)
  val Gb7 = Note("G♭7", 82)
  val G7 = Note("G7", 83)
  val Gs7 = Note("G♯7", 84)
  val Ab7 = Note("A♭7", 84)
  val A7 = Note("A7", 85)
  val As7 = Note("A♯7", 86)
  val Bb7 = Note("B♭7", 86)
  val B7 = Note("B7", 87)
  val C8 = Note("C8", 88)

  val notes = Seq(
    A0, As0, Bb0, B0, C1, Cs1, Db1, D1, Ds1, Eb1, E1, F1, Fs1, Gb1, G1, Gs1, Ab1,
    A1, As1, Bb1, B1, C2, Cs2, Db2, D2, Ds2, Eb2, E2, F2, Fs2, Gb2, G2, Gs2, Ab2,
    A2, As2, Bb2, B2, C3, Cs3, Db3, D3, Ds3, Eb3, E3, F3, Fs3, Gb3, G3, Gs3, Ab3,
    A3, As3, Bb3, B3, C4, Cs4, Db4, D4, Ds4, Eb4, E4, F4, Fs4, Gb4, G4, Gs4, Ab4,
    A4, As4, Bb4, B4, C5, Cs5, Db5, D5, Ds5, Eb5, E5, F5, Fs5, Gb5, G5, Gs5, Ab5,
    A5, As5, Bb5, B5, C6, Cs6, Db6, D6, Ds6, Eb6, E6, F6, Fs6, Gb6, G6, Gs6, Ab6,
    A6, As6, Bb6, B6, C7, Cs7, Db7, D7, Ds7, Eb7, E7, F7, Fs7, Gb7, G7, Gs7, Ab7,
    A7, As7, Bb7, B7, C8
  )

}