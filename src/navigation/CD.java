package navigation;

import dao.NodeDao;
import exception.IsNotDirectryException;
import exception.NodeNotFoundException;
import filesystem.Dir;
import filesystem.Node;

public class CD extends Navigation{
	private NodeDao<?> nodeDao;
	private Dir downdir;
	private String dir;
	
	public CD(NodeDao<?> nodeDao,  Dir currentDir, String dir){
		super();
		this.nodeDao = nodeDao;
		this.nodeDao.setCurrentDir(currentDir);
		this.dir = dir;
	}
	
	public void execute() throws  NodeNotFoundException, IsNotDirectryException{
		boolean b =true;
		for (Node node : nodeDao.getCurrentDir().getInsertedNode()){
			if (node.getName().equals(dir)) {
				if (node.isDir()){
					downdir=nodeDao.getCurrentDir();
					nodeDao.setCurrentDir((Dir)node); 
					b = false;
					break;
				}
				else throw new IsNotDirectryException();
			}
		}
		if (b) throw new NodeNotFoundException();
	}
	public void downDir(){
		nodeDao.setCurrentDir(downdir);
	}
	public Node getCurrentDir(){
		return nodeDao.getCurrentDir();
	}
}
