package ir.sajjadyosefi.accountauthenticator.authentication;

public class AccountGeneral {
    public static final String ACCOUNT_TYPE = "com.udinic.auth_example";    //ir.sajjadyosefi.android
    public static final String ACCOUNT_NAME = "Udinic";

    public static final String AUTHTOKEN_TYPE_READ_ONLY = "Read only";
    public static final String AUTHTOKEN_TYPE_READ_ONLY_LABEL = "Read only access to an Udinic account";

    public static final String AUTHTOKEN_TYPE_FULL_ACCESS = "Full access";
    public static final String AUTHTOKEN_TYPE_FULL_ACCESS_LABEL = "Full access to an Udinic account";

    public static final ServerAuthenticate sServerAuthenticate = new ParseComServerAuthenticate();


}
