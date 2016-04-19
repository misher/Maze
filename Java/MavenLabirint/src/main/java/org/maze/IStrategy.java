package org.maze;

public interface IStrategy {
	//TODO: today our findNewWay - tells whether route to the target exists. It is better to make it returning the route.
	boolean findNewWay(IMaze maze, IPoint sp);
}