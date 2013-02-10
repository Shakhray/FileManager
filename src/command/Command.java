package command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;

import filesystem.Node;


public abstract class Command {
	protected LinkDao linkdao;
	protected FileDao filedao;
	protected DirDao dirdao;
	private Node backup;
	
	public Command(DirDao dirdao){
		this.dirdao = dirdao;
		backup = dirdao.getRoot();
	}
	
	public abstract void execute() throws OperationNotSupportedException, NodeAlreadyExistsException;

	public void undo() throws IOException {
		dirdao.undo(backup);
	}
}
