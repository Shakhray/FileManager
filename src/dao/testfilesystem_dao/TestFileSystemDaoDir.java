package dao.testfilesystem_dao;

import java.util.ArrayList;

import dao.DirDao;
import exception.NodeAlreadyExistsException;

import filesystem.Dir;
import filesystem.Node;
import filesystem.testfilesystem.TestFileSystem;

public class TestFileSystemDaoDir implements DirDao{
	private TestFileSystem fs;
	private Dir root;
	private Dir currentdir;
	
	public TestFileSystemDaoDir(){
		fs = new TestFileSystem();
		root = fs.getRoot();
		currentdir = root;
	}
	public void setRoot(Dir root){
		this.root = root;
	}
	public Dir getRoot(){
		return root;
	}
	public Dir getCurrentDir(){
		return currentdir;
	}
	public void setCurrentDir(Dir currentdir){
		this.currentdir = currentdir;  
	}
		
	public void rename(Dir dir, String renameto){
		int i = currentdir.getInsertedNode().indexOf(dir);
		currentdir.getInsertedNode().get(i).setName(renameto);
	}
		
	public void delete(Dir deldir){
		currentdir.deleteNode(deldir);
	}

	public void create(Dir newdir){
		currentdir.addNode(newdir);
	}
	
	public void undo(ArrayList<Node> backup){
		root.setInsertedNode(backup);
	}
	private void findDirToCopy(Dir root, Dir copyDir, ArrayList<String> copyto) throws NodeAlreadyExistsException{
		if (!copyto.isEmpty())
			for(Node node : root.getInsertedNode()){
				if (node.getName().equals(copyto.get(0))){
					copyto.remove(0);
					try{
						findDirToCopy((Dir)node, copyDir, copyto);
					}catch(NodeAlreadyExistsException e){
						throw new NodeAlreadyExistsException();
					}
					break;
				}
			}
		else {
			boolean b = true;
			for(Node node : root.getInsertedNode()){
				if (node.getName().equals(copyDir.getName())) b = false;
				break;
			}
			if (b) root.addNode(copyDir);
			else throw new NodeAlreadyExistsException();
		}
	}
	public void copy(Dir copydir, ArrayList<String> copyto) throws NodeAlreadyExistsException{
		
		copyto.remove(0);
		findDirToCopy(root, copydir, copyto);
	}
	
	public void replace(Dir replacedir, ArrayList<String> replaceto) throws NodeAlreadyExistsException{

		replaceto.remove(0);
		findDirToCopy(root, replacedir, replaceto);
		currentdir.deleteNode(replacedir);
	}
}