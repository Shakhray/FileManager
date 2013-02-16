package navigation;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.NodeNotFoundException;
import filesystem.Dir;
import filesystem.Node;

public class CD {
	protected LinkDao linkdao;
	protected FileDao filedao;
	protected DirDao dirdao;
	private Dir downdir;
	private String dir;
	
	public CD(DirDao dirdao, Dir currentDir, String dir){
		this.dirdao = dirdao;
		this.dirdao.setCurrentDir(currentDir);
		this.dir = dir;
	}
	
	public void execute() throws  NodeNotFoundException{
		boolean b =true;
		for (Node node : dirdao.getCurrentDir().getInsertedNode()){
			if (node.getName().equals(dir)) {
				downdir=dirdao.getCurrentDir();
				dirdao.setCurrentDir((Dir)node); 
				b = false;
			}
		}
		if (b) throw new NodeNotFoundException();
	}
	public void downDir(){
		dirdao.setCurrentDir(downdir);
	}
	public Node getCurrentDir(){
		return dirdao.getCurrentDir();
	}
}
