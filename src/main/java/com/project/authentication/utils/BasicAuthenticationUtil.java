package com.project.authentication.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class BasicAuthenticationUtil {

    //todo: need to implement a util method that generate the secret to the user.
    //todo: might need to consider to move to here the decode/encode of the JWT.

    public static String encryptToMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

}
