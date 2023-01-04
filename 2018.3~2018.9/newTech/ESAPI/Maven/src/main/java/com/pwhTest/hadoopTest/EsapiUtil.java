package com.pwhTest.hadoopTest;


import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Validator;
import lombok.SneakyThrows;


public class EsapiUtil {

    private static final Validator VALIDATOR;

    private static final int MAX_MESSAGE_LENGTH = 10 * 10000;

    static {

        ESAPI.override(new ESAPIConfiguration());
        VALIDATOR = ESAPI.validator();
    }


	@SneakyThrows
    public static String xssEncode(String str) {
        return (str==null||str.trim().equals("")) ? str : VALIDATOR.getValidSafeHTML(EsapiUtil.class.getSimpleName(), str, MAX_MESSAGE_LENGTH, true);
    }

}