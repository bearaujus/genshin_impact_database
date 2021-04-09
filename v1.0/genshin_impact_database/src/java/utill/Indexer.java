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

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
public class Indexer {

    public static final String LOGIN = "login",
            REGISTER = "register",
            ADMIN_HOME = "admin_home",
            USER_HOME = "user_home",
            ADMIN_ELEMENT = "admin_element",
            USER_ELEMENT = "user_element",
            ADMIN_WEAPON_TYPE = "admin_weapon_type",
            USER_WEAPON_TYPE = "user_weapon_type",
            ADMIN_CHARACTER = "admin_character",
            USER_CHARACTER = "user_character",
            ADMIN_VOUCHER_CODE = "admin_voucher_code",
            USER_VOUCHER_CODE = "user_voucher_code",
            PROFILE = "profile";

    public static String getImageRootPath() {
        return "img/";
    }
}
