import java.lang.reflect.Method;

public class PrintJavaRuntimeVersion {
    public static void main(String[] args) {
        Class<Runtime> runtimeClass = Runtime.class;
        try {
            Method version = runtimeClass.getMethod("version");
            Object runtimeVer = version.invoke(runtimeClass);
            Class<? extends Object> runtimeVerClass = runtimeVer.getClass();
            try {
                int feature = (int) runtimeVerClass.getMethod("feature").invoke(runtimeVer);
                printVersionAndHalt((Integer.valueOf(feature)).toString());
            } catch (NoSuchMethodException e) {
                int major = (int) runtimeVerClass.getMethod("major").invoke(runtimeVer);
                printVersionAndHalt((Integer.valueOf(major)).toString());
            }
        } catch (Exception e) {
            printVersionAndHalt(System.getProperty("java.version"));
        }
    }

    private static void printVersionAndHalt(String version) {
        System.out.println("Java runtime version = '" + version + "'");
        Runtime.getRuntime().exit(0);
    }
}