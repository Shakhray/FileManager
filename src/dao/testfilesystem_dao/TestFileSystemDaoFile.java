package dao.testfilesystem_dao;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.NodeAlreadyExistsException;
import filesystem.Dir;
import filesystem.File;
import filesystem.Node;

public class TestFileSystemDaoFile implements FileDao{
	
	private DirDao dirdao;
	private LinkDao linkdao;
	
	private Dir root;
	private Dir currentdir;
	
	public TestFileSystemDaoFile(){
	
	}
	
	public TestFileSystemDaoFile(TestFileSystemDaoDir dirdao, TestFileSystemDaoLink linkdao) {
		this.dirdao = dirdao;
		this.linkdao = linkdao;
	}
	
	public void setDao(TestFileSystemDaoDir dirdao, TestFileSystemDaoLink linkdao){
		this.dirdao = dirdao;
		this.linkdao = linkdao;
	}
	
	public void setRoot(Dir root){
		this.root = root;
	}
	public Dir getRoot(){
		return root;
	}
	private void setRootForDirAndLink(){
		dirdao.setRoot(root);
		linkdao.setRoot(root);
	}
	
	public void undo(ArrayList<Node> backup) {
		root.setInsertedNode(backup);
		setRootForDirAndLink();
	}
	public void create(File newfile) {
		currentdir.addNode(newfile);
		setRootForDirAndLink();
	}
	
	public void setCurrentDir(Dir currentdir) {
		this.currentdir = currentdir;
	}
	
	public void delete(File delfile) {
		currentdir.deleteNode(delfile);
		setRootForDirAndLink();
	}

	public void rename(File file, String renameto) {
		int i = currentdir.getInsertedNode().indexOf(file);
		currentdir.getInsertedNode().get(i).setName(renameto);
		setRootForDirAndLink();
	}
	private void findDirToCopy(Dir root, File copyfile, ArrayList<String> copyto) throws NodeAlreadyExistsException{
		if (!copyto.isEmpty())
			for(Node node : root.getInsertedNode()){
				if (node.getName().equals(copyto.get(0))){
					copyto.remove(0);
					try{
						findDirToCopy((Dir)node, copyfile, copyto);
					}catch(NodeAlreadyExistsException e){
						throw new NodeAlreadyExistsException();
					}
					break;
				}
			}
		else {
			boolean b = true;
			for(Node node : root.getInsertedNode()){
				if (node.getName().equals(copyfile.getName())) b = false;
				break;
			}
			if (b) root.addNode(copyfile);
			else throw new NodeAlreadyExistsException();
		}
	}
	public void copy(File copyfile, ArrayList<String> copyto) {
		copyto.remove(0);
		findDirToCopy(root, copyfile, copyto);
		setRootForDirAndLink();
	}
	
	public void replace(File replacefile, ArrayList<String> replaceto) {
		replaceto.remove(0);
		findDirToCopy(root, replacefile, replaceto);
		currentdir.deleteNode(replacefile);
		setRootForDirAndLink();
	}
}
