package com.pwhTest.hadoopTest;


import org.owasp.esapi.reference.DefaultSecurityConfiguration;

import java.io.IOException;
import java.io.InputStream;


public class ESAPIConfiguration extends DefaultSecurityConfiguration {
    @Override
    public InputStream getResourceStream(String filename) throws IOException {
        return ClassLoader.getSystemResourceAsStream(filename);
    }

}