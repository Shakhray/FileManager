package navigation;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.IsNotDirectryException;
import exception.NodeNotFoundException;
import filesystem.Dir;
import filesystem.Node;

public class CD extends Navigation{
	private LinkDao linkdao;
	private FileDao filedao;
	private DirDao dirdao;
	private Dir downdir;
	private String dir;
	
	public CD(DirDao dirdao, FileDao filedao, LinkDao linkdao,  Dir currentDir, String dir){
		super();
		this.dirdao = dirdao;
		this.filedao = filedao;
		this.linkdao = linkdao;
		this.dirdao.setCurrentDir(currentDir);
		this.filedao.setCurrentDir(currentDir);
		this.linkdao.setCurrentDir(currentDir);
		this.dir = dir;
	}
	
	public void execute() throws  NodeNotFoundException, IsNotDirectryException{
		boolean b =true;
		for (Node node : dirdao.getCurrentDir().getInsertedNode()){
			if (node.getName().equals(dir)) {
				if (node.isDir()){
					downdir=dirdao.getCurrentDir();
					dirdao.setCurrentDir((Dir)node); 
					b = false;
					break;
				}
				else throw new IsNotDirectryException();
			}
		}
		if (b) throw new NodeNotFoundException();
	}
	public void downDir(){
		dirdao.setCurrentDir(downdir);
		filedao.setCurrentDir(downdir);
		linkdao.setCurrentDir(downdir);
	}
	public Node getCurrentDir(){
		return dirdao.getCurrentDir();
	}
}
