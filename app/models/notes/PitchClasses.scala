package models.notes

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

}
