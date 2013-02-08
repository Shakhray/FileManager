package dao.factory;

import dao.DataBaseDaoDir;
import dao.DataBaseDaoFile;
import dao.DataBaseDaoLink;
import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;

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
