/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prototype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kota
 */
public class Keyboard implements KeyListener {

    private static final int KEY_COUNT = 256;

    private boolean[] keys;

    /**
     * Creates a new Keyboard object
     */

    public Keyboard() {
        keys = new boolean[ KEY_COUNT ];
    }

    /**
     * Check the state of a single key
     * @param keyNumber The key code (out of 256)
     * @return boolean value, true if the key is down, false if it is up
     */
    public boolean checkKey(int keyNumber) {
        return keys[keyNumber];
    }

    private void keyAction(KeyEvent ke, boolean down) {
        int keyNumber = ke.getKeyCode();
        if(keyNumber >= 0 && keyNumber < KEY_COUNT)
            keys[keyNumber] = down;
    }

    /**
     * Used to change the keystate to true
     * @param ke Keyboard event
     */
    public void keyTyped(KeyEvent ke) {
        keyAction(ke, true);
    }

    /**
     * Used to change the keystate to true
     * @param ke Keyboard event
     */
    public void keyPressed(KeyEvent ke) {
        keyAction(ke, true);
    }

    /**
     * Used to change the keystate to false
     * @param ke Keyboard event
     */
    public void keyReleased(KeyEvent ke) {
        keyAction(ke, false);
    }

}
