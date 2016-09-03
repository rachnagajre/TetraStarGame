/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rachna Gajre <rgajre@scu.edu>
 */
public class StarAtlasComposite extends StarMapComponent {

    /**
     * List of StarMaps.
     */
    List<StarMapComponent> lmaps = new ArrayList<StarMapComponent>();

    /**
     * External Iterator used to traverse several StarMaps in StarAtlas.
     */
    StarMapComponentIterator lsmcIterator;

    /**
     * Initialize StarAtlas.
     *
     * @param starComponentID
     * @param loc
     */
    public StarAtlasComposite(int starComponentID, TFaceGrid loc) {
        this.starComponentID = starComponentID;
        this.mapLocation = loc;
        this.numberItems = 0;

    }

    /**
     * Create Iterator for traversal.
     *
     * @param lsmc
     */
    private void createIterator(List<StarMapComponent> lsmc) {
        this.lsmcIterator = new StarMapComponentIterator(lsmc);
    }

    /**
     * Get the message.
     *
     * @return
     */
    @Override
    String getBody() {
        return null;
    }

    /**
     * Get the encrypted Symbol.
     *
     * @return
     */
    @Override
    char getEncryptedSymbol() {
        StarMapComponent map;

        encryptionStatus = true;
        createIterator(lmaps);
        lsmcIterator.begin();
        if (lsmcIterator.hasNext()) {
            map = (StarMapComponent) lsmcIterator.next();
            return map.getEncryptedSymbol();
        }

        return '\0';
    }

    /**
     * Encrypt the StarAtlas.
     *
     * @param heroID
     * @param date
     * @param Symbol
     */
    @Override
    public void encrypt(int heroID, Date date, char symbol) {
        StarMapComponent map;

        encryptionStatus = true;
        createIterator(lmaps);
        lsmcIterator.begin();
        while (lsmcIterator.hasNext()) {
            map = (StarMapComponent) lsmcIterator.next();
            map.encrypt(heroID, date, symbol);
        }
    }

    /**
     * To check whether StarAtlas is encrypted by passed heroID or not.
     *
     * @param heroID
     * @return
     */
    @Override
    boolean isEncryptedByMe(int heroID) {
        StarMapComponent map;

        createIterator(lmaps);
        lsmcIterator.begin();
        while (lsmcIterator.hasNext()) {
            map = (StarMapComponent) lsmcIterator.next();
            if (map.isEncryptedByMe(heroID) == true) {
                return true;
            }
        }
        return false;
    }

    /**
     * Decrypt the StarAtlas.
     *
     * @param heroID
     */
    @Override
    public void decrypt(int heroID) {
        StarMapComponent map;

        createIterator(lmaps);
        lsmcIterator.begin();
        while (lsmcIterator.hasNext()) {
            map = (StarMapComponent) lsmcIterator.next();
            map.decrypt(heroID);
        }
    }

    /**
     * Display the StarAtlas Contents.
     */
    @Override
    public void display() {
        StarMapComponent map;

       if (encryptionStatus != true) {
            createIterator(lmaps);
            lsmcIterator.begin();
            while (lsmcIterator.hasNext()) {
                map = (StarMapComponent) lsmcIterator.next();
                map.display();
            }
        } else {
            String message = encryptedDisplay();
            CreateMessageUtility.createMsg(message);
        }
    }

    /**
     * To display the encrypted content.
     *
     * @return
     */
    @Override
    public String encryptedDisplay() {
        String message = null;
        StarMapComponent map;

        createIterator(lmaps);
        lsmcIterator.begin();
        map = (StarMapComponent) lsmcIterator.next();
        message = map.encryptedDisplay();
        while (lsmcIterator.hasNext()) {
            map = (StarMapComponent) lsmcIterator.next();
            message += "\n";
            message += "\t";
            message += map.getBody();
           
        }
        message += "\n";
        for (int i = 0; i < 40; ++i) {
            message = message + map.getEncryptedSymbol();
        }

        return message;
    }
    

    /**
     * Set the encryption Algorithm.
     *
     * @param encrypt
     */
    @Override
    public void setEncryptionAlgorithm(EncryptionAlgorithm encrypt) {
        StarMapComponent map;

        createIterator(lmaps);
        lsmcIterator.begin();
        while (lsmcIterator.hasNext()) {
            map = (StarMapComponent) lsmcIterator.next();
            map.setEncryptionAlgorithm(encrypt);
        }
    }

    /**
     * Add new StarMap in StarAtlas.
     *
     * @param starMap
     */
    public void addStarMap(StarMapComponent starMap) {
        lmaps.add(starMap);
        numberItems++;
    }
}