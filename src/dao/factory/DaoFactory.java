package dao.factory;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;

public abstract class DaoFactory {
	public abstract LinkDao getDaoLink();
	public abstract FileDao getDaoFile();
	public abstract DirDao getDaoDir();
	
}
