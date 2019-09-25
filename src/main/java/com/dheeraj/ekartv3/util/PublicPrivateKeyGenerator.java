package com.dheeraj.ekartv3.util;

import java.security.*;
import java.util.Base64;

public class PublicPrivateKeyGenerator {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public PublicPrivateKeyGenerator(int keylength){
        createKeys(keylength);
    }

    public void createKeys(int keylength) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(keylength);
            KeyPair pair = keyGen.generateKeyPair();
            this.privateKey = pair.getPrivate();
            this.publicKey = pair.getPublic();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating public private key pair");
        }
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public static String convertKeyToString(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
