package dao.database_dao;

import java.util.ArrayList;

import dao.FileDao;
import filesystem.Dir;
import filesystem.File;
import filesystem.Node;

public class DataBaseDaoFile implements FileDao{

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
	public void create(File newfile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentDir(Dir node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(File delfile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rename(File dir, String renameto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy(File copyfile, ArrayList<String> copyto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(File replacefile, ArrayList<String> replaceto) {
		// TODO Auto-generated method stub
		
	}

}
