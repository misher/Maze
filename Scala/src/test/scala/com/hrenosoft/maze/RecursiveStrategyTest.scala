package com.hrenosoft.maze

import org.scalatest.FlatSpec

/**
  * Created by misher on 11/5/15.
  */
class RecursiveStrategyTest extends FlatSpec {
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
  private val testMaze = new Maze(roadPnts, new Point(4, 1))
  private val traverseResult = Array(Point(1, 1), Point(1, 2), Point(2, 2), Point(3, 2), Point(4, 2), Point(4, 1))
  "Maze" should "be traversed Point(1, 1), Point(1, 2), Point(2, 2), Point(3, 2), Point(4, 2), Point(4, 1)" in {
    val strategy = new RecursiveTraversingStrategy()
    val wayPnts: Array[Point] = strategy.traverse(testMaze, Point(1, 1))
    assert(wayPnts.deep == traverseResult.deep)
  }
}
