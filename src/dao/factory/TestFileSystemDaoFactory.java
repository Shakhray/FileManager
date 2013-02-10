package dao.factory;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import dao.testfilesystem_dao.TestFileSystemDaoDir;
import dao.testfilesystem_dao.TestFileSystemDaoFile;
import dao.testfilesystem_dao.TestFileSystemDaoLink;
import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;

public class TestFileSystemDaoFactory extends DaoFactory{

	public LinkDao getDataBaseDaoLink() throws NodeAlreadyExistsException, OperationNotSupportedException {
		return new TestFileSystemDaoLink();
	}

	public FileDao getDataBaseDaoFile() throws NodeAlreadyExistsException, OperationNotSupportedException {
		return new TestFileSystemDaoFile();
	}

	public DirDao getDataBaseDaoDir() throws NodeAlreadyExistsException, OperationNotSupportedException {
		return new TestFileSystemDaoDir();
	}

}
