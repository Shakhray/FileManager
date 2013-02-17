package dao.testfilesystem_dao;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.NodeAlreadyExistsException;

import filesystem.Dir;
import filesystem.Node;
import filesystem.testfilesystem.TestFileSystem;

public class TestFileSystemDaoDir implements DirDao{
	
	private FileDao filedao;
	private LinkDao linkdao;
	
	private TestFileSystem fs;
	private Dir root;
	private Dir currentdir;
	
	public TestFileSystemDaoDir(){
		//fs = new TestFileSystem();
		//root = fs.getRoot();
		//currentdir = root;
	}	
	public TestFileSystemDaoDir(TestFileSystemDaoFile filedao, TestFileSystemDaoLink linkdao){
		//fs = new TestFileSystem();
		//root = fs.getRoot();
		//currentdir = root;
		this.filedao = filedao;
		this.linkdao = linkdao;
	}
	public void setDao(TestFileSystemDaoFile filedao, TestFileSystemDaoLink linkdao){
		this.filedao = filedao;
		this.linkdao = linkdao;
	}
	private void setRootForFileAndLink(){
		filedao.setRoot(root);
		linkdao.setRoot(root);
		//filedao.setCurrentDir(currentdir);
		//linkdao.setCurrentDir(currentdir);
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
		setRootForFileAndLink();
	}
		
	public void delete(Dir deldir){
		currentdir.deleteNode(deldir);
		setRootForFileAndLink();
	}

	public void create(Dir newdir){
		currentdir.addNode(newdir);
		setRootForFileAndLink();
	}
	
	public void undo(ArrayList<Node> backup){
		root.setInsertedNode(backup);
		setRootForFileAndLink();
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
		setRootForFileAndLink();
	}
	
	public void replace(Dir replacedir, ArrayList<String> replaceto) throws NodeAlreadyExistsException{
		replaceto.remove(0);
		findDirToCopy(root, replacedir, replaceto);
		currentdir.deleteNode(replacedir);
		setRootForFileAndLink();
	}
}