package dao.database_dao;

import java.util.ArrayList;

import dao.NodeDao;
import filesystem.Dir;
import filesystem.Node;

public class DataBaseDaoNode<PK> implements NodeDao<PK>{

	@Override
	public void update(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PK create(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRoot(Dir root) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dir getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo(ArrayList<Node> backup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy(Node copynode, Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(Node replacenode, Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentDir(Dir currentDir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dir getCurrentDir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void replace(Node replacenode, ArrayList<Integer> replaceto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy(Node copynode, ArrayList<Integer> copyto) {
		// TODO Auto-generated method stub
		
	}

}
