package model;

import java.util.HashMap;
import java.util.Observable;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class MyModel extends Observable implements Model{

	HashMap<String,Maze> nameMaze= new HashMap<>(); 
	HashMap<String,Solution> soltMaze= new HashMap<>();
	
	@Override
	public void generateMaze(String name, int cols, int rows) {
		System.out.println("generateMaze");
		setChanged();
		
		notifyObservers("generateMaze");
	}

	@Override
	public Maze getMaze(String name) {
		System.out.println("getMaze");
		return nameMaze.get(name);
	}

	@Override
	public void solveMaze(Maze m) {
		
		System.out.println("solveMaze");
		notifyObservers("solveMaze");
	}

	@Override
	public Solution getSolution(String name) {
		System.out.println("getSolution");
		return soltMaze.get(nameMaze.get(name));
	}

	@Override
	public void stop() {
		
		System.out.println("stop");
	}

}
