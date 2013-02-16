package command;

import java.util.ArrayList;

import dao.DirDao;
import filesystem.Dir;

public class Copy extends Command{

	private Dir copydir;
	private ArrayList<String> copyto = new ArrayList<String>();
	
	public Copy(DirDao dirdao, Dir currentdir, Dir copydir, ArrayList<String> copyto){
		super(dirdao, currentdir);
		this.copydir = copydir;
		this.copyto = copyto;
	}	
	
	public void execute(){
		super.dirdao.copy(copydir, copyto);
	}
	public void undo(){
		super.undo();
	}
}
