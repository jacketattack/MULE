package game;

public interface Command 
{
	public boolean execute();
	public boolean undo();
}
