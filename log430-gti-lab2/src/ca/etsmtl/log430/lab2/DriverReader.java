package ca.etsmtl.log430.lab2;

/**
 * This class reads from the InputFile and instantiates the Driver objects in
 * the system. It is assumed that the InputFile is in the local directory,
 * contains the active drivers, and each line of input is read and expected in
 * the following format: a field oriented and space-separated text file that
 * lists all the drivers. The fields are as follows:
 * <pre>
 *     DRV001 Bleau Joseph PRF D001:0200 D023:0130 D067:0030
 *     |      |     |      |   |
 *     |      |     |      |   Previous deliveries this week (ID-duration pairs)
 *     |      |     |      Type of driver(JNR=junior, SNR=senior)
 *     |      |     Driver's First Name
 *     |      Driver's Last Name 
 *     Driver ID</pre>
 * 
 * The deliveries.txt file has been provided as an example.
 *
 * @author A.J. Lattanze, CMU 
 * @version 1.5, 2012-May-31
 */

/*
 * Modification Log
 * ***********************************************************************
 * v1.5, R. Champagne, 2012-May-31 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.3, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.2, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.1, 2001-Jan-25, G.A. Lewis - Bug in ParseStudentText. There was a bug in
 * that was causing it not to read the last course into the list of courses
 * taken.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ***********************************************************************
 */

public class DriverReader extends LineOfTextFileReader {

	/**
	 * The list of drivers.
	 */
	private DriverList listOfDrivers = new DriverList();

	public DriverReader() {
		listOfDrivers = null;
	}

	public DriverReader(String InputFile) {
		listOfDrivers = readDriverListFromFile(InputFile);
	}

	/**
	 * Reads a line of text. The line of text is passed to the parseTeacherText
	 * method where it is parsed and a Driver object is returned. The Driver
	 * object is then added to the list. When a null is read from the Driver
	 * file the method terminates and returns the list to the caller. A list
	 * that points to null is an empty list.
	 * 
	 * @param inputFile
	 * @return The list of teachers
	 */
	public DriverList readDriverListFromFile(String inputFile) {

		String text; // Line of text from the file
		boolean done; // End of the file - stop processing

		// New teacher list object - this will contain all of the teachers in
		// the file

		DriverList listObject = new DriverList();

		if (openFile(inputFile)) {

			done = false;

			while (!done) {

				try {

					text = readLineOfText();

					if (text == null) {

						done = true;

					} else {

						listObject.addDriver(parseText(text));

					} // if

				} // try

				catch (Exception Error) {

					return (null);

				} // catch

			} // while

		} else {

			return (null);

		} // if

		return (listObject);

	} // readTeacherListFromFile

	public DriverList getListOfDrivers() {
		return listOfDrivers;
	}

	public void setListOfDrivers(DriverList listOfDrivers) {
		this.listOfDrivers = listOfDrivers;
	}

	/**
	 * Parse lines of text that are read from the text file containing driver
	 * information in the above format.
	 * 
	 * @param lineOfText
	 * @return populated Driver object
	 */
	private Driver parseText(String lineOfText) {

		boolean done = false; // Indicates the end of parsing
		String token; // String token parsed from the line of text
		int tokenCount = 0; // Number of tokens parsed
		int frontIndex = 0; // Front index or character position
		int backIndex = 0; // Rear index or character position
		int colonIndex = 0; // index of colon in delevery:duration substring

		// Create a teacher object to record all of the info parsed from
		// the line of text

		Driver driver = new Driver();

		while (!done) {

			backIndex = lineOfText.indexOf(' ', frontIndex);

			if (backIndex == -1) {

				done = true;
				token = lineOfText.substring(frontIndex);

			} else {

				token = lineOfText.substring(frontIndex, backIndex);
			}

			switch (tokenCount) {

			case 0: // Driver ID
				driver.setDriverID(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			case 1: // Driver's last name
				driver.setLastName(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			case 2: // Driver's First name
				driver.setFirstName(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			case 3: // Driver type (e.g. SNR, JNR)
				driver.setType(token);
				frontIndex = backIndex + 1;
				tokenCount++;
				break;

			default:
				/* This is where the previous deliveries are added to the driver's
				 * previous deliveries made list within the driver object.
				 * This needs to be parsed at ":" to separate ID and estimated duration.
				 */
				colonIndex = token.indexOf(':');
				if (colonIndex != -1) {
					String deliveryID = token.substring(0, colonIndex-1);
					String estimatedDuration = token.substring(colonIndex+1);
					driver.getDeliveriesMadeList().addDelivery(new Delivery(deliveryID, estimatedDuration));
					frontIndex = backIndex + 1;
					break;
				} else {
					// Error!
				}

			} // end switch

		} // end while

		return (driver);

	} // parseTeacherText

} // DriverReader