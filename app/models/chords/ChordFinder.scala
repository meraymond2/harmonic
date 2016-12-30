package models.chords

import dao.IntervalDb._
import models.chords.ChordClasses._
import models.intervals.Interval
import models.notes.Note

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by michael on 19/12/16.
  */

object ChordFinder {

  /***
    * I've wrapped this in a future, because it might become time consuming to do all of those checks.
    * @param notes There can be any number of notes, but 1-2 probably won't return anything. For additional
    *              notes, since it reduces compound intervals to simple intervals, if they're valid
    *              they'll just end up as repetitions, which are ignored as it uses Sets. So it can
    *              have as many valid notes as one wants.
    * @return
    */
  def findChord(notes: Note*): Future[Option[Chord]] = Future {
    val sorted = notes.sortBy(_.absPitch)
    val bass = sorted.head
    val intervals = sorted.tail.map(note => bass.diff(note).simpleInt).toSet
    intervals match {
      case _ if isMinorTriad(intervals) => Some(Chord(bass.pitchClass, minorTriad))
      case _ if isMajorTriad(intervals) => Some(Chord(bass.pitchClass, majorTriad))
      case _ if isMinSixChord(intervals) => Some(Chord((bass ↑ maj6th).pitchClass, minSixChord))
      case _ if isMajSixChord(intervals) => Some(Chord((bass ↑ min6th).pitchClass, majSixChord))
      case _ if isMinSixFourChord(intervals) => Some(Chord((bass ↑ per4th).pitchClass, minSixFour))
      case _ if isMajSixFourChord(intervals) => Some(Chord((bass ↑ per4th).pitchClass, majSixFour))
      case _ if isDimTriad(intervals) => Some(Chord(bass.pitchClass, diminishedTriad))
      case _ if isDimSixChord(intervals) => Some(Chord((bass ↑ maj6th).pitchClass, dimSixChord))
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
  private def isMinorTriad(toCheck: Set[Interval]): Boolean = {
    val minTriad: Set[Interval] = Set(min3rd, per5th, octave)
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
  private def isMinSixChord(toCheck: Set[Interval]): Boolean = {
    val minSixChord: Set[Interval] = Set(maj3rd, maj6th)
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
  private def isMinSixFourChord(toCheck: Set[Interval]): Boolean = {
    val minSixFourChord: Set[Interval] = Set(per4th, min6th, octave)
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
  private def isMajorTriad(toCheck: Set[Interval]): Boolean = {
    val majTriad: Set[Interval] = Set(maj3rd, per5th, octave)
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
  private def isMajSixChord(toCheck: Set[Interval]): Boolean = {
    val majSixChord: Set[Interval] = Set(min3rd, min6th)
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
  private def isMajSixFourChord(toCheck: Set[Interval]): Boolean = {
    val majSixFourChord: Set[Interval] = Set(per4th, maj6th, octave)
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
  private def isDimTriad(toCheck: Set[Interval]): Boolean = {
    val dimTriad: Set[Interval] = Set(min3rd, dim5th)
    dimTriad == toCheck
  }

  /***
    * This is the most common form of the diminished triad.
    * It will need a M6, which will be the root.
    * It will presumably need a m3 too, which will be the chord fifth.
    * @param toCheck
    * @return
    */
  private def isDimSixChord(toCheck: Set[Interval]): Boolean = {
    val dimSixChord: Set[Interval] = Set(min3rd, maj6th)
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

}
