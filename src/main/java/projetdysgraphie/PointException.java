/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetdysgraphie;

/**
 *
 * @author Utilisateur
 */
public class PointException extends Exception {

    /**
     * Creates a new instance of <code>PointException</code> without detail
     * message.
     */
    public PointException() {
    }

    /**
     * Constructs an instance of <code>PointException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PointException(String msg) {
        super(msg);
    }
}
