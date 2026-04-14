package util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatadorUtil {

    public static String formatarMoeda(Number valor) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        return nf.format(valor);
    }
}