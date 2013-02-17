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
	private ArrayList<Node> backup;
	
	public Command(DirDao dirdao, Dir currentDir){
		this.dirdao = dirdao;
		backup = new ArrayList(((Dir)dirdao.getRoot()).getInsertedNode());
		this.dirdao.setCurrentDir(currentDir);
	}
	
	public Command(FileDao filedao, Dir currentDir){
		this.filedao = filedao;
		backup = new ArrayList(((Dir)filedao.getRoot()).getInsertedNode());
		this.filedao.setCurrentDir(currentDir);
	}
	public Command(LinkDao linkdao, Dir currentDir){
		this.linkdao = linkdao;
		backup = new ArrayList(((Dir)linkdao.getRoot()).getInsertedNode());
		this.linkdao.setCurrentDir(currentDir);
	}
	
	public abstract void execute();

	public void undo(){
		dirdao.undo(backup);
	}
}
