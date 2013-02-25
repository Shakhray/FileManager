package filesystem;

import java.util.ArrayList;

import exception.NodeAlreadyExistsException;

public class Dir extends Node{
	
	private ArrayList<Node> insertedNode = new ArrayList<Node>();
	
	/*public Dir(String dirName, ArrayList<String> path, int id) {
		super(dirName, path, id);
	}
	
	public Dir(String dirName, int id) {
		super(dirName, id);
	}*/
	
	
	public Dir(Dir dir){
		super(dir);
	}
	
	public Dir(String dirName, ArrayList<Integer> path) {
		super(dirName, path);
	}
	
	public Dir(String dirName) {
		super(dirName);
	}
	
	public double size() {
		double total = 0;
		for (Node node : insertedNode) {
			total+=node.size();
		}
		return total;
	}
	
	public ArrayList<Node> getInsertedNode(){
		return 	insertedNode;
	}
	public boolean isDir(){
		return true;
	}
	public boolean isFile(){
		return false;
	}
	public boolean isLink(){
		return false;
	}
	public void addNode(Node newNode) throws NodeAlreadyExistsException{
		boolean b = true;
		for(Node node : insertedNode)
			if (node.equals(newNode)) b = false;
		if (b) insertedNode.add(newNode); 
		else throw new NodeAlreadyExistsException();
	}
	public void deleteNode(Node delNode){
		insertedNode.remove(delNode);
	}
	public void setInsertedNode(ArrayList<Node> insertedNode){
		this.insertedNode = insertedNode;
	}
}
