package use;

import manager.FileManager;
import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;
import filesystem.Node;
import filesystem.testfilesystem.TestFileSystem;
import viewers.TestConsole;

public class Main {

	public static void main(String[] args) {
		TestConsole cons = new TestConsole();
		cons.runConsole();
		FileManager fm = null;
		TestFileSystem fs = null;
		try {
			fm = new FileManager("test");
			fs = new TestFileSystem();
		} catch (NodeAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Node root = fs.getRoot();
		System.out.println(root.getName());
		
		System.out.println(fm.getCurrentDir());
	}

}
