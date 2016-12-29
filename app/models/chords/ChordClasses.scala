package models.chords

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
      case triad if triad == minorTriad => Set(minorTriad, minSixChord, minSixFour)
      case triad if triad == majorTriad => Set(majorTriad, majSixChord, majSixFour)
      case triad if triad == diminishedTriad => Set(diminishedTriad, dimSixChord)
      case seventh if seventh == minorSeventh => Set(minorSeventh, minorSeventhFirstInv, minorSeventhSecondInv, minorSeventhThirdInv)
      case seventh if seventh == minorMajorSeventh => Set(minorMajorSeventh, minorMajorSeventhFirstInv, minorMajorSeventhSecondInv, minorMajorSeventhThirdInv)
      case seventh if seventh == dominantSeventh => Set(dominantSeventh, dominantSeventhFirstInv, dominantSeventhSecondInv, dominantSeventhThirdInv)
      case seventh if seventh == majorSeventh => Set(majorSeventh, majorSeventhFirstInv, majorSeventhSecondInv, majorSeventhThirdInv)
      case seventh if seventh == diminishedSeventh => Set(diminishedSeventh, diminishedSeventhFirstInv, diminishedSeventhSecondInv, diminishedSeventhThirdInv)
      case seventh if seventh == halfDimSeventh => Set(halfDimSeventh, halfDimSeventhFirstInv, halfDimSeventhSecondInv, halfDimSeventhThirdInv)
      case _ => Set.empty[ChordClass]
    }
  }

  implicit def value2ChordClassValue(chordClass: Value): ChordClassValue = new ChordClassValue(chordClass)

}
