package modificationA;

import java.io.*;

public class SplitterFilter extends Thread {
    
    private PipedReader _Source;
    private PipedWriter _InputPipe;
    private PipedWriter _OutputPipe1;
    private PipedWriter _OutputPipe2;
    
    public SplitterFilter(PipedWriter inputPipe, PipedWriter outputPipe1, PipedWriter outputPipe2) {
        this._InputPipe = inputPipe;
        this._OutputPipe1 = outputPipe1;
        this._OutputPipe2 = outputPipe2;
        
        try {
            this._Source = new PipedReader();
            this._Source.connect(this._InputPipe);
        } catch (Exception ex) {
            System.out.println("SplitterFilter: An error occurred during initialization.");
            System.out.println(ex.getMessage());
        }
    }
    
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(this._Source);
            String line;
            while ((line = reader.readLine()) != null) {
                
                this._OutputPipe1.write(line + "\n");
                this._OutputPipe1.flush();
                
                this._OutputPipe2.write(line + "\n");
                this._OutputPipe2.flush();
            }
            
            this.closeReader(reader);
        } catch (Exception ex) {
            System.out.println("SplitterFilter: An error occurred reading from the input pipe.");
            System.out.println(ex.getMessage());
            
            if (reader != null) {
                this.closeReader(reader);
            }
        }
        
        try {
            this._OutputPipe1.close();
            this._OutputPipe2.close();
        }
        catch (Exception ex) {
            System.out.println("SplitterFilter: An error occurred closing the output pipe.");
            System.out.println(ex.getMessage());
        }
    }
    
    private void closeReader(Reader reader) {
        try {
            reader.close();
        }
        catch (Exception ex) {
            System.out.println("SplitterFilter: An error occurred trying to close a reader.");
            System.out.println(ex.getMessage());
        }
    }
}
