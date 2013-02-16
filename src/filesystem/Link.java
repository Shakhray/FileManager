package filesystem;

import java.util.ArrayList;
import java.util.Collection;

import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;

public class Link extends Node{

	private Node linkedTo;
	public Link(String linkName) {
		super(linkName, null);
	}
	public Link(String linkName, Node LinkedTo) {
		super(linkName, null);
		this.linkedTo = LinkedTo;
	}
	@Override
	public double size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isDir() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isFile() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isLink() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addNode(Node newNode) throws NodeAlreadyExistsException, OperationNotSupportedException {
		throw new OperationNotSupportedException();		
	}

	public void deleteNode(Node delNode) throws OperationNotSupportedException {
		throw new OperationNotSupportedException();		
	}
	@Override
	public ArrayList<String> getPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
