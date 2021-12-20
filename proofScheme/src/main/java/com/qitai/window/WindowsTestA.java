package com.qitai.window;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32Util;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;

import java.awt.*;

public class WindowsTestA {
    public interface User32 extends StdCallLibrary {

        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);
        int GetWindowTextA(WinDef.HWND hWnd, byte[] lpString, int nMaxCount);
        boolean MoveWindow(WinDef.HWND hWnd, int X, int Y, int nWidth, int nHeight, boolean bRepaint);
    }
    public static void main(String[] args) {
        final User32 user32 = User32.INSTANCE;
        user32.EnumWindows(new WinUser.WNDENUMPROC() {
            int count = 0;
            @Override
            public boolean callback(WinDef.HWND hWnd, Pointer arg1) {
                byte[] windowText = new byte[512];
                user32.GetWindowTextA(hWnd, windowText, 512);
                System.setProperty("jna.encoding","GBK");
                String wText = Native.toString(windowText);
                if (wText.isEmpty()) {
                    return true;
                }
                if (wText.equals("外网评价-好差评 - Google Chrome")){
                    System.out.println("Found window with text " + hWnd + ", total " + ++count
                            + " Text: " + wText);
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    GraphicsDevice[] gd = ge.getScreenDevices();
                    Pointer pointer = hWnd.getPointer();
                    boolean flag = user32.MoveWindow(hWnd,gd[1].getDefaultConfiguration().getBounds().x, gd[1].getDefaultConfiguration().getBounds().y,gd[1].getDefaultConfiguration().getBounds().width,gd[1].getDefaultConfiguration().getBounds().height,false);
                    //showOnScreen2(1,window);
                    System.out.println(flag);
                }
                return true;
            }
        }, null);

    }
    public static void showOnScreen2(int screen, WinDef.HWND hwnd) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        /*if (screen > -1 && screen < gd.length) {
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());
        } else if (gd.length > 0) {
            frame.setLocation(gd[1].getDefaultConfiguration().getBounds().x, frame.getY());
        } else {
            throw new RuntimeException("No Screens Found");
        }*/
    }
}