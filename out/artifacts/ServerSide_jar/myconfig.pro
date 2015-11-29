-injars ServerSide.jar
-outjars ServerSideOut.jar
-libraryjars <java.home>/lib/rt.jar

-keep public class Main {
    public static void main(java.lang.String[]);
}

-keep public class Checker {}
-keep public class GetDriveInfo {}
-keep public class MyHandler {}
-keep public class MyRequest {}
-keep public class NetworkClassLoader {}
-keep public class PreServerThread {}
-keep public class Server {}
-keep public class ServerThread {}
-keep public interface StarterInterface {}
-keep public class TimeChecker {}
-keep public class XMLCreator {}