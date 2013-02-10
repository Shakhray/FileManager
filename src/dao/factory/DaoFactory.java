package dao.factory;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;

public abstract class DaoFactory {
	public abstract LinkDao getDataBaseDaoLink() throws NodeAlreadyExistsException, OperationNotSupportedException;
	public abstract FileDao getDataBaseDaoFile() throws NodeAlreadyExistsException, OperationNotSupportedException;
	public abstract DirDao getDataBaseDaoDir() throws NodeAlreadyExistsException, OperationNotSupportedException;
	
}
