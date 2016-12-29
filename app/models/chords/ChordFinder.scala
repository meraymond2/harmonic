package models.chords

import dao.IntervalDb._
import models.chords.ChordClasses._
import models.intervals.Interval
import models.notes.Note

/**
  * Created by michael on 19/12/16.
  */

object ChordFinder {

  /***
    *
    * @param notes There can be any number of notes, but 1-2 probably won't return anything. For additional
    *              notes, since it reduces compound intervals to simple intervals, if they're valid
    *              they'll just end up as repetitions, which are ignored as it uses Sets. So it can
    *              have as many valid notes as one wants.
    * @return
    */
  def findChord(notes: Note*): Option[Chord] = {
    val sorted = notes.sortBy(_.absPitch)
    val bass = sorted.head
    val intervals = sorted.tail.map(note => bass.diff(note).simpleInt).toSet
    intervals match {
      case _ if isMinorTriad(intervals) => Some(Chord(bass.pitchClass, minorTriad))
      case _ if isMajorTriad(intervals) => Some(Chord(bass.pitchClass, majorTriad))
      case _ if isMinSixChord(intervals) => Some(Chord((bass ↑ maj6th).pitchClass, minSixChord))
      case _ if isMajSixChord(intervals) => Some(Chord((bass ↑ min6th).pitchClass, majSixChord))
      case _ => None
    }
  }

  /***
    * A minor triad must have a m3.
    * It should also have either a P5 or a P8. A normal four-tone chord
    * should have one of each, but it's possible to omit the P5 or P8,
    * and a three-tone chord would only have one of them.
    * It must not have any other intervals.
    * @param toCheck The set of intervals to be checked.
    * @return whether the intervals can be a minor triad.
    */
  private def isMinorTriad(toCheck: Set[Interval]): Boolean = {
    val minTriad: Set[Interval] = Set(min3rd, per5th, octave)
    val missingIntervals = minTriad.diff(toCheck)
    val hasNecessaryIntervals =
      (missingIntervals == Set.empty[Interval]) ||
      (missingIntervals == Set(per5th)) ||
      (missingIntervals == Set(octave))
    val doesNotContainOtherIntervals = toCheck.diff(minTriad) == Set.empty[Interval]
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * A major triad must have a M3.
    * It should also have either a P5 or a P8. A normal four-tone chord
    * should have one of each, but it's possible to omit the P5 or P8,
    * and a three-tone chord would only have one of them.
    * It must not have any other intervals.
    * @param toCheck The set of intervals to be checked.
    * @return whether the intervals can be a major triad.
    */
  private def isMajorTriad(toCheck: Set[Interval]): Boolean = {
    val majTriad: Set[Interval] = Set(maj3rd, per5th, octave)
    val missingIntervals = majTriad.diff(toCheck)
    val hasNecessaryIntervals =
      (missingIntervals == Set.empty[Interval]) ||
        (missingIntervals == Set(per5th)) ||
        (missingIntervals == Set(octave))
    val doesNotContainOtherIntervals = toCheck.diff(majTriad) == Set.empty[Interval]
    hasNecessaryIntervals && doesNotContainOtherIntervals
  }

  /***
    * A minor six chord must have a m6, which is the root.
    * Come back to this, I can't remember if it has to have the fifth or not. I know that the bass isn't normally
    * doubled, but I think it can be. Should check and come back.
    * @param toCheck
    * @return
    */
  private def isMinSixChord(toCheck: Set[Interval]): Boolean = {
    val minSixChord: Set[Interval] = Set(maj3rd, maj6th, octave)
    false
  }
  private def isMajSixChord(toCheck: Set[Interval]): Boolean = {
    val majSixChord: Set[Interval] = Set(min3rd, min6th, octave)
    false
  }

}
