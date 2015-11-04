package com.hrenosoft.maze

import org.scalatest.FlatSpec

/**
  * Created by misher on 11/4/15.
  */
class MazeTest extends FlatSpec {
  private val roadPnts: Array[Point] = Array(
    Point(1, 1),
    Point(1, 2),
    Point(1, 4),
    Point(2, 2),
    Point(2, 4),
    Point(3, 2),
    Point(3, 3),
    Point(3, 4),
    Point(4, 1),
    Point(4, 2),
    Point(4, 4)
  )
  private val testMaze = new Maze(roadPnts, new Point(4, 4))

  "Maze" should "have top left corner at (1, 1)" in {
    assert(testMaze.topLeft.x == 1)
    assert(testMaze.topLeft.y == 1)
  }
  it should "have bottom right corner at (4, 4)" in {
    assert(testMaze.bottomRight.x == 4)
    assert(testMaze.bottomRight.y == 4)
  }
  it should "have road point at (2, 2)" in {
    assert(testMaze.isRoadPoint(Point(2, 2)))
  }
  it should "have target point at (4, 4)" in {
    assert(testMaze.isRoadPoint(Point(4, 4)))
  }
  it should "not have road point at (1, 3)" in {
    assert(!testMaze.isRoadPoint(Point(1, 3)))
  }

  it should "be able to produce map string it is presenting" in {
    assert(testMaze.toString() == "X  X\nXXXX\n  X \nXXX$")
  }
}
