package model;

import dao.*;
import dao.factory.DaoFactory;
import dao.factory.DataBaseDaoFactory;

public class Model {
	private LinkDao linkdao;
	private FileDao filedao;
	private DirDao dirdao;
	private DaoFactory factory; 
	
	public Model(){
		factory = new DataBaseDaoFactory();
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
