package dao.testfilesystem_dao;

import dao.FileDao;
import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;
import filesystem.Node;
import filesystem.testfilesystem.TestFileSystem;

public class TestFileSystemDaoFile implements FileDao{
	private TestFileSystem fs;
	private Node root;
	
	public TestFileSystemDaoFile() throws NodeAlreadyExistsException, OperationNotSupportedException{
		fs = new TestFileSystem();
		root = fs.getRoot();
	}
	public void setRoot(Node root){
		this.root = root;
	}
	public void setFSForDirAndLink(){
		
	}
	public Node getRoot(){
		return root;
	}
}
