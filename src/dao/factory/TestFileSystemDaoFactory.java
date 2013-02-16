package dao.factory;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import dao.testfilesystem_dao.TestFileSystemDaoDir;
import dao.testfilesystem_dao.TestFileSystemDaoFile;
import dao.testfilesystem_dao.TestFileSystemDaoLink;

public class TestFileSystemDaoFactory extends DaoFactory{

	public LinkDao getDataBaseDaoLink(){
		return new TestFileSystemDaoLink();
	}

	public FileDao getDataBaseDaoFile(){
		return new TestFileSystemDaoFile();
	}

	public DirDao getDataBaseDaoDir(){
		return new TestFileSystemDaoDir();
	}

}
