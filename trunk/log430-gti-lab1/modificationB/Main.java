package modificationB;

import java.io.PipedWriter;

/**
 * ************************************************************************************
 ** Class name: Main Original author: A.J. Lattanze, CMU Date: 12/3/99 *
 * Version 1.2 * * Adapted by R. Champagne, Ecole de technologie superieure
 * 2002-May-08, * 2011-Jan-12, 2012-Jan-11. *
 * **************************************************************************************
 * * Purpose: Assignment 1 for LOG430, Architecture logicielle. This assignment
 * is * designed to illustrate a pipe and filter architecture. For the
 * instructions, * refer to the assignment write-up. * * Abstract: This class
 * contains the main method for assignment 1. The * assignment 1 program
 * consists of these files: * * 1) Main: instantiates all filters and pipes,
 * starts all filters. * 2) FileReaderFilter: reads input file and sends each
 * line to its output pipe. * 3) TypeFilter: separates the input stream in two
 * languages (FRA, EN) and writes * lines to the appropriate output pipe. * 4)
 * SeverityFilter: determines if an entry contains a particular severity
 * specified * at instantiation. If so, sends the whole line to its output pipe.
 * * 5) MergeFilter: accepts inputs from 2 input pipes and writes them to its
 * output pipe. * 6) FileWriterFilter: sends its input stream to a text file. *
 * * Pseudo Code: * * instantiate all filters and pipes * start FileReaderFilter
 * * start TypeFilter * start SeverityFilter for CRI * start SeverityFilter for
 * MAJ * start MergeFilter * start FileWriterFilter * * Running the program * *
 * java Main IputFile OutputFile > DebugFile * * Main - Program name * InputFile
 * - Text input file (see comments below) * OutputFile - Text output file with
 * students * DebugFile - Optional file to direct debug statements * *
 * Modification Log
 * *************************************************************************************
 * * 
 *************************************************************************************
 */
public class Main {

    public static void main(String argv[]) {
        // Lets make sure that input and output files are provided on the
        // command line
        
//        argv = new String[3];
//        argv[0] = "Input.txt";
//        argv[1] = "Output1.txt";
//        argv[2] = "Output2.txt";
        
        if (argv.length != 3) {

            System.out.println("\n\nNombre incorrect de parametres d'entree. Utilisation:");
            System.out.println("\njava Main <fichier d'entree> <fichier de sortie> <2e fichier de sortie>");
        } else {
            PipedWriter filePipe = new PipedWriter();
            FileReaderFilter readerFilter = new FileReaderFilter(argv[0], filePipe);
            
            PipedWriter typeDefPipe = new PipedWriter();
            PipedWriter typeAmePipe = new PipedWriter();
            TypeFilter typeFilter = new TypeFilter(filePipe, typeDefPipe, typeAmePipe);
            
            PipedWriter typeDefSeverityPipe = new PipedWriter();
            SeverityFilter typeDefSeverityFilter = new SeverityFilter("CRI;MAJ", false, typeDefPipe, typeDefSeverityPipe);
            
            PipedWriter typeAmeSeverityPipe = new PipedWriter();
            SeverityFilter typeAmeSeverityFilter = new SeverityFilter("MAJ;NOR", false, typeAmePipe, typeAmeSeverityPipe);
            
            PipedWriter typeDefStatusPipe = new PipedWriter();
            StatusFilter typeDefStatusFilter = new StatusFilter("NOU", false, typeDefSeverityPipe, typeDefStatusPipe);
            
            PipedWriter typeAmeStatusPipe = new PipedWriter();
            StatusFilter typeAmeStatusFilter = new StatusFilter("RES", true, typeAmeSeverityPipe, typeAmeStatusPipe);
            
            PipedWriter sortedPipe1 = new PipedWriter();
            StatusSortFilter sortFilter1 = new StatusSortFilter(typeDefStatusPipe, sortedPipe1);
            
            PipedWriter sortedPipe2 = new PipedWriter();
            StatusSortFilter sortFilter2 = new StatusSortFilter(typeAmeStatusPipe, sortedPipe2);
            
            PipedWriter trimmedPipe1 = new PipedWriter();
            TrimFilter trimFilter1 = new TrimFilter(sortedPipe1, trimmedPipe1);
            
            PipedWriter trimmedPipe2 = new PipedWriter();
            TrimFilter trimFilter2 = new TrimFilter(sortedPipe2, trimmedPipe2);
            
            FileWriterFilter writerFilter1 = new FileWriterFilter(argv[1], trimmedPipe1);
            FileWriterFilter writerFilter2 = new FileWriterFilter(argv[2], trimmedPipe2);
            
            readerFilter.start();
            
            typeFilter.start();
            
            typeDefSeverityFilter.start();
            typeAmeSeverityFilter.start();
            
            typeDefStatusFilter.start();
            typeAmeStatusFilter.start();
            
            sortFilter1.start();
            sortFilter2.start();
            
            trimFilter1.start();
            trimFilter2.start();
            
            writerFilter1.start();
            writerFilter2.start();
        }
    }
}