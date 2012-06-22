/**
 * Assignment 3, ETS course LOG430 - Architecture logicielle. The purpose of
 * this assignment is to introduce the implicit invocation architectural
 * style.<br><br>
 * 
 * The application is a contrived route management system. It is a menu
 * driven system that allows the following options:
 * 
 * <pre>
 *    1) List drivers
 *    2) List deliveries
 *    3) List deliveries currently assigned to a driver today
 *    4) List drivers currently assigned to a driver today
 *    5) Assign a driver to a delivery
 *    X) Exit.
 * </pre>
 * 
 * Functionally, the system is identical to the previous lab.
 * 
 * Dynamically, the main program initializes the primary objects
 * and dispatches commands at the user's request. When the program is started,
 * the driver objects are initialized from a file (<tt>drivers.txt</tt>).
 * The format of this file is listed in the
 * {@link ca.etsmtl.log430.lab3.DriverReader DriverReader} class header.
 * The delivery objects are initialized from another file (<tt>deliveries.txt</tt>).
 * The format of this file is listed in the
 * {@link ca.etsmtl.log430.lab3.DeliveryReader DeliveryReader} class header.<br><br>
 * 
 * <b>Running the program:</b><br><br>
 * 
 * <blockquote>
 * <tt>java SystemInitialize</tt>
 * </blockquote>
 *
 * @author A.J. Lattanze - CMU - 1999
 * @author Roger Champagne - ETS - 2002-2012
 * @version 2012-Jun-19
 */
package ca.etsmtl.log430.lab3;