package search;

import java.util.ArrayList;

import dao.NodeDao;
import exception.NodeNotFoundException;

import filesystem.Dir;
import filesystem.Node;

public class Search {
	
	private NodeDao nodedao;
	private ArrayList<Integer> pathById;
	private Dir root;
	
	public Search(NodeDao nodedao){
		this.nodedao = nodedao;
		root = nodedao.getRoot();
		pathById = new ArrayList<Integer>();
		pathById.add(nodedao.getRoot().getId());
	}
	
	private void createDefaultPath(){
		pathById.clear();
		pathById.add(nodedao.getRoot().getId());
	}
	
	/*private void findPathByString(Dir root, ArrayList<String> path, ArrayList<Integer> pathById){
		
		if (!path.isEmpty()){
			System.out.println("oioioi");
		for (Node node : root.getInsertedNode())
			if (node.getName().equals(path.get(0))) {
				System.out.println("ppppppp");
				pathById.add(node.getId());
				path.remove(0);
				findPathByString((Dir)node, path, pathById);
				break;
			}
		}
	}*/
	
	private void findPathByString(Dir root, ArrayList<String> path){
		if (!path.isEmpty()){
			System.out.println("oioioi");
		for (Node node : root.getInsertedNode())
			if (node.getName().equals(path.get(0))) {
				System.out.println(node.getId());
				pathById.add(node.getId());
				//System.out.println("!!!!   "+pathById.get(pathById.size()-1));
				System.out.println(pathById.toString());
				path.remove(0);
				findPathByString((Dir)node, path);
				break;
			}
		}
	}
	
	public ArrayList<Integer> findPath(ArrayList<String> strPath){
		createDefaultPath();
		//System.out.print(strPath.toString());
		strPath.remove(0);
		findPathByString(root, new ArrayList<String>(strPath));
		System.out.print(pathById.toString());
		return pathById;
	}
	
	public boolean isExistPath(ArrayList<String> strPath){
		createDefaultPath();
		int size = strPath.size();
		strPath.remove(0);
		findPathByString(root, new ArrayList<String>(strPath));
		System.out.println(pathById.size());
		System.out.println(strPath.size());
		if (size == pathById.size()) return true;
		else return false;
	}
	
	public Node findNode(ArrayList<Integer> path){
		return findNodeById(root, path);
	}
	
	private Node findNodeById (Dir root, ArrayList<Integer> path){
		Node findnode = null;
		if (path.size()>1){
		for (Node node : root.getInsertedNode())
			if (node.getName().equals(path.get(0))) {
				path.remove(0);
				findnode = findNodeById((Dir)node, path);
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
}
