package com.videonetics;

import java.awt.GridLayout;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Hello world!
 *
 */
public class App {
    private JFrame frame;

    public App() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 5, 10));
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        frame.add(panel);
    }

    public static void main(String[] args) {
        // https://github.com/LWJGL/lwjgl3/blob/master/modules/samples/src/test/java/org/lwjgl/demo/system/jawt/JAWTDemo.java
        if (Platform.get() == Platform.MACOSX) {
            throw new UnsupportedOperationException("This demo cannot run on macOS.");
        }
        GLFWErrorCallback.createPrint().set();
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize glfw");
        }
        LWJGLCanvas canvas = new LWJGLCanvas();
        canvas.setSize(640, 480);

        JFrame frame = new JFrame("JAWT Demo");

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                canvas.destroy();
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE && e.getID() == KeyEvent.KEY_PRESSED) {
                frame.dispose();

                GLFW.glfwTerminate();
                Objects.requireNonNull(GLFW.glfwSetErrorCallback(null)).free();

                return true;
            }

            return false;
        });

        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(new JTextField(), BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        // new App();
    }
}
