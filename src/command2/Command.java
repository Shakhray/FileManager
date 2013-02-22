package command2;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 20.02.13
 * Time: 23:55
 * To change this template use File | Settings | File Templates.
 */
public interface Command {
    
    void execute();
    public void undo();
}
