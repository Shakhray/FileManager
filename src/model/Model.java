package model;

import dao.*;
import dao.factory.DaoFactory;
import dao.factory.DataBaseDaoFactory;
import dao.factory.TestFileSystemDaoFactory;

public class Model {
	private LinkDao linkdao;
	private FileDao filedao;
	private DirDao dirdao;
	private DaoFactory factory; 
	
	public Model(){
		factory = new DataBaseDaoFactory();
		linkdao = factory.getDaoLink();
		filedao = factory.getDaoFile();
		dirdao = factory.getDaoDir();
	}
	public Model(String key){
		
		ModelKey modelkey = ModelKey.valueOf(key.toUpperCase());
		switch(modelkey){
			case DATABASE : {factory = new DataBaseDaoFactory(); break;}
			case TEST : {factory = new TestFileSystemDaoFactory(); break;}
			default : throw new IllegalArgumentException();
		}		
		linkdao = factory.getDaoLink();
		filedao = factory.getDaoFile();
		dirdao = factory.getDaoDir();
	}
	public LinkDao getLinkDao(){
		return linkdao;
	}
	public FileDao getFileDao(){
		return filedao;
	}
	public DirDao getDirDao(){
		return dirdao;
	}
}
