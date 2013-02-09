package filesystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import exception.NodeAlreadyExistsException;

public class Dir extends Node{
	
	private ArrayList<String> path = new ArrayList<String>();
	private Collection<Node> insertedNode = new ArrayList<Node>();
	
	public Dir(String dirName) {
		super(dirName);
	}
	public Dir(String dirName, ArrayList<String> path) {
		super(dirName);
		this.path = path;
	}
	
	public ArrayList<String> getPath(){
		return path;
	}
	
	public void setName(String dirName){
		super.setName(dirName);
	}
	public String getName(){
		return super.getName();
	}
	public double size() {
		double total = 0;
		for (Node node : insertedNode) {
			total+=node.size();
		}
		return total;
	}
	
	public Collection<Node> getInsertedNode(){
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
		/*boolean b = true;
		for(Node node : insertedNode)
			if (node.equals(delNode)) b = false;
		if (b) insertedNode.remove(delNode);*/
		insertedNode.remove(delNode);
	}
}
