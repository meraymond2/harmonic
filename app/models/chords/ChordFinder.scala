package models.chords

import dao.IntervalDb._
import models.chords.ChordClasses._
import models.intervals.HarmonicInterval
import models.notes.Note

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by michael on 19/12/16.
  */

object ChordFinder {

  /***
    * @param notes There can be any number of notes, but 1-2 probably won't return anything. For additional
    *              notes, since it reduces compound intervals to simple intervals, if they're valid
    *              they'll just end up as repetitions, which are ignored as it uses Sets. So it can
    *              have as many valid notes as one wants.
    * @return
    */
  def findChord(notes: Note*): Future[Option[Chord]] = Future {
    val sorted = notes.sortBy(_.absPitch)
    val bass = sorted.head
    val intervals = sorted.tail.map(note => bass.diff(note)).toSet
    intervals match {
        // Triads
      case _ if isMinorTriad(intervals) => Some(Chord(bass.pitchClass, minorTriad))
      case _ if isMajorTriad(intervals) => Some(Chord(bass.pitchClass, majorTriad))
      case _ if isDimTriad(intervals) => Some(Chord(bass.pitchClass, diminishedTriad))
        // Inverted Triads
      case _ if isMinSixChord(intervals) => Some(Chord((bass ↑ maj6th).pitchClass, minSixChord))
      case _ if isMajSixChord(intervals) => Some(Chord((bass ↑ min6th).pitchClass, majSixChord))
      case _ if isDimSixChord(intervals) => Some(Chord((bass ↑ maj6th).pitchClass, dimSixChord))
        // Second Inverted Triads
      case _ if isMinSixFourChord(intervals) => Some(Chord((bass ↑ per4th).pitchClass, minSixFour))
      case _ if isMajSixFourChord(intervals) => Some(Chord((bass ↑ per4th).pitchClass, majSixFour))
        // Sevenths
      case _ if isMinorSeventh(intervals) => Some(Chord(bass.pitchClass, minorSeventh))
      case _ if isMinorMajorSeventh(intervals) => Some(Chord(bass.pitchClass, minorMajorSeventh))
      case _ if isDominantSeventh(intervals) => Some(Chord(bass.pitchClass, dominantSeventh))
      case _ if isMajorSeventh(intervals) => Some(Chord(bass.pitchClass, majorSeventh))
      case _ if isDiminishedSeventh(intervals) => Some(Chord(bass.pitchClass, diminishedSeventh))
      case _ if isHalfDiminishedSeventh(intervals) => Some(Chord(bass.pitchClass, halfDimSeventh))
        // Inverted Sevenths
      case _ if isMinorSeventhFirstInv(intervals) => Some(Chord((bass ↑ maj6th).pitchClass, minorSeventhFirstInv))
      case _ if isMinorMajorSeventhFirstInv(intervals) => Some(Chord((bass ↑ maj6th).pitchClass, minorMajorSeventhFirstInv))
      case _ if isDominantSeventhFirstInv(intervals) => Some(Chord((bass ↑ min6th).pitchClass, dominantSeventhFirstInv))
      case _ if isMajorSeventhFirstInv(intervals) => Some(Chord((bass ↑ min6th).pitchClass, majorSeventhFirstInv))
      case _ if isDiminishedSeventhFirstInv(intervals) => Some(Chord((bass ↑ maj6th).pitchClass, diminishedSeventhFirstInv))
      case _ if isHalfDimSeventhFirstInv(intervals) => Some(Chord((bass ↑ maj6th).pitchClass, halfDimSeventhFirstInv))
        // Second Inverted Sevenths
      case _ if isMinorSeventhSecondInv(intervals) => Some(Chord((bass ↑ per4th).pitchClass, minorSeventhSecondInv))
      case _ if isMinorMajorSeventhSecondInv(intervals) => Some(Chord((bass ↑ per4th).pitchClass, minorMajorSeventhSecondInv))
      case _ if isDominantSeventhSecondInv(intervals) => Some(Chord((bass ↑ per4th).pitchClass, dominantSeventhSecondInv))
      case _ if isMajorSeventhSecondInv(intervals) => Some(Chord((bass ↑ per4th).pitchClass, majorSeventhSecondInv))
      case _ if isDiminishedSeventhSecondInv(intervals) => Some(Chord((bass ↑ aug4th).pitchClass, diminishedSeventhSecondInv))
      case _ if isHalfDimSeventhSecondInv(intervals) => Some(Chord((bass ↑ aug4th).pitchClass, halfDimSeventhSecondInv))
        // Third Inverted Sevenths
      case _ if isMinorSeventhThirdInv(intervals) => Some(Chord((bass ↑ maj2nd).pitchClass, minorSeventhThirdInv))
      case _ if isMinorMajorSeventhThirdInv(intervals) => Some(Chord((bass ↑ min2nd).pitchClass, minorMajorSeventhThirdInv))
      case _ if isDominantSeventhThirdInv(intervals) => Some(Chord((bass ↑ maj2nd).pitchClass, dominantSeventhThirdInv))
      case _ if isMajorSeventhThirdInv(intervals) => Some(Chord((bass ↑ min2nd).pitchClass, majorSeventhThirdInv))
      case _ if isDiminishedSeventhThirdInv(intervals) => Some(Chord((bass ↑ aug2nd).pitchClass, diminishedSeventhThirdInv))
      case _ if isHalfDimSeventhThirdInv(intervals) => Some(Chord((bass ↑ maj2nd).pitchClass, halfDimSeventhThirdInv))
      case _ => None
    }
  }


  // Minor Triad and its Inversions
  /***
    * A minor triad must have a m3.
    * It should also have either a P5 or a P8. A normal four-tone chord
    * should have one of each, but it's possible to omit the P5 (according
    * to Prout) or P8 (if the P5 is doubled), and a three-tone chord would
    * only have one of them.
    * It must not have any other intervals.
    * @param toCheck The set of intervals to be checked.
    * @return whether the intervals can be a minor triad.
    */
  private def isMinorTriad(toCheck: Set[HarmonicInterval]): Boolean = {
    val minTriad: Set[HarmonicInterval] = Set(min3rd, per5th, octave)
    val missingIntervals = minTriad.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      (missingIntervals == Set(per5th)) ||
      (missingIntervals == Set(octave))
    val doesNotContainOtherIntervals = toCheck.diff(minTriad).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * A minor six chord must have a M6 over the bass, which will be the root note, as well as
    * the fifth of the chord (so says Prout), which will be a M3 over the bass.
    * For now, I will exclude the octave, as most academics forbid doubling the chord-third, but if
    * I find any examples to the contrary, I'll amend this.
    * It must not have any other intervals.
    * Because it can't omit any possible intervals, we just check that the set matches exactly.
    * @param toCheck The set of intervals to be checked.
    * @return whether the intervals can be the first inversion of a minor triad.
    */
  private def isMinSixChord(toCheck: Set[HarmonicInterval]): Boolean = {
    val minSixChord: Set[HarmonicInterval] = Set(maj3rd, maj6th)
    minSixChord == toCheck
  }

  /***
    * The second inversion of a minor triad must have a P4 (root note) and a m6 (chord third)
    * over the bass. Neither can be omitted.
    * It will usually also have an octave (doubled chord fifth), but either other note may
    * be doubled instead.
    * It must not have any other intervals.
    * @param toCheck The set of intervals to be checked.
    * @return whether the intervals can be the second inversion of a minor triad.
    */
  private def isMinSixFourChord(toCheck: Set[HarmonicInterval]): Boolean = {
    val minSixFourChord: Set[HarmonicInterval] = Set(per4th, min6th, octave)
    val missingIntervals = minSixFourChord.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(octave)
    val doesNotContainOtherIntervals = toCheck.diff(minSixFourChord).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  // Major Triad and its Inversions

  /***
    * A major triad must have a M3.
    * It should also have either a P5 or a P8. A normal four-tone chord
    * should have one of each, but it's possible to omit the P5 (according
    * to Prout) or P8 (if the P5 is doubled), and a three-tone chord would
    * only have one of them.
    * It must not have any other intervals.
    * @param toCheck The set of intervals to be checked.
    * @return whether the intervals can be a major triad.
    */
  private def isMajorTriad(toCheck: Set[HarmonicInterval]): Boolean = {
    val majTriad: Set[HarmonicInterval] = Set(maj3rd, per5th, octave)
    val missingIntervals = majTriad.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      (missingIntervals == Set(per5th)) ||
      (missingIntervals == Set(octave))
    val doesNotContainOtherIntervals = toCheck.diff(majTriad).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * A major six chord must have a m6 over the bass, which will be the root note, as well as
    * the fifth of the chord (so says Prout), which will be a m3 over the bass.
    * For now, I will exclude the octave, as most academics forbid doubling the chord-third, but if
    * I find any examples to the contrary, I'll amend this.
    * It must not have any other intervals.
    * Because it can't omit any possible intervals, we just check that the set matches exactly.
    * @param toCheck The set of intervals to be checked.
    * @return whether the intervals can be the first inversion of a major triad.
    */
  private def isMajSixChord(toCheck: Set[HarmonicInterval]): Boolean = {
    val majSixChord: Set[HarmonicInterval] = Set(min3rd, min6th)
    majSixChord == toCheck
  }

  /***
    * The second inversion of a major triad must have a P4 (root note) and a M6 (chord third)
    * over the bass. Neither can be omitted.
    * It will usually also have an octave (doubled chord fifth), but either other note may
    * be doubled instead.
    * It must not have any other intervals.
    * @param toCheck The set of intervals to be checked.
    * @return whether the intervals can be the second inversion of a major triad.
    */
  private def isMajSixFourChord(toCheck: Set[HarmonicInterval]): Boolean = {
    val majSixFourChord: Set[HarmonicInterval] = Set(per4th, maj6th, octave)
    val missingIntervals = majSixFourChord.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
        missingIntervals == Set(octave)
    val doesNotContainOtherIntervals = toCheck.diff(majSixFourChord).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  // Diminished Triad and its Inversions

  /***
    * The root-position of the diminished triad is very rare, so there isn't as much
    * written about it. If the root is the leading tone, it should not be doubled (so
    * no octave above the bass), but in minor keys the supertonic chord is also
    * diminished, and I don't know if you could double that.
    * For now, I'll exclude the octave.
    * I'm not sure about the fifth. If you omitted it, you'd just have a minor third,
    * which could be mistaken for a minor triad (though a minor triad without the fifth
    * would almost certainly have a doubled root). Unless I find evidence to the
    * contrary, I'm going to assume that a diminished triad will always have the fifth.
    * It must not have any other intervals.
    * @param toCheck The set of intervals to be checked.
    * @return whether the intervals can be a diminished triad.
    */
  private def isDimTriad(toCheck: Set[HarmonicInterval]): Boolean = {
    val dimTriad: Set[HarmonicInterval] = Set(min3rd, dim5th)
    dimTriad == toCheck
  }

  /***
    * This is the most common form of the diminished triad.
    * It will need a M6, which will be the root.
    * It will presumably need a m3 too, which will be the chord fifth.
    * @param toCheck
    * @return
    */
  private def isDimSixChord(toCheck: Set[HarmonicInterval]): Boolean = {
    val dimSixChord: Set[HarmonicInterval] = Set(min3rd, maj6th)
    dimSixChord == toCheck
  }

  /***
    * I do not think the second inversion of the diminished triad is every likely
    * to come up. If it does, I'll add it.
    */


  // Augmented Triad
  /***
    * Until I come across one, I'm going to leave this one, because I can't imagine
    * anybody using one in baroque/classical works.
    */



  // *Seventh Chords*
  /***
    * As far as I remember, for all seventh chords, the fifth may be omitted, but
    * not the third.
    * In normal four-part harmony, you probably wouldn't double any notes, but you
    * could, especially if you left out the fifth. I will say (for now) that you
    * can either leave out the fifth or the octave, but not both.
    *
    * Of all of the seventh chords, the dominant is the most common, but you do
    * occasionally get the other ones, so I will do all of the ones based on the
    * minor, major and diminished triads. And they each have three inversions,
    * but the rules for all of them are the same, it's just that the intervals
    * differ slightly.
    */

  /***
    * A minor triad plus a minor seventh.
    */
  private def isMinorSeventh(toCheck: Set[HarmonicInterval]): Boolean = {
    val minorSeventh = Set(min3rd, per5th, min7th, octave)
    val missingIntervals = minorSeventh.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(octave) // 3, 5, 7
      missingIntervals == Set(per5th) // 3, 7, 8 (doubled root)
    val doesNotContainOtherIntervals = toCheck.diff(minorSeventh).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * A minor triad plus a major seventh.
    */
  private def isMinorMajorSeventh(toCheck: Set[HarmonicInterval]): Boolean = {
    val minorMajorSeventh = Set(min3rd, per5th, maj7th, octave)
    val missingIntervals = minorMajorSeventh.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(octave) // 3, 5, 7
      missingIntervals == Set(per5th) // 3, 7, 8 (doubled root)
    val doesNotContainOtherIntervals = toCheck.diff(minorMajorSeventh).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * A major triad plus a minor seventh.
    */
  private def isDominantSeventh(toCheck: Set[HarmonicInterval]): Boolean = {
    val dominantSeventh = Set(maj3rd, per5th, min7th, octave)
    val missingIntervals = dominantSeventh.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(octave) // 3, 5, 7
      missingIntervals == Set(per5th) // 3, 7, 8 (doubled root)
    val doesNotContainOtherIntervals = toCheck.diff(dominantSeventh).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * A major triad plus a major seventh.
    */
  private def isMajorSeventh(toCheck: Set[HarmonicInterval]): Boolean = {
    val majorSeventh = Set(maj3rd, per5th, maj7th, octave)
    val missingIntervals = majorSeventh.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(octave) // 3, 5, 7
      missingIntervals == Set(per5th) // 3, 7, 8 (doubled root)
    val doesNotContainOtherIntervals = toCheck.diff(majorSeventh).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * A diminished triad plus a diminished seventh (minor third above the fifth).
    */
  private def isDiminishedSeventh(toCheck: Set[HarmonicInterval]): Boolean = {
    val diminishedSeventh = Set(min3rd, dim5th, dim7th, octave)
    val missingIntervals = diminishedSeventh.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(octave) // 3, 5, 7
      missingIntervals == Set(dim5th) // 3, 7, 8 (doubled root)
    val doesNotContainOtherIntervals = toCheck.diff(diminishedSeventh).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * A diminished triad plus a minor seventh (major third above the fifth).
    */
  private def isHalfDiminishedSeventh(toCheck: Set[HarmonicInterval]): Boolean = {
    val halfDiminishedSeventh = Set(min3rd, dim5th, min7th, octave)
    val missingIntervals = halfDiminishedSeventh.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(octave) // 3, 5, 7
      missingIntervals == Set(dim5th) // 3, 7, 8 (doubled root)
    val doesNotContainOtherIntervals = toCheck.diff(halfDiminishedSeventh).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * For the first inversions, I don't think you would normally leave out the
    * chord-fifth, which would be a third over the bass, but it might be possible.
    * I don't think you would ever double the bass, so I'm not including the
    * octave for now (though this might not apply to thicker keyboard chords).
    * In figured bass, they're 6-5 chords, or 6-5-3 (where 3 is the chord-fifth).
    */

  private def isMinorSeventhFirstInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val minorSeventhFirstInv = Set(maj3rd, per5th, maj6th)
    val missingIntervals = minorSeventhFirstInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(maj3rd) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(minorSeventhFirstInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isMinorMajorSeventhFirstInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val minorMajorSeventhFirstInv = Set(maj3rd, aug5th, maj6th)
    val missingIntervals = minorMajorSeventhFirstInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(maj3rd) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(minorMajorSeventhFirstInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isDominantSeventhFirstInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val dominantSeventhFirstInv = Set(min3rd, dim5th, min6th)
    val missingIntervals = dominantSeventhFirstInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(min3rd) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(dominantSeventhFirstInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isMajorSeventhFirstInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val majorSeventhFirstInv = Set(min3rd, per5th, min6th)
    val missingIntervals = majorSeventhFirstInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(min3rd) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(majorSeventhFirstInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isDiminishedSeventhFirstInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val diminishedSeventhFirstInv = Set(min3rd, dim5th, maj6th)
    val missingIntervals = diminishedSeventhFirstInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(min3rd) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(diminishedSeventhFirstInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isHalfDimSeventhFirstInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val halfDimSeventhFirstInv = Set(min3rd, per5th, maj6th)
    val missingIntervals = halfDimSeventhFirstInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(min3rd) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(halfDimSeventhFirstInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * Since with second inversions the fifth is in the bass, nothing may be omitted.
    * For now, I will still disallow the octave, assuming that nothing is doubled (though
    * I don't know what would happen with more than 4 voices).
    * In figured bass these are 4-3 chords, short for 6-4-3.
    */

  private def isMinorSeventhSecondInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val minorSeventhSecondInv = Set(min3rd, per4th, min6th)
    minorSeventhSecondInv == toCheck
  }
  private def isMinorMajorSeventhSecondInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val minorMajorSeventhSecondInv = Set(maj3rd, per4th, min6th)
    minorMajorSeventhSecondInv == toCheck
  }
  private def isDominantSeventhSecondInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val dominantSeventhSecondInv = Set(min3rd, per4th, maj6th)
    dominantSeventhSecondInv == toCheck
  }
  private def isMajorSeventhSecondInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val majorSeventhSecondInv = Set(maj3rd, per4th, maj6th)
    majorSeventhSecondInv == toCheck
  }
  private def isDiminishedSeventhSecondInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val diminishedSeventhSecondInv = Set(min3rd, aug4th, maj6th)
    diminishedSeventhSecondInv == toCheck
  }
  private def isHalfDimSeventhSecondInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val halfDimSeventhSecondInv = Set(maj3rd, aug4th, maj6th)
    halfDimSeventhSecondInv == toCheck
  }

  /***
    * In figured bass, third inverted seventh chords can be written as just 2, 4-2
    * or 6-4-2. The 6 is the chord fifth, and may be omitted I think.
    */

  private def isMinorSeventhThirdInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val minorSeventhThirdInv = Set(maj2nd, per4th, maj6th)
    val missingIntervals = minorSeventhThirdInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(maj6th) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(minorSeventhThirdInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isMinorMajorSeventhThirdInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val minorMajorSeventhThirdInv = Set(min2nd, dim4th, min6th)
    val missingIntervals = minorMajorSeventhThirdInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(min6th) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(minorMajorSeventhThirdInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isDominantSeventhThirdInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val dominantSeventhThirdInv = Set(maj2nd, aug4th, maj6th)
    val missingIntervals = dominantSeventhThirdInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(maj6th) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(dominantSeventhThirdInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isMajorSeventhThirdInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val majorSeventhThirdInv = Set(min2nd, per4th, min6th)
    val missingIntervals = majorSeventhThirdInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(min6th) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(majorSeventhThirdInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isDiminishedSeventhThirdInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val diminishedSeventhThirdInv = Set(aug2nd, aug4th, maj6th)
    val missingIntervals = diminishedSeventhThirdInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(maj6th) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(diminishedSeventhThirdInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  private def isHalfDimSeventhThirdInv(toCheck: Set[HarmonicInterval]): Boolean = {
    val halfDimSeventhThirdInv = Set(maj2nd, per4th, min6th)
    val missingIntervals = halfDimSeventhThirdInv.diff(toCheck)
    val hasNecessaryIntervals =
      missingIntervals.isEmpty ||
      missingIntervals == Set(min6th) // omitting chord fifth
    val doesNotContainOtherIntervals = toCheck.diff(halfDimSeventhThirdInv).isEmpty
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

}
