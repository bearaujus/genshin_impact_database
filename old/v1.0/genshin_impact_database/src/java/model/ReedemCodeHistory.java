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
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Entity
@Table(name = "reedem_code_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReedemCodeHistory.findAll", query = "SELECT r FROM ReedemCodeHistory r")
    , @NamedQuery(name = "ReedemCodeHistory.findById", query = "SELECT r FROM ReedemCodeHistory r WHERE r.id = :id")
    , @NamedQuery(name = "ReedemCodeHistory.findByReedemDate", query = "SELECT r FROM ReedemCodeHistory r WHERE r.reedemDate = :reedemDate")})
public class ReedemCodeHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "reedem_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reedemDate;
    @JoinColumn(name = "id_code", referencedColumnName = "id")
    @ManyToOne
    private Code idCode;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne
    private Dbuser idUser;

    public ReedemCodeHistory() {
    }

    public ReedemCodeHistory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReedemDate() {
        return reedemDate;
    }

    public void setReedemDate(Date reedemDate) {
        this.reedemDate = reedemDate;
    }

    public Code getIdCode() {
        return idCode;
    }

    public void setIdCode(Code idCode) {
        this.idCode = idCode;
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
        if (!(object instanceof ReedemCodeHistory)) {
            return false;
        }
        ReedemCodeHistory other = (ReedemCodeHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ReedemCodeHistory[ id=" + id + " ]";
    }

}
