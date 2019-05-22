/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.portaventura.classes;

/**
 *
 * @author Gorka
 */
public enum TipusAcces {
    UN_SOL_US(1),
    ILIMITAT(2),
    UN_SOL_US_1aFILA(3),
    ILIMITAT_I_UN_SOL_US_1aFILA(4);
    
    private final int value;

    TipusAcces(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
