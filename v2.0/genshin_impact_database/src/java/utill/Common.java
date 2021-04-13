/* 
 * The MIT License
 *
 * Copyright 2021 Bear Au Jus - ジュースとくま.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package utill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
public class Common {

    public static final String FILTER_ALL = "all",
            FILTER_NOTOWNED = "not_owned";

    public static Date getCurrDate() {
        return new Date();
    }

    public static String addSeparatorToString(String v, Integer step, String sep) {
        String output = "";
        for (int i = 0; i < v.length(); i++) {
            output += v.charAt(i);
            if ((((i + 1) % step) == 0) && i < v.length() - 1) {
                output += sep;
            }
        }
        return output;
    }

    public static String getEncryptedString(String v) {
        String output = "";
        for (int i = 0; i < v.length(); i++) {
            output += "*";
        }
        return output;
    }

    public static String getDialogSeparator(String sep, Integer len) {
        String output = "";
        for (int i = 0; i < len; i++) {
            output += sep;
        }
        return output;
    }

    public static List<String> getArrBannerLocation(String rootDir, Integer len, String extension) {
        List<String> output = new ArrayList<>();
        for (int i = 1; i < len + 1; i++) {
            output.add(rootDir + String.valueOf(i) + "." + extension);
        }
        return output;
    }

    public static List shuffleCollection(List collections) {
        Collections.shuffle(collections);
        return collections;
    }

    public static String convertFilterPair(HashMap<Integer, Boolean> f) {
        if (f.isEmpty()) {
            return "";
        }
        Boolean pass = false;
        String output = "(";
        for (Map.Entry<Integer, Boolean> i : f.entrySet()) {
            if (i.getValue()) {
                output += String.valueOf(i.getKey()) + ", ";
                pass = true;
            }
        }
        if (pass) {
            output = output.substring(0, output.length() - 2);
            output += ")";
        } else {
            output = "";
        }
        return output;
    }

    public static Integer calculatePercentage(Integer v, Integer total) {
        try {
            return (v * 100) / total;
        } catch (Exception e) {
            return 0;
        }
    }

}
