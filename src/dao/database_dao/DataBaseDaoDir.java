package dao.database_dao;

import java.util.Collection;

import dao.DirDao;
import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;
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
	public Node getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo(Node backup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(String name) throws OperationNotSupportedException,
			NodeAlreadyExistsException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setCurrentDir(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getCurrentDir() {
		// TODO Auto-generated method stub
		return null;
	}
}
