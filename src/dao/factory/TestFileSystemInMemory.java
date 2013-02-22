package dao.factory;

import exception.NodeNotFoundException;
import filesystem.Dir;
import filesystem.Node;
import filesystem.testfilesystem.TestFileSystem;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 21.02.13
 * Time: 0:33
 * To change this template use File | Settings | File Templates.
 */
public class TestFileSystemInMemory{

	private static TestFileSystem FS = new TestFileSystem();
	
    private static Dir root = FS.getRoot();

    public static Dir getRoot() {
        return root;
    }

    public static Node getNode(ArrayList<String> path){
        return findNode(root, path);
    }
   
    public static void updateNode(Node node){
    	System.out.println(node.getId());
    	System.out.println(node.getPath().toString());
    	Dir currentDir = (Dir)findNodeToModify(root, node.getPath());
    	for (Node insNode : currentDir.getInsertedNode()){
    		if (insNode.getId() == node.getId()) {
    			
    			int index = currentDir.getInsertedNode().indexOf(insNode);
    			currentDir.deleteNode(insNode);
    			currentDir.getInsertedNode().add(index, node);
    			//currentDir.addNode(node);
    		}
    		break;
    	}
    }
    public static Integer createNode(Node node){
    	node.setId(node.hashCode());
    	Dir currentDir =  (Dir)findNodeToModify(root, node.getPath());
    	currentDir.addNode(node);
		return node.getId();
    }
    public static void deleteNode(Node node){
    	Dir currentDir =  (Dir)findNodeToModify(root, node.getPath());
    	currentDir.deleteNode(node);
    }
    
    private static Node findNode (Dir root, ArrayList<String> path){
		Node findNode = null;
		if (path.size()>1){
		for (Node node : root.getInsertedNode())
			if (node.getName().equals(path.get(1))) {
				path.remove(0);
				findNode = findNode((Dir)node, path);
				break;
			}
		}
		else
			if (path.size()==1){
				for (Node node : root.getInsertedNode())
					if (node.getName().equals(path.get(0))) {
						findNode = node;
						break;
					}
				}
		if (findNode != null) return findNode;
		else throw new NodeNotFoundException();
	}
    private static Node findNodeToModify (Dir root, ArrayList<String> path){
		Node findNode = null;
		if (path.size()>1){
		for (Node node : root.getInsertedNode())
			if (node.getName().equals(path.get(1))) {
				path.remove(0);
				findNode = findNodeToModify((Dir)node, path);
				break;
			}
		}
		else
			if (path.size()==1)	findNode = root;
		if (findNode != null) return findNode;
		else throw new NodeNotFoundException();
	}
}
