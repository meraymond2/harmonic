package models.notes

import models.intervals.{Interval, IntervalsDao, unknownInterval}
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

trait Note {
  val spn: String
  val absPitch: Int
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

  override def toString: String = this.spn
}

object A0 extends Note {
  override val spn: String = "A0"
  override val absPitch: Int = 1
}
object As0 extends Note {
  override val spn: String = "A♯0"
  override val absPitch: Int = 2
}
object Bb0 extends Note {
  override val spn: String = "B♭0"
  override val absPitch: Int = 2
}
object B0 extends Note {
  override val spn: String = "B0"
  override val absPitch: Int = 3
}
object C1 extends Note {
  override val spn: String = "C1"
  override val absPitch: Int = 4
}
object Cs1 extends Note {
  override val spn: String = "C♯1"
  override val absPitch: Int = 5
}
object Db1 extends Note {
  override val spn: String = "D♭1"
  override val absPitch: Int = 5
}
object D1 extends Note {
  override val spn: String = "D1"
  override val absPitch: Int = 6
}
object Ds1 extends Note {
  override val spn: String = "D♯1"
  override val absPitch: Int = 7
}
object Eb1 extends Note {
  override val spn: String = "E♭1"
  override val absPitch: Int = 7
}
object E1 extends Note {
  override val spn: String = "E1"
  override val absPitch: Int = 8
}
object F1 extends Note {
  override val spn: String = "F1"
  override val absPitch: Int = 9
}
object Fs1 extends Note {
  override val spn: String = "F♯1"
  override val absPitch: Int = 10
}
object Gb1 extends Note {
  override val spn: String = "G♭1"
  override val absPitch: Int = 10
}
object G1 extends Note {
  override val spn: String = "G1"
  override val absPitch: Int = 11
}
object Gs1 extends Note {
  override val spn: String = "G♯1"
  override val absPitch: Int = 12
}
object Ab1 extends Note {
  override val spn: String = "A♭1"
  override val absPitch: Int = 12
}
object A1 extends Note {
  override val spn: String = "A1"
  override val absPitch: Int = 13
}
object As1 extends Note {
  override val spn: String = "A♯1"
  override val absPitch: Int = 14
}
object Bb1 extends Note {
  override val spn: String = "B♭1"
  override val absPitch: Int = 14
}
object B1 extends Note {
  override val spn: String = "B1"
  override val absPitch: Int = 15
}
object C2 extends Note {
  override val spn: String = "C2"
  override val absPitch: Int = 16
}
object Cs2 extends Note {
  override val spn: String = "C♯2"
  override val absPitch: Int = 17
}
object Db2 extends Note {
  override val spn: String = "D♭2"
  override val absPitch: Int = 17
}
object D2 extends Note {
  override val spn: String = "D2"
  override val absPitch: Int = 18
}
object Ds2 extends Note {
  override val spn: String = "D♯2"
  override val absPitch: Int = 19
}
object Eb2 extends Note {
  override val spn: String = "E♭2"
  override val absPitch: Int = 19
}
object E2 extends Note {
  override val spn: String = "E2"
  override val absPitch: Int = 20
}
object F2 extends Note {
  override val spn: String = "F2"
  override val absPitch: Int = 21
}
object Fs2 extends Note {
  override val spn: String = "F♯2"
  override val absPitch: Int = 22
}
object Gb2 extends Note {
  override val spn: String = "G♭2"
  override val absPitch: Int = 22
}
object G2 extends Note {
  override val spn: String = "G2"
  override val absPitch: Int = 23
}
object Gs2 extends Note {
  override val spn: String = "G♯2"
  override val absPitch: Int = 24
}
object Ab2 extends Note {
  override val spn: String = "A♭2"
  override val absPitch: Int = 24
}
object A2 extends Note {
  override val spn: String = "A2"
  override val absPitch: Int = 25
}
object As2 extends Note {
  override val spn: String = "A♯2"
  override val absPitch: Int = 26
}
object Bb2 extends Note {
  override val spn: String = "B♭2"
  override val absPitch: Int = 26
}
object B2 extends Note {
  override val spn: String = "B2"
  override val absPitch: Int = 27
}
object C3 extends Note {
  override val spn: String = "C3"
  override val absPitch: Int = 28
}
object Cs3 extends Note {
  override val spn: String = "C♯3"
  override val absPitch: Int = 29
}
object Db3 extends Note {
  override val spn: String = "D♭3"
  override val absPitch: Int = 29
}
object D3 extends Note {
  override val spn: String = "D3"
  override val absPitch: Int = 30
}
object Ds3 extends Note {
  override val spn: String = "D♯3"
  override val absPitch: Int = 31
}
object Eb3 extends Note {
  override val spn: String = "E♭3"
  override val absPitch: Int = 31
}
object E3 extends Note {
  override val spn: String = "E3"
  override val absPitch: Int = 32
}
object F3 extends Note {
  override val spn: String = "F3"
  override val absPitch: Int = 33
}
object Fs3 extends Note {
  override val spn: String = "F♯3"
  override val absPitch: Int = 34
}
object Gb3 extends Note {
  override val spn: String = "G♭3"
  override val absPitch: Int = 34
}
object G3 extends Note {
  override val spn: String = "G3"
  override val absPitch: Int = 35
}
object Gs3 extends Note {
  override val spn: String = "G♯3"
  override val absPitch: Int = 36
}
object Ab3 extends Note {
  override val spn: String = "A♭3"
  override val absPitch: Int = 36
}
object A3 extends Note {
  override val spn: String = "A3"
  override val absPitch: Int = 37
}
object As3 extends Note {
  override val spn: String = "A♯3"
  override val absPitch: Int = 38
}
object Bb3 extends Note {
  override val spn: String = "B♭3"
  override val absPitch: Int = 38
}
object B3 extends Note {
  override val spn: String = "B3"
  override val absPitch: Int = 39
}
object C4 extends Note {
  override val spn: String = "C4"
  override val absPitch: Int = 40
}
object Cs4 extends Note {
  override val spn: String = "C♯4"
  override val absPitch: Int = 41
}
object Db4 extends Note {
  override val spn: String = "D♭4"
  override val absPitch: Int = 41
}
object D4 extends Note {
  override val spn: String = "D4"
  override val absPitch: Int = 42
}
object Ds4 extends Note {
  override val spn: String = "D♯4"
  override val absPitch: Int = 43
}
object Eb4 extends Note {
  override val spn: String = "E♭4"
  override val absPitch: Int = 43
}
object E4 extends Note {
  override val spn: String = "E4"
  override val absPitch: Int = 44
}
object F4 extends Note {
  override val spn: String = "F4"
  override val absPitch: Int = 45
}
object Fs4 extends Note {
  override val spn: String = "F♯4"
  override val absPitch: Int = 46
}
object Gb4 extends Note {
  override val spn: String = "G♭4"
  override val absPitch: Int = 46
}
object G4 extends Note {
  override val spn: String = "G4"
  override val absPitch: Int = 47
}
object Gs4 extends Note {
  override val spn: String = "G♯4"
  override val absPitch: Int = 48
}
object Ab4 extends Note {
  override val spn: String = "A♭4"
  override val absPitch: Int = 48
}
object A4 extends Note {
  override val spn: String = "A4"
  override val absPitch: Int = 49
}
object As4 extends Note {
  override val spn: String = "A♯4"
  override val absPitch: Int = 50
}
object Bb4 extends Note {
  override val spn: String = "B♭4"
  override val absPitch: Int = 50
}
object B4 extends Note {
  override val spn: String = "B4"
  override val absPitch: Int = 51
}
object C5 extends Note {
  override val spn: String = "C5"
  override val absPitch: Int = 52
}
object Cs5 extends Note {
  override val spn: String = "C♯5"
  override val absPitch: Int = 53
}
object Db5 extends Note {
  override val spn: String = "D♭5"
  override val absPitch: Int = 53
}
object D5 extends Note {
  override val spn: String = "D5"
  override val absPitch: Int = 54
}
object Ds5 extends Note {
  override val spn: String = "D♯5"
  override val absPitch: Int = 55
}
object Eb5 extends Note {
  override val spn: String = "E♭5"
  override val absPitch: Int = 55
}
object E5 extends Note {
  override val spn: String = "E5"
  override val absPitch: Int = 56
}
object F5 extends Note {
  override val spn: String = "F5"
  override val absPitch: Int = 57
}
object Fs5 extends Note {
  override val spn: String = "F♯5"
  override val absPitch: Int = 58
}
object Gb5 extends Note {
  override val spn: String = "G♭5"
  override val absPitch: Int = 58
}
object G5 extends Note {
  override val spn: String = "G5"
  override val absPitch: Int = 59
}
object Gs5 extends Note {
  override val spn: String = "G♯5"
  override val absPitch: Int = 60
}
object Ab5 extends Note {
  override val spn: String = "A♭5"
  override val absPitch: Int = 60
}
object A5 extends Note {
  override val spn: String = "A5"
  override val absPitch: Int = 61
}
object As5 extends Note {
  override val spn: String = "A♯5"
  override val absPitch: Int = 62
}
object Bb5 extends Note {
  override val spn: String = "B♭5"
  override val absPitch: Int = 62
}
object B5 extends Note {
  override val spn: String = "B5"
  override val absPitch: Int = 63
}
object C6 extends Note {
  override val spn: String = "C6"
  override val absPitch: Int = 64
}
object Cs6 extends Note {
  override val spn: String = "C♯6"
  override val absPitch: Int = 65
}
object Db6 extends Note {
  override val spn: String = "D♭6"
  override val absPitch: Int = 65
}
object D6 extends Note {
  override val spn: String = "D6"
  override val absPitch: Int = 66
}
object Ds6 extends Note {
  override val spn: String = "D♯6"
  override val absPitch: Int = 67
}
object Eb6 extends Note {
  override val spn: String = "E♭6"
  override val absPitch: Int = 67
}
object E6 extends Note {
  override val spn: String = "E6"
  override val absPitch: Int = 68
}
object F6 extends Note {
  override val spn: String = "F6"
  override val absPitch: Int = 69
}
object Fs6 extends Note {
  override val spn: String = "F♯6"
  override val absPitch: Int = 70
}
object Gb6 extends Note {
  override val spn: String = "G♭6"
  override val absPitch: Int = 70
}
object G6 extends Note {
  override val spn: String = "G6"
  override val absPitch: Int = 71
}
object Gs6 extends Note {
  override val spn: String = "G♯6"
  override val absPitch: Int = 72
}
object Ab6 extends Note {
  override val spn: String = "A♭6"
  override val absPitch: Int = 72
}
object A6 extends Note {
  override val spn: String = "A6"
  override val absPitch: Int = 73
}
object As6 extends Note {
  override val spn: String = "A♯6"
  override val absPitch: Int = 74
}
object Bb6 extends Note {
  override val spn: String = "B♭6"
  override val absPitch: Int = 74
}
object B6 extends Note {
  override val spn: String = "B6"
  override val absPitch: Int = 75
}
object C7 extends Note {
  override val spn: String = "C7"
  override val absPitch: Int = 76
}
object Cs7 extends Note {
  override val spn: String = "C♯7"
  override val absPitch: Int = 77
}
object Db7 extends Note {
  override val spn: String = "D♭7"
  override val absPitch: Int = 77
}
object D7 extends Note {
  override val spn: String = "D7"
  override val absPitch: Int = 78
}
object Ds7 extends Note {
  override val spn: String = "D♯7"
  override val absPitch: Int = 79
}
object Eb7 extends Note {
  override val spn: String = "E♭7"
  override val absPitch: Int = 79
}
object E7 extends Note {
  override val spn: String = "E7"
  override val absPitch: Int = 80
}
object F7 extends Note {
  override val spn: String = "F7"
  override val absPitch: Int = 81
}
object Fs7 extends Note {
  override val spn: String = "F♯7"
  override val absPitch: Int = 82
}
object Gb7 extends Note {
  override val spn: String = "G♭7"
  override val absPitch: Int = 82
}
object G7 extends Note {
  override val spn: String = "G7"
  override val absPitch: Int = 83
}
object Gs7 extends Note {
  override val spn: String = "G♯7"
  override val absPitch: Int = 84
}
object Ab7 extends Note {
  override val spn: String = "A♭7"
  override val absPitch: Int = 84
}
object A7 extends Note {
  override val spn: String = "A7"
  override val absPitch: Int = 85
}
object As7 extends Note {
  override val spn: String = "A♯7"
  override val absPitch: Int = 86
}
object Bb7 extends Note {
  override val spn: String = "B♭7"
  override val absPitch: Int = 86
}
object B7 extends Note {
  override val spn: String = "B7"
  override val absPitch: Int = 87
}
object C8 extends Note {
  override val spn: String = "C8"
  override val absPitch: Int = 88
}