package dao.factory;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;

public abstract class DaoFactory {
	public abstract LinkDao getDataBaseDaoLink();
	public abstract FileDao getDataBaseDaoFile();
	public abstract DirDao getDataBaseDaoDir();
	
}
