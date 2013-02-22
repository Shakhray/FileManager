package dao.testfilesystem_dao;

import dao.NodeDao;
import dao.factory.TestFileSystemInMemory;
import filesystem.Dir;
import filesystem.Node;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 21.02.13
 * Time: 0:13
 * To change this template use File | Settings | File Templates.
 */
public class TestGenericDao<PK> implements NodeDao<PK>{

    private Dir root;



    @Override
    public void update(Node node) {
        TestFileSystemInMemory.updateNode(node);
    }

    @Override
    public PK create(Node node) {
       return null;
    }

    @Override
    public void delete(Node node) {

    }

    @Override
    public void setRoot(Dir root) {
        this.root = root;
    }

	@Override
	public Dir getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo(ArrayList<Node> backup) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void copy(Node copynode, ArrayList<String> copyto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy(Node copynode, Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(Node replacenode, ArrayList<String> replaceto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(Node replacenode, Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentDir(Dir currentDir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dir getCurrentDir() {
		// TODO Auto-generated method stub
		return null;
	}
}
