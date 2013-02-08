package command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import filesystem.Node;


public abstract class Command {
	protected LinkDao linkdao;
	protected FileDao filedao;
	protected DirDao dirdao;
	
	private Collection<Node> backup;

	private void backup(){
		
	}
	
	public abstract void execute();

	public void undo() {
	
	}
}
