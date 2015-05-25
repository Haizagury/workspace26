package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import presenter.Presenter.Command;

public class NewCli {
	
	private BufferedReader in;
	private PrintStream out;
	private MyView view;
	
	HashMap<String, Command> userCommands = new HashMap<String, Command>();

	public NewCli(HashMap<String, Command> userCommands, MyView view, BufferedReader in, PrintStream out) {
		this.view = view;
		//this.userCommands = userCommands;
		this.in = in;
		this.out = out;
		}

	public void start() 
	 	{ 
	 		out.print("Enter command: "); 
	 
	 
	 		try { 
	 			String line = in.readLine(); 
	 			 
	 			while (!line.equals("exit")) 
	 			{ 
	 				String[] sp = line.split(" ", 2); 
	 								 
	 				String commandName = sp[0]; 
	 				String arg = null; 
	 				if (sp.length > 1) 
	 					arg = sp[1]; 
	 				// Invoke the command 
	 				Command command = userCommands.get(commandName);
	 				if(command == null) 
	 					out.println("command not found "+commandName); 
	 				else 
	 					if(arg==null) 
	 						out.println("No argument has been entered"); 
	 					else{ 
	 						view.commandQueue.isEmpty();
	 						view.commandQueue.add(command);
	 					    view.notify();
	 						
	 					}
	 				 
	 				out.print("Enter command: "); 
	 				line = in.readLine(); 
	 			} 
	 			out.println("Goodbye");
	 			view.commandQueue.add(userCommands.get("exit"));
	 			view.notify();
	 						 
	 		} catch (IOException e) {			 
	 			System.out.println("can't read/write from/to in/out streams"); 
	 		} finally { 
	 			try { 
	 				in.close(); 
	 				out.close(); 
	 			} catch (IOException e) {				 
	 				System.out.println("can't close from in/out streams"); 
	 			}		 
	 		}	 
	 	} 

}
