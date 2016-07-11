package org.thiki.kanban.foundation.security;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-26
 * <p>Version: 1.0
 */
public class Constants {
    public static final String ALGORITHM_RSA = "RSA";
    public static final String SECURITY_IDENTIFY_PASSED = "passed";
    public static final String SECURITY_IDENTITY_NO_AUTHENTICATION_TOKEN = "AuthenticationToken is required,please authenticate first.";
    public static final String SECURITY_IDENTITY_AUTHENTICATION_TOKEN_HAS_EXPIRE = "Your authenticationToken has expired,please authenticate again.";
    public static final String SECURITY_IDENTITY_USER_NAME_IS_NOT_CONSISTENT = "Your userName is not consistent with that in token.";
    public static final String LOCAL_ADDRESS = "127.0.0.1";
    public static final String FREE_AUTHENTICATION = "no";
    public static final String HEADER_PARAMS_TOKEN = "token";
    public static final String HEADER_PARAMS_USER_NAME = "userName";
    public static final String HEADER_PARAMS_AUTHENTICATION = "authentication";
}
