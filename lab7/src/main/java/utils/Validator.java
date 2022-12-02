package utils;

public class Validator {
    
    public static Boolean isValid(String[] raw) {
        if (raw[0] == null || raw[1] == null || raw[2] == null || raw[3] == null)
            return false;
        return isNotHeader(raw[0]) && isValidLine(raw[2], raw[3]);
    }
    
    private static boolean isValidLine(String uSlots, String fSlots) {
        return !(uSlots.equals("0") && fSlots.equals("0"));
    }
    
    private static boolean isNotHeader(String rawFirst) {
        return !rawFirst.equals("station");
    }

}
