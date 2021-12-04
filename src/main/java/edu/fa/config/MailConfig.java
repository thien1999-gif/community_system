/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fa.config;

/**
 *
 * @author SE130204
 */
//configuraton for sent email 
public class MailConfig {
    public static final String HOST_NAME = "smtp.gmail.com";
//465
    public static final int SSL_PORT = 465; // Port for SSL
//587
    public static final int TSL_PORT = 587; // Port for TLS/STARTTLS

    public static final String APP_EMAIL = "thiennhse130501@fpt.edu.vn"; // your email

    public static final String APP_PASSWORD = "01692354000"; // your password

    public static String RECEIVE_EMAIL = "thiennhse130501@fpt.edu.vn";
}
