package model;

import dao.*;
import dao.factory.DaoFactory;
import dao.factory.DataBaseDaoFactory;
import dao.factory.TestFileSystemDaoFactory;

public class Model {
	private NodeDao<?> nodeDao;
	private DaoFactory factory; 
	
	public Model(){
		factory = new DataBaseDaoFactory();
		nodeDao = factory.getNodeDao();
	}
	public Model(String key){
		
		ModelKey modelkey = ModelKey.valueOf(key.toUpperCase());
		switch(modelkey){
			case DATABASE : {factory = new DataBaseDaoFactory(); break;}
			case TEST : {factory = new TestFileSystemDaoFactory(); break;}
			default : throw new IllegalArgumentException();
		}		
		nodeDao = factory.getNodeDao();
	}
	public NodeDao<?> getNodeDao(){
		return nodeDao;
	}
}
