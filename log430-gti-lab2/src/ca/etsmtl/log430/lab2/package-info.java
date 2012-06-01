/**
 * Assignment 2, ETS course LOG430 - Architecture logicielle. The purpose of
 * this assignment is to introduce object-oriented implementations in the
 * context of layered architectures.<br><br>
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
 * Structurally, the system is roughly divided into three layers:
 * 
 * <blockquote>
 *  1) Presentation Layer<br>
 *  2) Business Rule Layer<br>
 *  3) File I/O Layer<br>
 * </blockquote>
 * 
 * Dynamically, the main program initializes the primary objects
 * and dispatches commands at the user's request. When the program is started,
 * the driver objects are initialized from a file (<tt>drivers.txt</tt> is an
 * example). The format of this file is listed in the
 * {@link ca.etsmtl.log430.lab2.DriverReader DriverReader} class header.
 * The delivery objects are initialized from another file (<tt>deliveries.txt</tt> is
 * an example). The format of this file is listed in the
 * {@link ca.etsmtl.log430.lab2.DeliveryReader DeliveryReader} class header.<br><br>
 * 
 * <b>Running the program:</b><br><br>
 * 
 * <blockquote>
 * <tt>java DriverAssignment &lt;DeliveryDataFile&gt; &lt;DriverDataFile&gt;</tt><br><br>
 * 
 * <b>With the supplied example files:</b><br><br>
 * 
 * <tt>java DriverAssignment deliveries.txt drivers.txt</tt><br><br>
 * </blockquote>
 *
 * @author A.J. Lattanze - CMU - 1999
 * @author Roger Champagne - ETS - 2002-2012
 * @version 2012-May-31
 */
package ca.etsmtl.log430.lab2;