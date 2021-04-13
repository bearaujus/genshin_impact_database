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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import listener.CodeListener;
import utill.Common;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@EntityListeners(CodeListener.class)
@Entity
@Table(name = "code")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Code.findAll", query = "SELECT c FROM Code c")
    , @NamedQuery(name = "Code.findById", query = "SELECT c FROM Code c WHERE c.id = :id")
    , @NamedQuery(name = "Code.findByCode", query = "SELECT c FROM Code c WHERE c.code = :code")
    , @NamedQuery(name = "Code.findByQuota", query = "SELECT c FROM Code c WHERE c.quota = :quota")
    , @NamedQuery(name = "Code.findByExpireDate", query = "SELECT c FROM Code c WHERE c.expireDate = :expireDate")
    , @NamedQuery(name = "Code.findByPostedDate", query = "SELECT c FROM Code c WHERE c.postedDate = :postedDate")
    , @NamedQuery(name = "Code.findByVersion", query = "SELECT c FROM Code c WHERE c.version = :version")})
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 12)
    @Column(name = "code")
    private String code;
    @Column(name = "quota")
    private Integer quota;
    @Column(name = "expire_date")
    @Temporal(TemporalType.DATE)
    private Date expireDate;
    @Column(name = "posted_date")
    @Temporal(TemporalType.DATE)
    private Date postedDate;
    @Column(name = "version")
    @Version
    private Integer version;
    @OneToMany(mappedBy = "idCode")
    private Collection<ReedemCodeHistory> reedemCodeHistoryCollection;
    @Transient
    private Boolean isExpired;
    @Transient
    private Boolean isSoldout;
    @Transient
    private String md5;

    public Code() {
    }

    public Code(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getCodeView() {
        return Common.addSeparatorToString(code, 4, "-");
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Date getExpireDateView() {
        return expireDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getPostedDateView() {
        return postedDate;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    public Boolean getIsSoldout() {
        return isSoldout;
    }

    public void setIsSoldout(Boolean isSoldout) {
        this.isSoldout = isSoldout;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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
        if (!(object instanceof Code)) {
            return false;
        }
        Code other = (Code) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Code[ id=" + id + " ]";
    }

}
