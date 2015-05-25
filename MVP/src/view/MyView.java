package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;

import presenter.Presenter.Command;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;


public class MyView extends Observable implements View {

	BufferedReader in;
	PrintStream out;
	NewCli cli;
	Queue<Command> commandQueue;
	

	HashMap<String, Command> userCommands = new HashMap<String, Command>();

	public MyView(BufferedReader in, PrintStream out) {
	
		super();
		
		commandQueue = new LinkedList<Command>();
		
		this.in = new BufferedReader(new InputStreamReader(System.in));
		this.out = System.out;
	}
	


	@Override
	public void start() {
		System.out.println("start");
		setChanged();
		notifyObservers("start");
		Thread t = new Thread(new CLIRunnable(cli) );
		t.start();
	}

	@Override
	public void setCommands(String commandName, Command command) {
		userCommands.put(commandName, command);
		System.out.println("setCommand" + commandName);
		cli = new NewCli(userCommands, this, in, out);
	}

	@Override
	public Command getUserCommand() {
		System.out.println("getUserCommand");
		//Command userCommand = userCommands.get("test");
		
		return commandQueue.poll();
	}

	@Override
	public void displayMaze(Maze m) {
		System.out.println("displayMaze");
		
		MyMazeDisplayer disMaze = new MyMazeDisplayer();
		disMaze.mazeDisplayer(m, out);

	}

	@Override
	public void displaySolution(Solution s) {
		System.out.println("displaySolution");
		
		MySolutionDisplayer disSol = new MySolutionDisplayer();
		disSol.solutionDisplayer(s, out);
		
	}

	public void Notify(String arg){
		setChanged();
		notifyObservers(arg);
	}
	
}
