package dao.testfilesystem_dao;

import dao.LinkDao;
import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;
import filesystem.Node;
import filesystem.testfilesystem.TestFileSystem;

public class TestFileSystemDaoLink implements LinkDao{
	private TestFileSystem fs;
	private Node root;
	
	public TestFileSystemDaoLink() throws NodeAlreadyExistsException, OperationNotSupportedException{
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
