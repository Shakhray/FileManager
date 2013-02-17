package filesystem;

import java.util.ArrayList;

public abstract class Node {
	protected String name;
	private ArrayList<String> path;
	
	
	public Node(String name, ArrayList<String> path){
		setName(name);
		this.path = path;
	}
	public Node(String name){
		setName(name);
		path = new ArrayList<String>(); 
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	public ArrayList<String> getPath(){
		return path;
	}
	
	public abstract double size();
	public abstract boolean isDir();
	public abstract boolean isFile();
	public abstract boolean isLink();
	
	public boolean equals(Node node){
		if (this.name.equals(node.getName())&&
			(this.isDir()==node.isDir() || this.isFile() == node.isFile() || this.isLink() == node.isLink())) 
			return true;
		else return false;
	}
}
