package navigation;

import java.io.IOException;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.NodeAlreadyExistsException;
import exception.NodeNotFoundException;
import exception.OperationNotSupportedException;
import filesystem.Node;

public class CD {
	protected LinkDao linkdao;
	protected FileDao filedao;
	protected DirDao dirdao;
	private Node downdir;
	private String dir;
	
	public CD(DirDao dirdao, Node currentDir, String dir){
		this.dirdao = dirdao;
		this.dirdao.setCurrentDir(currentDir);
		this.dir = dir;
	}
	
	public void execute() throws OperationNotSupportedException, NodeAlreadyExistsException, NodeNotFoundException{
		boolean b =true;
		for (Node node : dirdao.getCurrentDir().getInsertedNode()){
			if (node.getName().equals(dir)) {
				downdir=dirdao.getCurrentDir();
				dirdao.setCurrentDir(node); 
				b = false;
			}
		}
		if (b) throw new NodeNotFoundException();
	}
	public void downDir(){
		dirdao.setCurrentDir(downdir);
	}
}
