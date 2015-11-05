package com.hrenosoft.maze

import scala.collection.mutable.ArrayBuffer

/**
  * Created by misher on 11/5/15.
  */
class RecursiveTraversingStrategy extends MazeTraversingStrategy {

  def find(maze: Maze, pnt: Point, visitedPnts: ArrayBuffer[Point]): Array[Point] = {
    visitedPnts+=pnt
    if (maze.isTargetPoint(pnt)) {
      Array(pnt)
    } else if (!maze.isRoadPoint(pnt)) {
      Array.empty[Point]
    } else {
      val nextDirections = Array(pnt.above, pnt.below, pnt.right, pnt.left).filter{!visitedPnts.contains(_)}
      for (nextDir <- nextDirections) {
        val findResult = find(maze, nextDir, visitedPnts)
        if (findResult.size > 0) {
          return Array(pnt)++findResult
        }
      }
      return Array.empty[Point]
    }
  }

  override def traverse(maze: Maze, start: Point): Array[Point] = {
     find(maze, start, ArrayBuffer.empty[Point])
  }
}
