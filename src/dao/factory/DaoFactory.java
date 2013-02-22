package dao.factory;

import dao.NodeDao;

public abstract class DaoFactory {
	public abstract NodeDao<?> getNodeDao();
	
}
