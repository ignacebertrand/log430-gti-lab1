package modificationA;

import java.io.*;

public class TrimFilter extends Thread {
    
    PipedReader _Source;
    PipedWriter _InputPipe;
    PipedWriter _OutputPipe;
    
    public TrimFilter(PipedWriter inputPipe, PipedWriter outputPipe) {
        this._InputPipe = inputPipe;
        this._OutputPipe = outputPipe;
        
        try {
            this._Source = new PipedReader();
            this._Source.connect(this._InputPipe);
        } catch (Exception ex) {
            System.out.println("TrimFilter: An error occurred during initialization.");
            System.out.println(ex.getMessage());
        }
    }
    
    public void run() {
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(this._Source);
            String line;
            while ((line = reader.readLine()) != null) {
                String status = line.substring(26, 29);
                String severity = line.substring(22, 25);
                String type = line.substring(5, 8);
                String noTicket = line.substring(0, 4);
                
                this._OutputPipe.write(status + " " + severity + " " + type + " " + noTicket + "\n");
                this._OutputPipe.flush();
            }
            
            this.closeReader(reader);
        } catch (Exception ex) {
            System.out.println("TrimFilter: An error occurred reading from the input pipe.");
            System.out.println(ex.getMessage());
            
            if (reader != null) {
                this.closeReader(reader);
            }
        }
        
        try {
            this._OutputPipe.close();
        }
        catch (Exception ex) {
            System.out.println("TrimFilter: An error occurred closing the output pipe.");
            System.out.println(ex.getMessage());
        }
    }
    
    private void closeReader(Reader reader) {
        try {
            reader.close();
        }
        catch (Exception ex) {
            System.out.println("TrimFilter: An error occurred trying to close a reader.");
            System.out.println(ex.getMessage());
        }
    }
}
