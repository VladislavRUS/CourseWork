-injars ServerSide.jar
-outjars ServerSideOut.jar
-libraryjars <java.home>/lib/rt.jar
-dontwarn

-keep public class DataBaseConnection
-keep public class Starter {
	
    public static void main(java.lang.String[]);
}