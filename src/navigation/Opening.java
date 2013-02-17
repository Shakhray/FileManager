package navigation;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.NodeNotFoundException;
import filesystem.Dir;
import filesystem.Link;
import filesystem.Node;

public class Opening extends Navigation{
	
	private DirDao dirdao;
	private LinkDao linkdao;
	private FileDao filedao;
	
	private Link openlink;
	private Dir downdir;
	private int KEY = 0;
	
	public Opening(DirDao dirdao, LinkDao linkdao, FileDao filedao, Link openlink){
		this.dirdao = dirdao;
		this.linkdao = linkdao;
		this.filedao = filedao;
		this.openlink = openlink;
		KEY = 1;
	}
	
	public void execute() throws  NodeNotFoundException{
		if (KEY == 1){
			System.out.println(openlink.linkTo().getName());
			ArrayList<String> path = new ArrayList<String>();
			path = openlink.linkTo().getPath();
			path.add(openlink.linkTo().getName());
			path.remove(0);
			Node node = findNode(dirdao.getRoot(), path);
			if (node != null){
				if (node.isDir()){
					downdir=dirdao.getCurrentDir();
					dirdao.setCurrentDir((Dir)node); 
				}
			}
			else throw new NodeNotFoundException();
		}
	}
	private Node findNode (Dir root, ArrayList<String> path){
		Node findnode = null;
		if (path.size()>1){
		for (Node node : root.getInsertedNode())
			if (node.getName().equals(path.get(0))) {
				path.remove(0);
				findnode = findNode((Dir)node, path);
				break;
			}
		}
		else
			if (path.size()==1){
				for (Node node : root.getInsertedNode())
					if (node.getName().equals(path.get(0))) {
						findnode = node;
						break;
					}
				}
		if (findnode != null) return findnode;
		else throw new NodeNotFoundException();
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
