package filesystem.testfilesystem;

import java.util.ArrayList;

import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;
import filesystem.Dir;
import filesystem.File;
import filesystem.Node;

public class TestFileSystem {
	private Node root = new Dir("root");
	
	public TestFileSystem() throws NodeAlreadyExistsException, OperationNotSupportedException{
		setFileSystem();
	}
	public void setFileSystem() throws NodeAlreadyExistsException, OperationNotSupportedException{
		
		ArrayList<String> path = new ArrayList<String>();
		
		path = root.getPath();
		path.add(root.getName());
		
		Node dir1 = new Dir("dir1", path);
		Node t = new File("t.txt",12, path);
		
		path = dir1.getPath();
		path.add(dir1.getName());
		
		Node photo = new Dir("photo", path);		
		Node text = new Dir("text", path);
		Node move = new File("move.avi",13, path);
		
		path = photo.getPath();
		path.add(photo.getName());
		
		Node image1 = new File("image1.jpeg", 14, path);
		Node image2 = new File("image2.jpeg", 15, path);
		
		path = text.getPath();
		path.add(text.getName());
		
		Node textfile = new File("text.txt", 10, path);
		
		root.addNode(dir1);
		root.addNode(t);
		
		dir1.addNode(photo);
		dir1.addNode(text);
		dir1.addNode(move);
		
		photo.addNode(image1);
		photo.addNode(image2);
		
		text.addNode(textfile);
	}
	
	public Node getRoot(){
		return root;
	}
}
