package view;

public class CLIRunnable implements Runnable  {
	
	NewCli clI;

	public CLIRunnable(NewCli clI) {
	
		super();
		this.clI = clI;
	
	}
	
	@Override
	public void run() {
		
		clI.start();
		
	}

}
