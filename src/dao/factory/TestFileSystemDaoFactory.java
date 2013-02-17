package dao.factory;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import dao.testfilesystem_dao.TestFileSystemDaoDir;
import dao.testfilesystem_dao.TestFileSystemDaoFile;
import dao.testfilesystem_dao.TestFileSystemDaoLink;
import filesystem.Dir;
import filesystem.testfilesystem.TestFileSystem;

public class TestFileSystemDaoFactory extends DaoFactory{

	private TestFileSystem fs = new TestFileSystem();
	
	private Dir root = fs.getRoot();
	
	private LinkDao linkdao;
	private FileDao filedao;
	private DirDao  dirdao;
	
	public TestFileSystemDaoFactory(){
		TestFileSystemDaoDir  dir  = new TestFileSystemDaoDir();
		TestFileSystemDaoFile file = new TestFileSystemDaoFile();
		TestFileSystemDaoLink link = new TestFileSystemDaoLink();
		
		dir.setDao(file, link);
		file.setDao(dir, link);
		link.setDao(dir, file);
		
		dir.setRoot(root);
		file.setRoot(root);
		link.setRoot(root);
		
		dir.setCurrentDir(root);
		file.setCurrentDir(root);
		link.setCurrentDir(root);
		
		dirdao = dir;
		filedao = file;
		linkdao = link;
	}
	
	public LinkDao getDaoLink(){
		return linkdao;
	}

	public FileDao getDaoFile(){
		return filedao;
	}

	public DirDao getDaoDir(){
		return dirdao;
	}

}
