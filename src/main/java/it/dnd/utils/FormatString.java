package it.dnd.utils;

import org.apache.commons.lang3.StringUtils;

public class FormatString {
    /**
     * Converts a given string by trimming leading and trailing spaces,
     * replacing all spaces with a dash (-), and converting the entire string to lowercase.
     *
     * @param input the input string
     * @return the transformed string
     */
    public static String dashString (String input){
        if (StringUtils.isBlank(input))
            return input;

        return input.trim().replace(" ","-").toLowerCase();
    }
}
