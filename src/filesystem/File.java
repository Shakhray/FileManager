package filesystem;

import java.util.ArrayList;
import java.util.Collection;

import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;

public class File extends Node{

	private ArrayList<String> path = new ArrayList<String>();
	private double size = 0;
	
	public File(String fileName, double size, ArrayList<String> path){
		super(fileName);
		this.path = path;
		this.size = size;
	}
	
	public File(String fileName) {
		super(fileName);
	}
	public File(String nameFile, double size) {
		super(nameFile);
		this.size = size;
	}
	
	public ArrayList<String> getPath(){
		return path;
	}
	public String getName(){
		return super.getName();
	}
	public void setName(String fileName){
		super.setName(fileName);
	}
	public double size() {
		return size;
	}

	public boolean isDir() {
		return false;
	}

	public boolean isFile() {
		return true;
	}

	public boolean isLink() {
		return false;
	}

	public void addNode(Node newNode) throws NodeAlreadyExistsException, OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}
	public void deleteNode(Node delNode) throws OperationNotSupportedException{
		throw new OperationNotSupportedException();
	}

	@Override
	public ArrayList<Node> getInsertedNode()
			throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

}
