package in.srain.cube.demo.utils;

public class DemoEnv {

    private static String ENV_PROD = "production";
    private static String ENV_BETA = "production";
    private static String sEnv = "";

    public static boolean isProd() {
        return sEnv != null && sEnv.equals(ENV_PROD);
    }

    public static boolean isBeta() {
        return sEnv != null && sEnv.equals(ENV_BETA);
    }

    public static void setEnv(String env) {
        sEnv = env;
    }
}
