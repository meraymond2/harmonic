package models.chords

import models.intervals._
import models.notes.Note

/**
  * Created by michael on 19/12/16.
  */

object ChordFinder {

  def findChord(notes: Note*): Option[Chord] = {
    val sorted = notes.sortBy(_.absPitch)
    val bass = sorted.head
    val intervals = sorted.tail.map(note => between(bass, note)).toSet
    intervals match {
      case _ if isMinorTriad(intervals) =>
        println("minor triad!")
        None
      case _ => None
    }
  }

  private def between(n: Note, n2: Note): Interval = octave

  /***
    * A minor triad must have a m3.
    * It should also have either a P5 or a P8. A normal four-tone chord
    * should have one of each, but it's possible to omit the P5 or P8,
    * and a three-tone chord would only have one of them.
    * It must not have any other intervals.
    * @param intervals The set of intervals to be checked.
    * @return whether the intervals can be a minor triad.
    */
  private def isMinorTriad(intervals: Set[Interval]): Boolean = {
    val minorTriadIntervals: Set[Interval] = Set(min3rd, per5th, octave)
    val containsNecessaryIntervals = (minorTriadIntervals.diff(intervals) == Set(per5th)) || (minorTriadIntervals.diff(intervals) == Set(octave))
    val doesntcontainOtherIntervals = intervals.diff(minorTriadIntervals) == Set.empty[Interval]
    containsNecessaryIntervals && doesntcontainOtherIntervals
  }

}
