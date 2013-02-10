package model;

import java.io.IOException;

import dao.*;
import dao.factory.DaoFactory;
import dao.factory.DataBaseDaoFactory;
import dao.factory.TestFileSystemDaoFactory;
import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;

public class Model {
	private LinkDao linkdao;
	private FileDao filedao;
	private DirDao dirdao;
	private DaoFactory factory; 
	
	public Model() throws NodeAlreadyExistsException, OperationNotSupportedException{
		factory = new DataBaseDaoFactory();
		linkdao = factory.getDataBaseDaoLink();
		filedao = factory.getDataBaseDaoFile();
		dirdao = factory.getDataBaseDaoDir();
	}
	public Model(String key) throws NodeAlreadyExistsException, OperationNotSupportedException{
		
		ModelKey modelkey = ModelKey.valueOf(key.toUpperCase());
		switch(modelkey){
			case DATABASE : {factory = new DataBaseDaoFactory(); break;}
			case TEST : {factory = new TestFileSystemDaoFactory(); break;}
			default : throw new IllegalArgumentException();
		}		
		linkdao = factory.getDataBaseDaoLink();
		filedao = factory.getDataBaseDaoFile();
		dirdao = factory.getDataBaseDaoDir();
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
