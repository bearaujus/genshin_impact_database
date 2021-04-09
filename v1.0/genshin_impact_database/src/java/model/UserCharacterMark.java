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
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Entity
@Table(name = "user_character_mark")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserCharacterMark.findAll", query = "SELECT u FROM UserCharacterMark u")
    , @NamedQuery(name = "UserCharacterMark.findById", query = "SELECT u FROM UserCharacterMark u WHERE u.id = :id")
    , @NamedQuery(name = "UserCharacterMark.findByIsown", query = "SELECT u FROM UserCharacterMark u WHERE u.isown = :isown")})
public class UserCharacterMark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "isown")
    private Short isown;
    @JoinColumn(name = "id_character", referencedColumnName = "id")
    @ManyToOne
    private GenshinCharacter idCharacter;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private Dbuser idUser;

    public UserCharacterMark() {
    }

    public UserCharacterMark(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsown() {
        return isown == null ? false : isown == 1;
    }

    public void setIsown(Boolean isown) {
        this.isown = isown ? Integer.valueOf(1).shortValue() : Integer.valueOf(0).shortValue();
    }

    public GenshinCharacter getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(GenshinCharacter idCharacter) {
        this.idCharacter = idCharacter;
    }

    public Dbuser getIdUser() {
        return idUser;
    }

    public void setIdUser(Dbuser idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCharacterMark)) {
            return false;
        }
        UserCharacterMark other = (UserCharacterMark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserCharacterMark[ id=" + id + " ]";
    }

}
