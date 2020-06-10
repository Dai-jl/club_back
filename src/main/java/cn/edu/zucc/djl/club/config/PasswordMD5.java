package cn.edu.zucc.djl.club.config;



import org.apache.commons.codec.digest.DigestUtils;



public class PasswordMD5 {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "qazxswedc";

    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }


    public static void main(String[] args) {
        // System.out.println(md5("123"));
        System.out.println(inputPassToFormPass("123"));
        //  System.out.println(formPassToDBPass(inputPassToFormPass("123456"), UUID.randomUUID().toString().replace("-", "")));

    }

}
