package dao.factory;

import dao.NodeDao;
import dao.testfilesystem_dao.TestFileSystemDaoNode;

public class TestFileSystemDaoFactory extends DaoFactory{
	
	private NodeDao nodeDao;
	
	public TestFileSystemDaoFactory(){
		nodeDao  = new TestFileSystemDaoNode();
	}

	@Override
	public NodeDao<?> getNodeDao() {
		
		return nodeDao;
	}

}
