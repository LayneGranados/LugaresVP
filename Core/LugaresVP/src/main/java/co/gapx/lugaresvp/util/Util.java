/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gapx.lugaresvp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author laynegranadosmogollon
 */
public class Util {
    
    public static String encriptar(String password) throws NoSuchAlgorithmException{

    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(password.getBytes());

    byte byteData[] = md.digest();

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++)
        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

        return sb.toString();
    }
    
}
