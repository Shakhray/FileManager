package viewers;

public class Viewer {
	public void comandLine(){
		System.out.print("\nInfoSys> ");
	}
	public void programError(){
		System.out.println("program error");
	}
	public void badCommand() {
		System.out.println("Bad command. Enter command 'help' to open list of programs command.");
	}
}
