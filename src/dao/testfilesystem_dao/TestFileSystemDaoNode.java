package dao.testfilesystem_dao;

import java.util.ArrayList;

import dao.NodeDao;
import dao.factory.TestFileSystemInMemory;
import exception.NodeAlreadyExistsException;
import filesystem.Dir;
import filesystem.Node;

public class TestFileSystemDaoNode<PK> implements NodeDao<PK> {

	private Dir root;
	private Dir currentDir;
	
	public TestFileSystemDaoNode(){
		root = TestFileSystemInMemory.getRoot();
		currentDir = root;
	}
	
	public void setRoot(Dir root){
		this.root = root;
	}
	public Dir getRoot(){
		return root;
	}
	
	public void setCurrentDir(Dir currentDir){
		this.currentDir = currentDir;
	}
		
	public Dir getCurrentDir(){
		return currentDir;
	}
	
	public void update(Node node){
		TestFileSystemInMemory.updateNode(node);
	}
		
	public void delete(Node node){
		TestFileSystemInMemory.deleteNode(node);
	}

	public PK create(Node node){
		PK id = (PK) TestFileSystemInMemory.createNode(node);
		return id;
	}
	
	public void undo(ArrayList<Node> backup){
		root.setInsertedNode(backup);
	}
	
	public void copy(Node copyNode, ArrayList<Integer> copyto) throws NodeAlreadyExistsException{
		copyNode.setPath(copyto);
		TestFileSystemInMemory.createNode(copyNode);
	}
	
	public void replace(Node replaceNode, ArrayList<Integer> replaceTo) throws NodeAlreadyExistsException{
		TestFileSystemInMemory.deleteNode(replaceNode);
		replaceNode.setPath(replaceTo);
		TestFileSystemInMemory.createNode(replaceNode);
	}

	@Override
	public void copy(Node copynode, Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(Node replacenode, Integer id) {
		// TODO Auto-generated method stub
		
	}


}
