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
package validator;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@FacesValidator("validator.voucher.code")
public class Voucher_Code implements Validator, Serializable {

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object obj) throws ValidatorException {
        if (null == (String) obj || "".equals((String) obj)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Manage Voucher Code", "Voucher Code is required"));
        } else if (String.valueOf(obj).length() < 6) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Manage Voucher Code", "Voucher Code minimum character is 6"));
        } else {
            if (Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE).matcher((String) obj).find()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Manage Voucher Code", "Voucher Code cannot contain special cases"));
            }
        }
    }
}