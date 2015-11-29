-injars ServerSide-1.0.jar
-outjars ServerSideOut.jar
-libraryjars <java.home>/lib/rt.jar
-dontwarn

-keep public class Main {
	
    public static void main(java.lang.String[]);
}