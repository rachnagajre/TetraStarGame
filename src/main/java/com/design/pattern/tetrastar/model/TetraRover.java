/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;
/**
 *   @author Akshata, Rachna and Shweta. 
 */

import com.design.pattern.tetrastar.constants.TetraConstants;
import com.design.pattern.tetrastar.enums.Direction;
import com.design.pattern.tetrastar.enums.EncryptionStrategy;
import com.design.pattern.tetrastar.enums.PeopleType;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

 
public class TetraRover extends TetraPeople {

    private Image img, image = null;

    public TetraRover() {
        peopleObservable = new TetraPeopleObservable();
    }

    @Override
    public void makeMove(Direction direction, JButton[][] gridOfLocations) {
        if (direction != null) {
            switch (direction) {
                case NORTH:
                    makeNorthMove(gridOfLocations);
                    break;
                case SOUTH:
                    makeSouthMove(gridOfLocations);
                    break;
                case EAST:
                    makeEastMove(gridOfLocations);
                    break;
                case WEST:
                    makeWestMove(gridOfLocations);
                    break;
            }
        }
    }

    private void makeNorthMove(JButton[][] gridOfLocations) {
        int newRow = currentLocation.getRow() - 1;
        if (newRow < TetraConstants.minRows) {
            String s = "Cannot move North. Out of the Grid";
            CreateMessageUtility.createMsg(s);
        } else {
            newLocation = new TFaceGrid(newRow, currentLocation.getColumn());
            checkLocation(gridOfLocations, newLocation);
        }
    }

    private void makeSouthMove(JButton[][] gridOfLocations) {
        int newRow = currentLocation.getRow() + 1;
        if (newRow > TetraConstants.maxRows) {
            String s = "Cannot move South. Out of the Grid";
            CreateMessageUtility.createMsg(s);
        } else {
            newLocation = new TFaceGrid(newRow, currentLocation.getColumn());
            checkLocation(gridOfLocations, newLocation);
        }
    }

    private void makeEastMove(JButton[][] gridOfLocations) {
        int newCol = currentLocation.getColumn() + 1;
        if (newCol > TetraConstants.maxColumns) {
            String s = "Cannot move East. Out of the Grid";
            CreateMessageUtility.createMsg(s);
        } else {
            newLocation = new TFaceGrid(currentLocation.getRow(), newCol);
            checkLocation(gridOfLocations, newLocation);
        }
    }

    private void makeWestMove(JButton[][] gridOfLocations) {
        int newCol = currentLocation.getColumn() - 1;
        if (newCol < TetraConstants.minColumns) {
            String s = "Cannot move West. Out of the Grid";
            CreateMessageUtility.createMsg(s);
        } else {
            newLocation = new TFaceGrid(currentLocation.getRow(), newCol);
            checkLocation(gridOfLocations, newLocation);
        }
    }

    private void setIcons(JButton[][] gridOfLocations) {
        try {
            image = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
        } catch (IOException e) {
            System.err.println("Error occurred while reading image : " + e.getMessage());
        }
        gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(new ImageIcon(image));
        gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setDisabledIcon(new ImageIcon(image));
        gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setText("");

        gridOfLocations[newLocation.getRow()][newLocation.getColumn()].setIcon(new ImageIcon(img));
        gridOfLocations[newLocation.getRow()][newLocation.getColumn()].setDisabledIcon(new ImageIcon(img));
        gridOfLocations[newLocation.getRow()][newLocation.getColumn()].setText("ROVER");

        currentLocation = newLocation;
    }

    public void checkLocation(JButton[][] gridOfLocations, TFaceGrid currentLocation) {
        int row = currentLocation.getRow();
        int col = currentLocation.getColumn();

        boolean empty = false;

        if (gridOfLocations[row][col].getText().equals("")) {
            try {
                img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/tRover.jpg"));
            } catch (IOException e) {
                System.err.println("Error occurred " + e.getMessage());
            }
            setIcons(gridOfLocations);
        }

        if (gridOfLocations[row][col].getText().equals("HEROBASE")) {
            String s = "Cannot move to the herobase";
            CreateMessageUtility.createMsg(s);
        }
        if (gridOfLocations[row][col].getText().equals("VADERBASE")) {
            String s = "Cannot move to vaderbase";
            CreateMessageUtility.createMsg(s);
        }
        if (gridOfLocations[row][col].getText().equals("HERO")) {
            String s = "Cannot enter location. Hero Present";
            CreateMessageUtility.createMsg(s);
        }

    }

    @Override
    public void setLocation(JButton[][] gridOfLocations, TFaceGrid initialLocation, PeopleType peopleType) {
        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn()].setText("ROVER");

        try {
            img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/tRover.jpg"));
        } catch (IOException e) {
            System.err.println("Error occurred " + e.getMessage());
        }

        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn()].setIcon(new ImageIcon(img));
        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn()].setDisabledIcon(new ImageIcon(img));
    }

    @Override
    public void flyToLocation(JButton[][] gridOfLocations, TFaceGrid locationToFlyTo) throws Exception {
        String s = "Rovers cannot fly.";
        CreateMessageUtility.createMsg(s);
    }

    @Override
    public void requestToFly() {
        String s = "Rovers cannot request for a fly vehicle.";
        CreateMessageUtility.createMsg(s);
    }

    @Override
    public EncryptionStrategy getEncryptionStrategy() {
        return EncryptionStrategy.NULL;
    }

}
