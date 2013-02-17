package dao.database_dao;

import java.util.ArrayList;

import dao.LinkDao;
import filesystem.Dir;
import filesystem.Link;
import filesystem.Node;

public class DataBaseDaoLink implements LinkDao{

	@Override
	public Dir getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRoot(Dir root) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo(ArrayList<Node> backup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Link newlink) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentDir(Dir node) {
		// TODO Auto-generated method stub
		
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
