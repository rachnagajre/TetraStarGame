package com.design.pattern.tetrastar.util;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *   @author Akshata, Rachna and  Shweta. 
 *   Utility to create a dialog popup message
 */
public class CreateMessageUtility {
    
    public static void createMsg(String msg) {
    	String message = msg;
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, null, null);
        JDialog dialog = optionPane.createDialog(null, "Scenario");
        dialog.setLocation(1050, 450);
        dialog.setVisible(true);
    }
    
}
