package ca.etsmtl.log430.lab2;

/**
 * Main class for assignment 2 for LOG430, Architecture logicielle.
 * 
 * <pre>
 * <b>Pseudo Code:</b>
 * 
 *   Instantiate lists
 *   do until done
 *     Present Menu
 *     if user choice = 1 then list drivers
 *     if user choice = 2 then list deliveries
 *     if user choice = 3 then
 *        list driver
 *        ask user to select a driver (by ID)
 *        list deliveries assigned to that driver today
 *     endif
 *     if user choice = 4 then
 *        list deliveries
 *        ask user to select a delivery (by ID)
 *        list driver assigned to that delivery today
 *     endif
 *     if user choice = 5 then
 *        list drivers
 *        ask user to select a driver(by ID)
 *        list deliveries
 *        ask user to select a delivery (by ID)
 *        assign delivery to driver (and vice versa)
 *     endif
 *     if user choice = x then you are done
 *   end do
 * </pre>
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.4, 2012-May-31
 */

/*
 * Modification Log
 * **************************************************************************
 * v1.4, R. Champagne, 2012-May-31 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * **************************************************************************
 */

public class DriverAssignment {

	public static void main(String argv[]) {

		if (argv.length != 2) {
			System.out.println("\n\nIncorrect number of input parameters -"
					+ " correct usage:");
			System.out.println("\njava DriverAssignment <delivery file name>"
					+ " <driver file name>");
		} else {

			// Declarations:

			boolean done; // Loop invariant
			char userChoice; // User's menu choice
			Delivery myCourse = null; // A course object
			Driver myTeacher = null; // A teacher object

			// Instantiates a menu object
			Menus menu = new Menus();

			// Instantiates a display object
			Displays display = new Displays();

			/*
			 * The following instantiations create a list of courses and
			 * teachers. The pathname for the file containing course information
			 * is passed to the main program on the command line as the first
			 * argument (argv[0]). The pathname for the file containing teacher
			 * information is passed to the main program on the command line as
			 * the second argument (argv[1]). An example teacher file and course
			 * file is provided as enseignantsLOG.txt and coursLOG.txt
			 */

			DeliveryReader myCourseList = new DeliveryReader(argv[0]);
			DriverReader myTeacherList = new DriverReader(argv[1]);

			if ((myCourseList.getListOfDeliveries() == null)
					|| (myTeacherList.getListOfDrivers() == null)) {
				System.out
						.println("\n\n *** The course list and/or the teacher"
								+ " list was not initialized ***");
				done = true;
			} else {
				done = false;
			} // if

			while (!done) {

				userChoice = menu.mainMenu();
				switch (userChoice) {

				case '1':

					display.displayDriverList(myTeacherList
							.getListOfDrivers());
					break;

				case '2':

					display.displayDeliveryList(myCourseList.getListOfDeliveries());
					break;

				case '3':

					display.displayDriverList(myTeacherList
							.getListOfDrivers());
					myTeacher = menu.pickDriver(myTeacherList
							.getListOfDrivers());
					if (myTeacher != null) {
						display.displayDeliveriesAssignedToDriver(myTeacher);
					} // if
					break;

				case '4':

					display.displayDeliveryList(myCourseList.getListOfDeliveries());
					myCourse = menu.pickDelivery(myCourseList.getListOfDeliveries());

					if (myCourse != null) {
						display.displayDriversAssignedToDelivery(myCourse);
					} // if
					break;

				case '5':

					display.displayDriverList(myTeacherList
							.getListOfDrivers());
					myTeacher = menu.pickDriver(myTeacherList
							.getListOfDrivers());

					if (myTeacher != null) {
						display.displayDeliveryList(myCourseList
								.getListOfDeliveries());
						myCourse = menu.pickDelivery(myCourseList
								.getListOfDeliveries());
						if (myCourse != null) {
							myCourse.assignDriver(myTeacher);
							myTeacher.assignDelivery(myCourse);
						} // if
					} // if

					break;

				case 'X':

				case 'x':
					done = true;
				} // switch
			} // while
		} // if
	} // main
} // Class