package dao.database_dao;

import java.util.ArrayList;
import java.util.Collection;

import dao.DirDao;
import filesystem.Dir;
import filesystem.Node;

public class DataBaseDaoDir implements DirDao{
	private Collection<Node> dir;
	
	public void setDir(Collection<Node> dir){
		this.dir = dir;
	}
	
	public Collection<Node> getDir(){
		return dir;
	}

	@Override
	public void undo(ArrayList<Node> backup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Dir newdir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentDir(Dir node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Dir deldir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rename(Dir dir, String renameto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy(Dir copynode, ArrayList<String> copyto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(Dir replacenode, ArrayList<String> replaceto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dir getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dir getCurrentDir() {
		// TODO Auto-generated method stub
		return null;
	}
}
