package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

public class Presenter implements Observer {

	private Model model;
	private View view;

	public Presenter(View view, Model model) {
		this.model = model;
		this.view = view;
		UserCommand();
		view.setCommands("displayMaze", new displayMazeCommand());;
		
		

	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("presenter.update");

		if(arg != null && ((String)arg).equals("start"))
		      return; 
	    else{
		
		
		if (o == view) {
			Command command = view.getUserCommand();
			command.doCommand((String) arg);
			
		}
		if (o == model) {
			if(arg == null)
				return;
			else{
				
				String[] Name_params = ((String)arg).split(" ");
				if (Name_params[0].equals("generateMazeComplete")){
					view.displayMaze(model.getMaze(Name_params[1]));
				}
				if(Name_params[0].equals("solveMazeCompleted")){
					view.displaySolution(model.getSolution(Name_params[1]));
				}
				
		

		  }
		     
	    }		
	  }
	}

	HashMap<String, Command> commands = new HashMap<String , Command>();

	public interface Command {
		void doCommand(String arg);
	}

	public void UserCommand(){
		commands.put("generateMaze",new generateMazeCommand());
		commands.put("displayMaze",new displayMazeCommand());
		commands.put("solveMaze",new solveMazeCommand());
		commands.put("displaySolution",new displaySolutionCommand());
		commands.put("exit",new exitCommand());
	}
	
	public class generateMazeCommand implements Command {

		@Override
		public void doCommand(String arg) {
			String [] name_Arg = arg.split(" ");
			    if(name_Arg.length != 2)
			    	return;
			    String[] row_col = name_Arg[1].split(",");
			    if(row_col.length != 2)
			    	return;
			    int rows = Integer.parseInt(row_col[0]);
			    int cols = Integer.parseInt(row_col[1]);
			    model.generateMaze(name_Arg[0], cols, rows);
			//System.out.println("TestMVPCommand.doCommand");
		

		}


	}
		public class displayMazeCommand implements Command {

			@Override
			public void doCommand(String arg) {
				if(model.getMaze(arg) != null)
					view.displayMaze(model.getMaze(arg));
				else
					
				System.out.println("Mazze not found");
				

			}
		}	
     	public class solveMazeCommand implements Command {

				@Override
				public void doCommand(String arg) {
					model.solveMaze(model.getMaze(arg));
					System.out.println("meze solved");
					

				}	

	}

     	public class displaySolutionCommand implements Command {

    		@Override
    		public void doCommand(String arg) {
    			if(model.getSolution(arg) != null)
    				view.displaySolution(model.getSolution(arg));
    			else
    			System.out.println("Solution not found");
    			

    		}
     }
     	
     	public class exitCommand implements Command {

    		@Override
    		public void doCommand(String arg) {
    			model.stop();
    			System.out.println("exit");
    			

    		}  
  }
}
