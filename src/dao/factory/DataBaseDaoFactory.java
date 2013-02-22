package dao.factory;


import dao.NodeDao;
import dao.database_dao.DataBaseDaoNode;


public class DataBaseDaoFactory extends DaoFactory{

	@Override
	public NodeDao<?> getNodeDao() {
		return new DataBaseDaoNode();
	}




}
