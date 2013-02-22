package filesystem;

import java.util.ArrayList;

public abstract class Node {
	protected String name;
	protected int id;
	private ArrayList<String> path;
	
	public Node(String name, ArrayList<String> path, int id){
		setName(name);
		this.path = path;
		this.id = id;
	}
	
	public Node(String name, int id){
		setName(name);
		path = new ArrayList<String>();
		this.id = id;
	}
	
	public Node(String name, ArrayList<String> path){
		setName(name);
		this.path = path;
	}
	
	public Node(String name){
		setName(name);
		path = new ArrayList<String>(); 
	}
	
	public void setId(int id){
		this.id = id;
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
	
	public void setPath(ArrayList<String> path){
		this.path = path; 
	}
	
	public int getId(){
		return id;
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
	
	@Override
	public int hashCode(){
		return this.hashCode()*100;
	}
}
