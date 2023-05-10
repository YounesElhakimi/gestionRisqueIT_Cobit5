package ma.ehtp.gestionrisqueit.phase0.modelles;

import ma.ehtp.gestionrisqueit.phase0.tools.U;

public class Test {

    public static void main(String[] args) {
        Integer imp = Integer.valueOf("3");
        Integer fre = Integer.valueOf("4");
        Integer exp = imp * fre;
        U.ptxt(""+exp);
    }
}
