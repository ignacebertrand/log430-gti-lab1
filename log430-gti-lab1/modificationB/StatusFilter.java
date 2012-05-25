package modificationB;

import java.io.*;

public class StatusFilter extends Thread {
    
    PipedReader _Source = null;
    PipedWriter _InputPipe = null;
    PipedWriter _OutputPipe = null;
    boolean _Exclude = false;
    String _Status;
    
    public StatusFilter(String status, boolean exclude, PipedWriter inputPipe, PipedWriter outputPipe) {
        this._InputPipe = inputPipe;
        this._OutputPipe = outputPipe;
        
        this._Exclude = exclude;
        this._Status = status;
        
        try {
            this._Source = new PipedReader();
            this._Source.connect(this._InputPipe);
        } catch (Exception ex) {
            System.out.println("StatusFilter: An error occurred during initialization.");
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
                
                boolean isStatus = status.equals(this._Status);
                
                if ((!this._Exclude && isStatus) || (this._Exclude && !isStatus)) {
                    this._OutputPipe.write(line + "\n");
                    this._OutputPipe.flush();
                }
            }
            
            this.closeReader(reader);
        } catch (Exception ex) {
            System.out.println("StatusFilter: An error occurred reading from the input pipe.");
            System.out.println(ex.getMessage());
            
            if (reader != null) {
                this.closeReader(reader);
            }
        }
        
        try {
            this._OutputPipe.close();
        }
        catch (Exception ex) {
            System.out.println("StatusFilter: An error occurred closing the output pipe.");
            System.out.println(ex.getMessage());
        }
    }
    
    private void closeReader(Reader reader) {
        try {
            reader.close();
        }
        catch (Exception ex) {
            System.out.println("StatusFilter: An error occurred trying to close a reader.");
            System.out.println(ex.getMessage());
        }
    }
}
