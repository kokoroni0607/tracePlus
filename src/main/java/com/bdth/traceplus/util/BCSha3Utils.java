package com.bdth.traceplus.util;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.Charset;

/**
 * @author weiming.zhu
 * @date 2019/6/13 11:00
 */
public class BCSha3Utils {
    // SHA3-224 算法
    public static String sha3224(byte[] bytes) {
        Digest digest = new SHA3Digest(224);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }

    // SHA3-256 算法
    public static String sha3256(byte[] bytes) {
        Digest digest = new SHA3Digest(256);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }

    // SHA3-384 算法
    public static String sha3384(byte[] bytes) {
        Digest digest = new SHA3Digest(384);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }

    // SHA3-512 算法
    public static String sha3512(byte[] bytes) {
        Digest digest = new SHA3Digest(512);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }

    // SHAKE-128 算法
    public static String shake128(byte[] bytes) {
        Digest digest = new SHAKEDigest(128);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }

    // SHAKE-256 算法
    public static String shake256(byte[] bytes) {
        Digest digest = new SHAKEDigest(256);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }

    public static String encrypt(String method, String target) {
        String result = null;
        byte[] bytes = target.getBytes(Charset.forName("UTF-8"));
        switch (method) {
            case "sha3-224":
                result = sha3224(bytes);
                break;
            case "sha3-256":
                result = sha3256(bytes);
                break;
            case "sha3-384":
                result = sha3384(bytes);
                break;
            case "sha3-512":
                result = sha3512(bytes);
                break;
            case "shake-128":
                result = shake128(bytes);
                break;
            case "shake-256":
                result = shake256(bytes);
                break;
            default:
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        byte[] bytes = "bdthSY@2019".getBytes(Charset.forName("UTF-8"));
        String sha3224 = sha3224(bytes);
        System.out.println("sha3-224:" + sha3224 + ",lengh=" + sha3224.length());
        String sha3256 = sha3256(bytes);
        System.out.println("sha3-256:" + sha3256 + ",lengh=" + sha3256.length());
        String sha3384 = sha3384(bytes);
        System.out.println("sha3-384:" + sha3384 + ",lengh=" + sha3384.length());
        String sha3512 = sha3512(bytes);
        System.out.println("sha3-512:" + sha3512 + ",lengh=" + sha3512.length());
        String shake128 = shake128(bytes);
        System.out.println("shake-128:" + shake128 + ",lengh=" + shake128.length());
        String shake256 = shake256(bytes);
        System.out.println("shake-256:" + shake256 + ",lengh=" + shake256.length());
    }
}
