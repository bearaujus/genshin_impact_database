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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import utill.Common;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Entity
@Table(name = "dbuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dbuser.findAll", query = "SELECT d FROM Dbuser d")
    , @NamedQuery(name = "Dbuser.findById", query = "SELECT d FROM Dbuser d WHERE d.id = :id")
    , @NamedQuery(name = "Dbuser.findByUsername", query = "SELECT d FROM Dbuser d WHERE d.username = :username")
    , @NamedQuery(name = "Dbuser.findByPassword", query = "SELECT d FROM Dbuser d WHERE d.password = :password")
    , @NamedQuery(name = "Dbuser.findByIsadmin", query = "SELECT d FROM Dbuser d WHERE d.isadmin = :isadmin")})
public class Dbuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @Column(name = "isadmin")
    private Short isadmin;
    @OneToMany(mappedBy = "idUser")
    private Collection<UserCharacterMark> userCharacterMarkCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<ReedemCodeHistory> reedemCodeHistoryCollection;

    public Dbuser() {
    }

    public Dbuser(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEncryptedPassword() {
        return Common.getEncryptedString(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsadmin() {
        return isadmin == null ? false : isadmin == 1;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin ? Integer.valueOf(1).shortValue() : Integer.valueOf(0).shortValue();
    }

    @XmlTransient
    public Collection<UserCharacterMark> getUserCharacterMarkCollection() {
        return userCharacterMarkCollection;
    }

    public void setUserCharacterMarkCollection(Collection<UserCharacterMark> userCharacterMarkCollection) {
        this.userCharacterMarkCollection = userCharacterMarkCollection;
    }

    @XmlTransient
    public Collection<ReedemCodeHistory> getReedemCodeHistoryCollection() {
        return reedemCodeHistoryCollection;
    }

    public void setReedemCodeHistoryCollection(Collection<ReedemCodeHistory> reedemCodeHistoryCollection) {
        this.reedemCodeHistoryCollection = reedemCodeHistoryCollection;
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
        if (!(object instanceof Dbuser)) {
            return false;
        }
        Dbuser other = (Dbuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Dbuser[ id=" + id + " ]";
    }

}
