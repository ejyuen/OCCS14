/*
 * PongLegacy.java
 */

package prototype;

/**
 * <a href="http://java.sun.com/docs/books/tutorial/deployment/jar/appman.html">
 * Main-Class</a> of PongLegacy project.
 *
 * @author 2009-2010 WHS 
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class PongLegacy {

    /**
     * PongLegacy main method. PongLegacy is
     * <a href="http://java.sun.com/docs/books/tutorial/deployment/jar/appman.html">
	 * Main-Class</a> of project.
     * @param args command-line arguments
     */
    public static void main(String... args) {
        for (String string : args) System.out.print(string + " ");
        MainMenu.main(args);
    }
}
