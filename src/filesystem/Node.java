package filesystem;

import java.util.ArrayList;

public abstract class Node {
	protected String name;
	private final int id;// = hashCode();
	private ArrayList<Integer> path;
	//private ArrayList<Integer> pathById;
	
	/*public Node(String name, ArrayList<String> path, int id){
		setName(name);
		this.path = path;
		this.id = id;
	}
	
	public Node(String name, int id){
		setName(name);
		path = new ArrayList<String>();
		this.id = id;
	}*/
	
	public Node(Node node){
		name = node.getName();
		path = node.getPath();
		id = hashCode();
	}
	
	public Node(String name, ArrayList<Integer> path){
		setName(name);
		this.path = path;
		id = hashCode();
	}
	
	public Node(String name){
		setName(name);
		path = new ArrayList<Integer>(); 
		id = hashCode();
	}
	
	/*public void setId(int id){
		this.id = id;
	}*/
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Integer> getPath(){
		return path;
	}
	
	/*public ArrayList<Integer> getPathById(){
		return pathById;
	}*/
	
	public void setPath(ArrayList<Integer> path){
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
		int hash = 0;
		hash += name.hashCode();
		hash += path.hashCode();
		return hash;
	}
}
