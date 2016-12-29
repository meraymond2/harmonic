package dao

import models.chords.Chord
import models.keys.{Key, MajorKey, MinorKey}
import models.notes.PitchClasses

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by michael on 29/12/16.
  */

object KeyDb {

  lazy val keys: Set[Key] =
    PitchClasses.values.map(pitchClass => new MajorKey(pitchClass)) ++
    PitchClasses.values.map(pitchClass => new MinorKey(pitchClass))

  /***
    * For now I can't think of a faster way of doing this, so I've wrapped it in
    * a future.
    * @param chord The chord to check.
    * @return All of the keys that could contain that chord.
    */
  def findKeys(chord: Chord): Future[Set[Key]] = Future {
    keys.filter(_.contains(chord))
  }

}
