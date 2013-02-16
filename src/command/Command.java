package command;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.OperationNotSupportedException;

import filesystem.Dir;
import filesystem.Node;

public abstract class Command {
	protected LinkDao linkdao;
	protected FileDao filedao;
	protected DirDao dirdao;
	//private Node backup = new Dir("root");
	private ArrayList<Node> backup;
	
	public Command(DirDao dirdao, Dir currentDir) throws OperationNotSupportedException{
		this.dirdao = dirdao;
		//ArrayList<Node> insertedNode = new ArrayList(dirdao.getRoot().getInsertedNode());
		backup = new ArrayList(((Dir)dirdao.getRoot()).getInsertedNode());
		//backup.setInsertedNode(insertedNode);
		this.dirdao.setCurrentDir(currentDir);
		//backup = dirdao.getRoot();
	}
	
	public abstract void execute();

	public void undo(){
		dirdao.undo(backup);
	}
}
