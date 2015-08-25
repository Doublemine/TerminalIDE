/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.terminal.ide.external;

import com.sun.tools.javac.Main;

public class javac {
    public static void main(String[] zArgs) {
        Main mm = new Main();
        Main.compile(zArgs);
    }
}
