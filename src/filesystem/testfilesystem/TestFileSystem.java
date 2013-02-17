package filesystem.testfilesystem;

import java.util.ArrayList;

import exception.NodeAlreadyExistsException;
import filesystem.Dir;
import filesystem.File;
import filesystem.Link;

public class TestFileSystem {
	private Dir root = new Dir("root"); 
	
	public TestFileSystem() throws NodeAlreadyExistsException{
		setFileSystem();
	}
	public void setFileSystem() throws NodeAlreadyExistsException{
		
		ArrayList<String> path = new ArrayList<String>();
		path.add(root.getName());
		
		Dir dir1 = new Dir("dir1", path);
		File t = new File("t.txt",12, path);
		Link link = new Link("link.lnk", path);
		
			
		ArrayList<String> path2 = new ArrayList<String>();
		path2 = new ArrayList(dir1.getPath());
		path2.add(dir1.getName());
		
		Dir photo = new Dir("photo", path2);		
		Dir text = new Dir("text", path2);
		File move = new File("move.avi",13, path2);
		
		ArrayList<String> path3 = new ArrayList<String>();
		path3 = new ArrayList(photo.getPath());
		path3.add(photo.getName());
		
		File image1 = new File("image1.jpeg", 14, path3);
		File image2 = new File("image2.jpeg", 15, path3);
		
		ArrayList<String> path4 = new ArrayList<String>();
		path4 = new ArrayList(text.getPath());
		path4.add(text.getName());
		
		File textfile = new File("text.txt", 10, path4);
		
		link.setLink(text);
		
		text.addNode(textfile);
		
		photo.addNode(image1);
		photo.addNode(image2);
		
		dir1.addNode(photo);
		dir1.addNode(text);
		dir1.addNode(move);

		root.addNode(dir1);
		root.addNode(t);
		root.addNode(link);
	}
	
	/*public void setFileSystem() throws NodeAlreadyExistsException, OperationNotSupportedException{
		
		ArrayList<String> path = new ArrayList<String>();
		path.add("root");
		
		Node dir1 = new Dir("dir1", path);
		Node t = new File("t.txt",12, path);
		
		ArrayList<String> path2 = new ArrayList<String>();
		path2.add("root");
		path2.add("dir1");
		
		Node photo = new Dir("photo", path2);		
		Node text = new Dir("text", path2);
		Node move = new File("move.avi",13, path2);
		
		ArrayList<String> path3 = new ArrayList<String>();
		path3.add("root");
		path3.add("dir1");
		path3.add("photo");
		
		Node image1 = new File("image1.jpeg", 14, path3);
		Node image2 = new File("image2.jpeg", 15, path3);
		
		ArrayList<String> path4 = new ArrayList<String>();
		path4.add("root");
		path4.add("dir1");
		path4.add("text");
		
		Node textfile = new File("text.txt", 10, path4);
		
		text.addNode(textfile);
		
		photo.addNode(image1);
		photo.addNode(image2);
		
		dir1.addNode(photo);
		dir1.addNode(text);
		dir1.addNode(move);

		root.addNode(dir1);
		root.addNode(t);
		
	}*/
	
	public Dir getRoot(){
		return root;
	}
}
