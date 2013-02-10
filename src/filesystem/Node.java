package filesystem;

import java.util.ArrayList;
import java.util.Collection;

import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;

public abstract class Node {
	protected String name;
	
	public Node(String name){
		setName(name);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract double size();
	public abstract ArrayList<String> getPath();
	public abstract boolean isDir();
	public abstract boolean isFile();
	public abstract boolean isLink();
	
	public boolean equals(Node node){
		if (this.name.equals(node.getName())&&
			(this.isDir()==node.isDir() || this.isFile() == node.isFile() || this.isLink() == node.isLink())) 
			return true;
		else return false;
	}
	public abstract void addNode(Node newNode) throws NodeAlreadyExistsException, OperationNotSupportedException;
	public abstract void deleteNode(Node delNode) throws OperationNotSupportedException;
	public abstract ArrayList<Node> getInsertedNode() throws OperationNotSupportedException;
}
