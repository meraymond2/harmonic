package models

import Letter._
import models.PitchClasses.PitchClass
import models.PitchClasses._


case class Tone(letter: Letter, pitchClass: PitchClass, spn: String, pitch: Int) {

  def up(interval: Interval): Option[Tone] = {
    Tone.tones.find( tone =>
      (tone.letter.index == this.letter.index + interval.letterGap)
        &&
      (tone.pitch == this.pitch + interval.halfSteps)
    )
  }

  def down(interval: Interval): Option[Tone] = {
    Tone.tones.find( tone =>
      (tone.letter.index == this.letter.index - interval.letterGap)
        &&
      (tone.pitch == this.pitch - interval.halfSteps)
    )
  }

}

object Tone {

  val A0: Tone  = Tone(a, A,  "A0",  1)
  val As0: Tone = Tone(a, As, "A♯0", 2)
  val Bb0: Tone = Tone(b, Bb, "B♭0", 2)
  val B0: Tone  = Tone(b, B,  "B0",  3)

  val C1: Tone  = Tone(c, C,  "C1",  4)
  val Cs1: Tone = Tone(c, Cs, "C♯1", 5)
  val Db1: Tone = Tone(d, Db, "D♭1", 5)
  val D1: Tone  = Tone(d, D,  "D1",  6)
  val Ds1: Tone = Tone(d, Ds, "D♯1", 7)
  val Eb1: Tone = Tone(e, Eb, "E♭1", 7)
  val E1: Tone  = Tone(e, E,  "E1",  8)
  val F1: Tone  = Tone(f, F,  "F1",  9)
  val Fs1: Tone = Tone(f, Fs, "F♯1", 10)
  val Gb1: Tone = Tone(g, Gb, "G♭1", 10)
  val G1: Tone  = Tone(g, G,  "G1",  11)
  val Gs1: Tone = Tone(g, Gs, "G♯1", 12)
  val Ab1: Tone = Tone(a, Ab, "A♭1", 12)
  val A1: Tone  = Tone(a, A,  "A1",  13)
  val As1: Tone = Tone(a, As, "A♯1", 14)
  val Bb1: Tone = Tone(b, Bb, "B♭1", 14)
  val B1: Tone  = Tone(b, B,  "B1",  15)

  val C2: Tone  = Tone(c, C,  "C2",  16)
  val Cs2: Tone = Tone(c, Cs, "C♯2", 17)
  val Db2: Tone = Tone(d, Db, "D♭2", 17)
  val D2: Tone  = Tone(d, D,  "D2",  18)
  val Ds2: Tone = Tone(d, Ds, "D♯2", 19)
  val Eb2: Tone = Tone(e, Eb, "E♭2", 19)
  val E2: Tone  = Tone(e, E,  "E2",  20)
  val F2: Tone  = Tone(f, F,  "F2",  21)
  val Fs2: Tone = Tone(f, Fs, "F♯2", 22)
  val Gb2: Tone = Tone(g, Gb, "G♭2", 22)
  val G2: Tone  = Tone(g, G,  "G2",  23)
  val Gs2: Tone = Tone(g, Gs, "G♯2", 24)
  val Ab2: Tone = Tone(a, Ab, "A♭2", 24)
  val A2: Tone  = Tone(a, A,  "A2",  25)
  val As2: Tone = Tone(a, As, "A♯2", 26)
  val Bb2: Tone = Tone(b, Bb, "B♭2", 26)
  val B2: Tone  = Tone(b, B,  "B2",  27)

  val C3:  Tone = Tone(c, C,  "C3",  28)
  val Cs3: Tone = Tone(c, Cs, "C♯3", 29)
  val Db3: Tone = Tone(d, Db, "D♭3", 29)
  val D3:  Tone = Tone(d, D,  "D3",  30)
  val Ds3: Tone = Tone(d, Ds, "D♯3", 31)
  val Eb3: Tone = Tone(e, Eb, "E♭3", 31)
  val E3:  Tone = Tone(e, E,  "E3",  32)
  val F3:  Tone = Tone(f, F,  "F3",  33)
  val Fs3: Tone = Tone(f, Fs, "F♯3", 34)
  val Gb3: Tone = Tone(g, Gb, "G♭3", 34)
  val G3:  Tone = Tone(g, G,  "G3",  35)
  val Gs3: Tone = Tone(g, Gs, "G♯3", 36)
  val Ab3: Tone = Tone(a, Ab, "A♭3", 36)
  val A3:  Tone = Tone(a, A,  "A3",  37)
  val As3: Tone = Tone(a, As, "A♯3", 38)
  val Bb3: Tone = Tone(b, Bb, "B♭3", 38)
  val B3:  Tone = Tone(b, B,  "B3",  39)

  val C4:  Tone = Tone(c, C , "C4",  40)
  val Cs4: Tone = Tone(c, Cs, "C♯4", 41)
  val Db4: Tone = Tone(d, Db, "D♭4", 41)
  val D4:  Tone = Tone(d, D , "D4",  42)
  val Ds4: Tone = Tone(d, Ds, "D♯4", 43)
  val Eb4: Tone = Tone(e, Eb, "E♭4", 43)
  val E4:  Tone = Tone(e, E , "E4",  44)
  val F4:  Tone = Tone(f, F , "F4",  45)
  val Fs4: Tone = Tone(f, Fs, "F♯4", 46)
  val Gb4: Tone = Tone(g, Gb, "G♭4", 46)
  val G4:  Tone = Tone(g, G , "G4",  47)
  val Gs4: Tone = Tone(g, Gs, "G♯4", 48)
  val Ab4: Tone = Tone(a, Ab, "A♭4", 48)
  val A4:  Tone = Tone(a, A , "A4",  49)
  val As4: Tone = Tone(a, As, "A♯4", 50)
  val Bb4: Tone = Tone(b, Bb, "B♭4", 50)
  val B4:  Tone = Tone(b, B , "B4",  51)

  val C5:  Tone = Tone(c, C,  "C5",  52)
  val Cs5: Tone = Tone(c, Cs, "C♯5", 53)
  val Db5: Tone = Tone(d, Db, "D♭5", 53)
  val D5:  Tone = Tone(d, D,  "D5",  54)
  val Ds5: Tone = Tone(d, Ds, "D♯5", 55)
  val Eb5: Tone = Tone(e, Eb, "E♭5", 55)
  val E5:  Tone = Tone(e, E,  "E5",  56)
  val F5:  Tone = Tone(f, F,  "F5",  57)
  val Fs5: Tone = Tone(f, Fs, "F♯5", 58)
  val Gb5: Tone = Tone(g, Gb, "G♭5", 58)
  val G5:  Tone = Tone(g, G,  "G5",  59)
  val Gs5: Tone = Tone(g, Gs, "G♯5", 60)
  val Ab5: Tone = Tone(a, Ab, "A♭5", 60)
  val A5:  Tone = Tone(a, A,  "A5",  61)
  val As5: Tone = Tone(a, As, "A♯5", 62)
  val Bb5: Tone = Tone(b, Bb, "B♭5", 62)
  val B5:  Tone = Tone(b, B,  "B5",  63)

  val C6:  Tone = Tone(c, C,  "C6",  64)
  val Cs6: Tone = Tone(c, Cs, "C♯6", 65)
  val Db6: Tone = Tone(d, Db, "D♭6", 65)
  val D6:  Tone = Tone(d, D,  "D6",  66)
  val Ds6: Tone = Tone(d, Ds, "D♯6", 67)
  val Eb6: Tone = Tone(e, Eb, "E♭6", 67)
  val E6:  Tone = Tone(e, E,  "E6",  68)
  val F6:  Tone = Tone(f, F,  "F6",  69)
  val Fs6: Tone = Tone(f, Fs, "F♯6", 70)
  val Gb6: Tone = Tone(g, Gb, "G♭6", 70)
  val G6:  Tone = Tone(g, G,  "G6",  71)
  val Gs6: Tone = Tone(g, Gs, "G♯6", 72)
  val Ab6: Tone = Tone(a, Ab, "A♭6", 72)
  val A6:  Tone = Tone(a, A,  "A6",  73)
  val As6: Tone = Tone(a, As, "A♯6", 74)
  val Bb6: Tone = Tone(b, Bb, "B♭6", 74)
  val B6:  Tone = Tone(b, B,  "B6",  75)

  val C7:  Tone = Tone(c, C,  "C7",  76)
  val Cs7: Tone = Tone(c, Cs, "C♯7", 77)
  val Db7: Tone = Tone(d, Db, "D♭7", 77)
  val D7:  Tone = Tone(d, D,  "D7",  78)
  val Ds7: Tone = Tone(d, Ds, "D♯7", 79)
  val Eb7: Tone = Tone(e, Eb, "E♭7", 79)
  val E7:  Tone = Tone(e, E,  "E7",  80)
  val F7:  Tone = Tone(f, F,  "F7",  81)
  val Fs7: Tone = Tone(f, Fs, "F♯7", 82)
  val Gb7: Tone = Tone(g, Gb, "G♭7", 82)
  val G7:  Tone = Tone(g, G,  "G7",  83)
  val Gs7: Tone = Tone(g, Gs, "G♯7", 84)
  val Ab7: Tone = Tone(a, Ab, "A♭7", 84)
  val A7:  Tone = Tone(a, A,  "A7",  85)
  val As7: Tone = Tone(a, As, "A♯7", 86)
  val Bb7: Tone = Tone(b, Bb, "B♭7", 86)
  val B7:  Tone = Tone(b, B,  "B7",  87)

  val C8:  Tone = Tone(c, C,  "C8",  88)

  val tones: Seq[Tone] = Seq(A0, As0, Bb0, B0, C1, Cs1, Db1, D1, Ds1, Eb1, E1, F1, Fs1, Gb1, G1, Gs1, Ab1, A1, As1, Bb1, B1, C2, Cs2, Db2, D2, Ds2, Eb2, E2, F2, Fs2, Gb2, G2, Gs2, Ab2, A2, As2, Bb2, B2, C3, Cs3, Db3, D3, Ds3, Eb3, E3, F3, Fs3, Gb3, G3, Gs3, Ab3, A3, As3, Bb3, B3, C4, Cs4, Db4, D4, Ds4, Eb4, E4, F4, Fs4, Gb4, G4, Gs4, Ab4, A4, As4, Bb4, B4, C5, Cs5, Db5, D5, Ds5, Eb5, E5, F5, Fs5, Gb5, G5, Gs5, Ab5, A5, As5, Bb5, B5, C6, Cs6, Db6, D6, Ds6, Eb6, E6, F6, Fs6, Gb6, G6, Gs6, Ab6, A6, As6, Bb6, B6, C7, Cs7, Db7, D7, Ds7, Eb7, E7, F7, Fs7, Gb7, G7, Gs7, Ab7, A7, As7, Bb7, B7, C8)

}
