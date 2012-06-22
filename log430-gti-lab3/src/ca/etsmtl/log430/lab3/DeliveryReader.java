package ca.etsmtl.log430.lab3;

/**
 * This class will read from the InputFile and instantiate the Delivery objects
 * in the system. It is assumed that the InputFile is in the local directory,
 * contains the scheduled deliveries and each line of input is read and expected
 * in the following format.
 * <pre>
 *		D164 1030 0130 1100 Notre-Dame West, Montreal, HC3 1K3
 *		|    |    |    |  
 *		|    |    |    Delivery address
 *      |    |    Estimated delivery duration (HHMM))
 *		|    Desired delivery time (HHMM)
 *		Delivery ID </pre>
 *
 * The deliveries.txt file has been provided as an example file.
 *
 * @author A.J. Lattanze, CMU
 * @version 1.5, 2012-Jun-19
 */

/* Modification Log
 *****************************************************************************
 * v1.5, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2012-May-31 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 *  
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS. 
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 *****************************************************************************/

public class DeliveryReader extends LineOfTextFileReader {

	/**
	 * List of deliveries.
	 */
    private DeliveryList listOfDeliveries = new DeliveryList();

    public DeliveryReader() {
        setListOfDeliveries(null);
    }

    public DeliveryReader(String InputFile) {
        setListOfDeliveries(getDeliveryList(InputFile));
    }

	/**
	 * Reads a line of text from the course file. The line of text is then
	 * passed to the ParseCourseText method where it is parsed and a Delivery
	 * object is returned. The Delivery object is then added to the list. When a
	 * null is read from the Delivery file the method terminates and returns the
	 * list to the caller. A list that points to null is an empty list.
	 * 
	 * @param inputFile
	 * @return The DeliveryList properly populated
	 */
    public DeliveryList getDeliveryList(String inputFile) {

        String text;
        boolean done;
        DeliveryList list = new DeliveryList();

        if (openFile(inputFile)) {

            done = false;

            while (!done) {

                try {

                    text = readLineOfText();

                    if (text == null) {

                        done = true;

                    } else {

                        list.addDelivery(parseText(text));

                    } // if 

                } // try

                catch (Exception Error) {

                    return (null);

                } // catch

            } // while		

        } else {

            return (null);

        } // if

        closeFile();

        return (list);

    } // GetDeliveryList

	/**
	 * Parse lines of text that are read from the text file containing delivery
	 * information in the above format. Note that this is a private method.
	 * 
	 * @param lineOfText
	 * @return A properly populated Delivery instance.
	 */
    private Delivery parseText(String lineOfText) {

        boolean done; // Loop terminator
        String token; // String token parsed from LineOfText
        int tokenCount; // Number of tokens parsed
        int frontIndex; // Front index of token to parse
        int backIndex; // Back index of token to parse

        Delivery newDelivery = new Delivery();

        tokenCount = 0;
        frontIndex = 0;
        backIndex = 0;
        done = false;

        while (!done) {

            backIndex = lineOfText.indexOf(' ', frontIndex);

            if (tokenCount < 3) {

                token = lineOfText.substring(frontIndex, backIndex);

            } else {

                token = lineOfText.substring(frontIndex);

            } // if 

            switch (tokenCount) {

                case 0 : // Delivery ID 
                    newDelivery.setDeliveryID(token);
                    frontIndex = backIndex + 1;
                    tokenCount++;
                    break;

                case 1 : // Desired delivery time
                    newDelivery.setDesiredDeliveryTime(token);
                    frontIndex = backIndex + 1;
                    tokenCount++;
                    break;

                case 2 : // Estimated delivery duration
                    newDelivery.setEstimatedDeliveryDuration(token);
                    frontIndex = backIndex + 1;
                    tokenCount++;
                    break;

                default : // Delivery address
                    newDelivery.setAddress(token);
                    done = true;
                    break;

            } // end switch

        } // end while

        return (newDelivery);

    } // ParseText

	public void setListOfDeliveries(DeliveryList listOfDeliveries) {
		this.listOfDeliveries = listOfDeliveries;
	}

	public DeliveryList getListOfDeliveries() {
		return listOfDeliveries;
	}

} // DeliveryReader