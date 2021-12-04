/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fa.config;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
//check email
public class MailError implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String invalidVerifyCode;

    public MailError() {
    }

    public String getInvalidVerifyCode() {
        return invalidVerifyCode;
    }

    public void setInvalidVerifyCode(String invalidVerifyCode) {
        this.invalidVerifyCode = invalidVerifyCode;
    }
    
    
}
