package dao.factory;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import dao.database_dao.DataBaseDaoDir;
import dao.database_dao.DataBaseDaoFile;
import dao.database_dao.DataBaseDaoLink;

public class DataBaseDaoFactory extends DaoFactory{

	public LinkDao getDaoLink() {
		return new DataBaseDaoLink();
	}

	public FileDao getDaoFile() {
		return new DataBaseDaoFile();
	}

	public DirDao getDaoDir() {
		return new DataBaseDaoDir();
	}


}
