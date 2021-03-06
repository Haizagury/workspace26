package view;

import presenter.Presenter.Command;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public interface View {

	void start();
	Command getUserCommand();
	void displayMaze(Maze m);
	void displaySolution(Solution s);
	void setCommands(String commandName, Command command);
}
