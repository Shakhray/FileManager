package dao.testfilesystem_dao;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import filesystem.Dir;
import filesystem.Link;
import filesystem.Node;
import filesystem.testfilesystem.TestFileSystem;

public class TestFileSystemDaoLink implements LinkDao{
	
	private DirDao dirdao;
	private FileDao filedao;
	
	private TestFileSystem fs;
	private Dir root;
	private Dir currentdir;
	
	public TestFileSystemDaoLink(){
		//fs = new TestFileSystem();
		//root = fs.getRoot();
	}
	
	public TestFileSystemDaoLink(TestFileSystemDaoDir dirdao, TestFileSystemDaoFile filedao){
		//fs = new TestFileSystem();
		//root = fs.getRoot();
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
	//	dirdao.setCurrentDir(currentdir);
	//	filedao.setCurrentDir(currentdir);
	}	
	
	public void setRoot(Dir root){
		this.root = root;
	}
	
	
	
	public Dir getRoot(){
		return root;
	}
	@Override
	public void undo(ArrayList<Node> backup) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void create(Link newlink) {
		// TODO Auto-generated method stub
		
	}

	public void setCurrentDir(Dir currentdir) {
		this.currentdir = currentdir;	
	}
	@Override
	public void delete(Link dellink) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rename(Link dir, String renameto) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void copy(Link copylink, ArrayList<String> copyto) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void replace(Link replacelink, ArrayList<String> replaceto) {
		// TODO Auto-generated method stub
		
	}
}
