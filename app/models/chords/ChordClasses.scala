package models.chords

import play.api.libs.json.Format
import utils.EnumUtils

/**
  * Created by michael on 19/12/16.
  */

object ChordClasses extends Enumeration {
  type ChordClass = ChordClasses.Value

  val minorTriad,
  majorTriad,
  diminishedTriad,
  augmentedTriad,
  minSixChord,
  majSixChord,
  dimSixChord,
  minSixFour,
  majSixFour,
  minorSeventh,
  minorMajorSeventh,
  dominantSeventh,
  majorSeventh,
  diminishedSeventh,
  halfDimSeventh,
  minorSeventhFirstInv,
  minorMajorSeventhFirstInv,
  dominantSeventhFirstInv,
  majorSeventhFirstInv,
  diminishedSeventhFirstInv,
  halfDimSeventhFirstInv,
  minorSeventhSecondInv,
  minorMajorSeventhSecondInv,
  dominantSeventhSecondInv,
  majorSeventhSecondInv,
  diminishedSeventhSecondInv,
  halfDimSeventhSecondInv,
  minorSeventhThirdInv,
  minorMajorSeventhThirdInv,
  dominantSeventhThirdInv,
  majorSeventhThirdInv,
  diminishedSeventhThirdInv,
  halfDimSeventhThirdInv = Value

  class ChordClassValue(chordClass: ChordClass) {
    def andInversions: Set[ChordClass] = chordClass match {
      case `minorTriad` => Set(minorTriad, minSixChord, minSixFour)
      case `majorTriad` => Set(majorTriad, majSixChord, majSixFour)
      case `diminishedTriad` => Set(diminishedTriad, dimSixChord)
      case `minorSeventh` => Set(minorSeventh, minorSeventhFirstInv, minorSeventhSecondInv, minorSeventhThirdInv)
      case `minorMajorSeventh` => Set(minorMajorSeventh, minorMajorSeventhFirstInv, minorMajorSeventhSecondInv, minorMajorSeventhThirdInv)
      case `dominantSeventh` => Set(dominantSeventh, dominantSeventhFirstInv, dominantSeventhSecondInv, dominantSeventhThirdInv)
      case `majorSeventh` => Set(majorSeventh, majorSeventhFirstInv, majorSeventhSecondInv, majorSeventhThirdInv)
      case `diminishedSeventh` => Set(diminishedSeventh, diminishedSeventhFirstInv, diminishedSeventhSecondInv, diminishedSeventhThirdInv)
      case `halfDimSeventh` => Set(halfDimSeventh, halfDimSeventhFirstInv, halfDimSeventhSecondInv, halfDimSeventhThirdInv)
      case _ => Set.empty[ChordClass]
    }
  }

  implicit def value2ChordClassValue(chordClass: Value): ChordClassValue = new ChordClassValue(chordClass)
  implicit val chordClassFormat: Format[ChordClass] = EnumUtils.enumJsonFormat(ChordClasses)

}
