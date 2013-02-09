package dao.factory;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import dao.database_dao.DataBaseDaoDir;
import dao.database_dao.DataBaseDaoFile;
import dao.database_dao.DataBaseDaoLink;

public class DataBaseDaoFactory extends DaoFactory{

	public LinkDao getDataBaseDaoLink() {
		return new DataBaseDaoLink();
	}

	public FileDao getDataBaseDaoFile() {
		return new DataBaseDaoFile();
	}

	public DirDao getDataBaseDaoDir() {
		return new DataBaseDaoDir();
	}


}
