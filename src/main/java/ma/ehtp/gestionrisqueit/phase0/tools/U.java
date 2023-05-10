package ma.ehtp.gestionrisqueit.phase0.tools;


import java.util.List;

public class U {

    public static void peg(){
        System.out.println("======================================================");
    }

    public static void pet(){
        System.out.println("*******************************************************");
    }

    public static void pgt(){
        System.out.println("====****====****====****====****====****====****====****");
    }

    public static void p(Object o){
        pet();
        System.out.println(o);
        pet();
    }

    public  static  void  ptxt(String txt){
        pgt();
        System.out.println(txt);
        pgt();
    }

    public static void pl(List<Object> objects){
        pet();
        for (Object o:objects) {
            System.out.println(o);
        }
        pet();
    }
}
