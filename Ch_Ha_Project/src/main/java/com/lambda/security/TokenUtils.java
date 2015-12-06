package com.lambda.security;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;



@Component
public class TokenUtils {
    
	public static final String MAGIC_KEY = "obfuscate";
	public HmacSha1Signature hmac;
    
    public String createToken(UserDetails userDetails) {
        long expires = System.currentTimeMillis() + 1000L * 60 * 60;
        return userDetails.getUsername() + ":" + expires + ":" + computeSignature(userDetails, expires);
    }

    public static String computeSignature(UserDetails userDetails, long expires) {

        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(userDetails.getUsername());
        signatureBuilder.append(":");
        signatureBuilder.append(expires);
        signatureBuilder.append(":");
        signatureBuilder.append(userDetails.getPassword());
        signatureBuilder.append(":");
        signatureBuilder.append(TokenUtils.MAGIC_KEY);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
    }

    public static String getUserNameFromToken(String authToken) {

        if (null == authToken) {
            return null;
        }

        String[] parts = authToken.split(":");
        return parts[0];
    }

    public static boolean validateToken(String authToken, UserDetails userDetails, String url) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {

        String[] parts = authToken.split(":");
        long expires = Long.parseLong(parts[1]);
        String signatureHRecu = parts[2];
        String signature = computeSignature(userDetails, expires); 
        String signatureHConcu= HmacSha1Signature.calculateHMACSHA1(url, signature);
        
        boolean validated =  expires >= System.currentTimeMillis() && signatureHRecu.equals( signatureHConcu );
        
        return validated ; 

    }
}