package models.notes

import dao.NoteDb
import models.intervals.Interval
import play.api.libs.json.{Format, JsString, Json}
import utils.EnumUtils

/**
  * Created by michael on 19/12/16.
  */

object PitchClasses extends Enumeration {
  type PitchClass = PitchClasses.Value

  val C = Value("C")
  val Cs = Value("C♯")
  val Db = Value("D♭")
  val D = Value("D")
  val Ds = Value("D♯")
  val Eb = Value("E♭")
  val E = Value("E")
  val F = Value("F")
  val Fs = Value("F♯")
  val Gb = Value("G♭")
  val G = Value("G")
  val Gs = Value("G♯")
  val Ab = Value("A♭")
  val A = Value("A")
  val As = Value("A♯")
  val Bb = Value("B♭")
  val B = Value("B")

  class PitchClassValue(pitchClass: Value) {
    /***
      * This is ugly, having to convert a pitch-class to a Note and
      * back again, but for now I can't think of any way to apply an
      * interval based solely on the pitch class. For example, how would
      * it know that a perfect fifth above B would be F# and not F?
      * @param interval The interval to apply.
      * @return The pitch-class a given interval above the starting pitch-class.
      */
    def ↑(interval: Interval): PitchClass = (NoteDb.findFirst(this.pitchClass) ↑ interval).pitchClass
    def ↓(interval: Interval): PitchClass = (NoteDb.findLast(this.pitchClass) ↓ interval).pitchClass
  }

  implicit def value2PitchClassValue(pitchClass: Value): PitchClassValue = new PitchClassValue(pitchClass)
  implicit val pitchClassFormat: Format[PitchClass] = EnumUtils.enumJsonFormat(PitchClasses)

}
