package dao.database_dao;

import java.util.Collection;

import dao.DirDao;
import filesystem.Node;

public class DataBaseDaoDir implements DirDao{
	private Collection<Node> dir;
	
	public void setDir(Collection<Node> dir){
		this.dir = dir;
	}
	
	public Collection<Node> getDir(){
		return dir;
	}
}
