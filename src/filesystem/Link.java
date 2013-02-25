package filesystem;

import java.util.ArrayList;

public class Link extends Node{

	private Node linkedTo;
	
	/*public Link(String linkName, ArrayList<String> path, int id) {
		super(linkName, path, id);
	}
	
	public Link(String linkName, Node LinkedTo, ArrayList<String> path, int id) {
		super(linkName, path, id);
		this.linkedTo = LinkedTo;
	}*/
	
	public Link(Link link){
		super(link);
	}
	
	public Link(String linkName, ArrayList<Integer> path) {
		super(linkName, path);
	}
	public Link(String linkName, Node LinkedTo, ArrayList<Integer> path) {
		super(linkName, path);
		this.linkedTo = LinkedTo;
	}
	
	public double size() {
		return linkedTo.size();
	}
	
	public boolean isDir() {
		return false;
	}

	public boolean isFile() {
		return false;
	}
	
	public boolean isLink() {
		return true;
	}

	public Node linkTo(){
		return linkedTo;
	}
	public void setLink(Node linkedTo){
		this.linkedTo = linkedTo;
	}
}
