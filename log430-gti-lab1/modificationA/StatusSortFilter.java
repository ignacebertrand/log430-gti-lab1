package modificationA;

import java.io.*;
import java.util.*;

public class StatusSortFilter extends Thread {
    
    PipedReader _Source = null;
    PipedWriter _InputPipe = null;
    PipedWriter _OutputPipe = null;
    
    public StatusSortFilter(PipedWriter inputPipe, PipedWriter outputPipe) {
        this._InputPipe = inputPipe;
        this._OutputPipe = outputPipe;
        
        try {
            this._Source = new PipedReader();
            this._Source.connect(this._InputPipe);
        } catch (Exception ex) {
            System.out.println("StatusSortFilter: An error occurred during initialization.");
            System.out.println(ex.getMessage());
        }
    }
    
    public void run() {
        ArrayList<String> lines = new ArrayList();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(this._Source);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            
            this.closeReader(reader);
        } catch (Exception ex) {
            System.out.println("StatusSortFilter: An error occurred reading from the input pipe.");
            System.out.println(ex.getMessage());
            
            if (reader != null) {
                this.closeReader(reader);
            }
        }
        
        StatusComparator comparator = this.new StatusComparator();
        Collections.sort(lines, comparator);
        
        try {
            for (int i = 0; i < lines.size(); i++) {
                this._OutputPipe.write(lines.get(i) + "\n");
                this._OutputPipe.flush();
            }
        } catch (Exception ex) {
            System.out.println("StatusSortFilter: An error occurred writing to the output pipe.");
            System.out.println(ex.getMessage());
        }
        
        try {
            this._OutputPipe.close();
        }
        catch (Exception ex) {
            System.out.println("StatusSortFilter: An error occurred closing the output pipe.");
            System.out.println(ex.getMessage());
        }
    }
    
    private void closeReader(Reader reader) {
        try {
            reader.close();
        }
        catch (Exception ex) {
            System.out.println("StatusSortFilter: An error occurred trying to close a reader.");
            System.out.println(ex.getMessage());
        }
    }
    
    class StatusComparator implements Comparator<String> {
        
        private ArrayList<String> _StatusOrder;
        
        public StatusComparator() {
            this._StatusOrder = new ArrayList<String>();
            this._StatusOrder.add(0, "ASS");
            this._StatusOrder.add(1, "NOU");
            this._StatusOrder.add(2, "RES");
            this._StatusOrder.add(3, "ROU");
        }
        
        public int compare(String o1, String o2) {
            
            String status1 = o1.trim().substring(26, 29);
            String status2 = o2.trim().substring(26, 29);
            
            int pos1 = this._StatusOrder.indexOf(status1);
            int pos2 = this._StatusOrder.indexOf(status2);
            
            int order = -1;
            if (pos1 == pos2) {
                order = 0;
            } else if (pos1 > pos2) {
                order = 1;
            }
            return order;
        }
    }
}
