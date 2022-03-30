import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatWorkerThread extends Thread
{
    private Socket theClientSocket;
    private PrintStream clientOutput;
    private Scanner clientInput;

    public ChatWorkerThread(Socket theClientSocket)
    {
        try 
        {
            System.out.println("Connection Established...");
            this.theClientSocket = theClientSocket;
            this.clientOutput = new PrintStream(this.theClientSocket.getOutputStream());    
            this.clientInput = new Scanner(this.theClientSocket.getInputStream());
        } 
        catch (Exception e) 
        {
            System.err.println("Bad things happened in thread!!!!!");
            e.printStackTrace();
        }
        
    }

    public void run()
    {
        //this is what the thread does
        Singleton x = Singleton.createSingleton();
        while(true)
        {
            this.clientOutput.println(x.messages);
            String message = clientInput.nextLine();
            x.addMessage(message);
            System.out.println("read: " + message);
        }
        
    }
}
class Singleton {
    private static Singleton single = null;
    public String s = "Message: ";
    public String messages = "";
    public String temp = "";

    public static Singleton createSingleton()
    {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }

    private Singleton()
    {
        s = messages;
    }

    public void addMessage (String y)
    {
        messages = messages + "Message: " + y + " ";
        temp = messages;
    }

    public boolean checkIfChanged()
    {
        if(temp == s)
        {
            return true;
        }
        return false;
    }


}

