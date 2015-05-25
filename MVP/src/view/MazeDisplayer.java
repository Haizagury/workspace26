package view;

import java.io.PrintStream;

import algorithms.mazeGenerators.Maze;

public interface MazeDisplayer {
	
	public void mazeDisplayer(Maze m , PrintStream out);

}
