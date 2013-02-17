package dao.testfilesystem_dao;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.NodeAlreadyExistsException;
import filesystem.Dir;
import filesystem.Link;
import filesystem.Node;
public class TestFileSystemDaoLink implements LinkDao{
	
	private DirDao dirdao;
	private FileDao filedao;
	
	private Dir root;
	private Dir currentdir;
	
	public TestFileSystemDaoLink(){

	}
	
	public TestFileSystemDaoLink(TestFileSystemDaoDir dirdao, TestFileSystemDaoFile filedao){
		this.dirdao = dirdao;
		this.filedao = filedao;
	}
	
	public void setDao(TestFileSystemDaoDir dirdao, TestFileSystemDaoFile filedao){
		this.dirdao = dirdao;
		this.filedao = filedao;
	}
	
	private void setRootForDirAndFile(){
		dirdao.setRoot(root);
		filedao.setRoot(root);
	}	
	
	public void setRoot(Dir root){
		this.root = root;
	}
	
	public Dir getRoot(){
		return root;
	}
	
	public void undo(ArrayList<Node> backup) {
		root.setInsertedNode(backup);
		setRootForDirAndFile();
	}
	
	public void create(Link newlink) {
		currentdir.addNode(newlink);
		setRootForDirAndFile();
	}

	public void setCurrentDir(Dir currentdir) {
		this.currentdir = currentdir;	
	}
	
	public void delete(Link dellink) {
		currentdir.deleteNode(dellink);
		setRootForDirAndFile();
	}

	public void rename(Link link, String renameto) {
		int i = currentdir.getInsertedNode().indexOf(link);
		currentdir.getInsertedNode().get(i).setName(renameto);
		setRootForDirAndFile();
	}
	private void findDirToCopy(Dir root, Link copylink, ArrayList<String> copyto) throws NodeAlreadyExistsException{
		if (!copyto.isEmpty())
			for(Node node : root.getInsertedNode()){
				if (node.getName().equals(copyto.get(0))){
					copyto.remove(0);
					try{
						findDirToCopy((Dir)node, copylink, copyto);
					}catch(NodeAlreadyExistsException e){
						throw new NodeAlreadyExistsException();
					}
					break;
				}
			}
		else {
			boolean b = true;
			for(Node node : root.getInsertedNode()){
				if (node.getName().equals(copylink.getName())) b = false;
				break;
			}
			if (b) root.addNode(copylink);
			else throw new NodeAlreadyExistsException();
		}
	}
	public void copy(Link copylink, ArrayList<String> copyto) {
		copyto.remove(0);
		findDirToCopy(root, copylink, copyto);
		setRootForDirAndFile();
	}
	
	public void replace(Link replacelink, ArrayList<String> replaceto) {
		replaceto.remove(0);
		findDirToCopy(root, replacelink, replaceto);
		currentdir.deleteNode(replacelink);
		setRootForDirAndFile();
	}
}
