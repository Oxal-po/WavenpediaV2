package fr.oxal.v2.utils.text;

import fr.oxal.v2.Wavenpedia;

import java.text.Normalizer;

public class TextUtils {

    public static String normalize(String str){
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static String upperCaseFirst(String str) {
        char[] arr = str.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }
}
