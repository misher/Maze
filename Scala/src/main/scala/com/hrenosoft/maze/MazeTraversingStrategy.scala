package com.hrenosoft.maze

/**
  * Created by misher on 11/4/15.
  */
trait MazeTraversingStrategy {
  def traverse(maze: Maze, start: Point): Array[Point]
}
