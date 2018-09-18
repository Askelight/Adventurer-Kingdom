/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

/**
 *
 * @author hour
 */
public class Keys {
    
    public static final int NUM_KEYS = 8;
    public static boolean[] keyState = new boolean[8];
    public static boolean[] prevKeyState = new boolean[8];
    public static int UP = 0;
    public static int LEFT = 1;
    public static int DOWN = 2;
    public static int RIGHT = 3;
    public static int SPACE = 4;
    public static int ENTER = 5;
    public static int ESCAPE = 6;
    public static int F1 = 7;

    public static void keySet(int i, boolean b) {
        if ((i == 38) || (i == 87)) {
          keyState[UP] = b;
        } else if ((i == 37) || (i == 65)) {
          keyState[LEFT] = b;
        } else if ((i == 40) || (i == 83)) {
          keyState[DOWN] = b;
        } else if ((i == 39) || (i == 68)) {
          keyState[RIGHT] = b;
        } else if (i == 32) {
          keyState[SPACE] = b;
        } else if (i == 10) {
          keyState[ENTER] = b;
        } else if (i == 27) {
          keyState[ESCAPE] = b;
        } else if (i == 112) {
          keyState[F1] = b;
        }
    }

    public static void update() {
        for (int i = 0; i < 8; i++) {
            prevKeyState[i] = keyState[i];
        }
    }

    public static boolean isPressed(int i) {
        return (keyState[i] != false) && (prevKeyState[i] == false);
    }

    public static boolean isDown(int i) {
        return keyState[i];
    }

    public static boolean anyKeyDown() {
        for (int i = 0; i < 8; i++) {
            if (keyState[i] != false) {
                return true;
            }
        }
        return false;
    }

    public static boolean anyKeyPress() {
        for (int i = 0; i < 8; i++) {
            if ((keyState[i] != false) && (prevKeyState[i] == false)) {
                return true;
            }
        }
        return false;
    }
}
