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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Entity
@Table(name = "genshin_character")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenshinCharacter.findAll", query = "SELECT g FROM GenshinCharacter g")
    , @NamedQuery(name = "GenshinCharacter.findById", query = "SELECT g FROM GenshinCharacter g WHERE g.id = :id")
    , @NamedQuery(name = "GenshinCharacter.findByName", query = "SELECT g FROM GenshinCharacter g WHERE g.name = :name")
    , @NamedQuery(name = "GenshinCharacter.findByPicture", query = "SELECT g FROM GenshinCharacter g WHERE g.picture = :picture")
    , @NamedQuery(name = "GenshinCharacter.findByPictureHd", query = "SELECT g FROM GenshinCharacter g WHERE g.pictureHd = :pictureHd")
    , @NamedQuery(name = "GenshinCharacter.findByDescription", query = "SELECT g FROM GenshinCharacter g WHERE g.description = :description")
    , @NamedQuery(name = "GenshinCharacter.findByNormalAttackName", query = "SELECT g FROM GenshinCharacter g WHERE g.normalAttackName = :normalAttackName")
    , @NamedQuery(name = "GenshinCharacter.findByDescriptionNormalAttack", query = "SELECT g FROM GenshinCharacter g WHERE g.descriptionNormalAttack = :descriptionNormalAttack")
    , @NamedQuery(name = "GenshinCharacter.findByElementalSkillName", query = "SELECT g FROM GenshinCharacter g WHERE g.elementalSkillName = :elementalSkillName")
    , @NamedQuery(name = "GenshinCharacter.findByDescriptionElementalSkill", query = "SELECT g FROM GenshinCharacter g WHERE g.descriptionElementalSkill = :descriptionElementalSkill")
    , @NamedQuery(name = "GenshinCharacter.findByElementalBurstName", query = "SELECT g FROM GenshinCharacter g WHERE g.elementalBurstName = :elementalBurstName")
    , @NamedQuery(name = "GenshinCharacter.findByDescriptionElementalBurst", query = "SELECT g FROM GenshinCharacter g WHERE g.descriptionElementalBurst = :descriptionElementalBurst")})
public class GenshinCharacter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 500)
    @Column(name = "picture")
    private String picture;
    @Size(max = 500)
    @Column(name = "picture_hd")
    private String pictureHd;
    @Size(max = 2500)
    @Column(name = "description")
    private String description;
    @Size(max = 150)
    @Column(name = "normal_attack_name")
    private String normalAttackName;
    @Size(max = 2500)
    @Column(name = "description_normal_attack")
    private String descriptionNormalAttack;
    @Size(max = 150)
    @Column(name = "elemental_skill_name")
    private String elementalSkillName;
    @Size(max = 2500)
    @Column(name = "description_elemental_skill")
    private String descriptionElementalSkill;
    @Size(max = 150)
    @Column(name = "elemental_burst_name")
    private String elementalBurstName;
    @Size(max = 2500)
    @Column(name = "description_elemental_burst")
    private String descriptionElementalBurst;
    @JoinColumn(name = "id_element", referencedColumnName = "id")
    @ManyToOne
    private Element idElement;
    @JoinColumn(name = "id_weapon_type", referencedColumnName = "id")
    @ManyToOne
    private WeaponType idWeaponType;
    @OneToMany(mappedBy = "idCharacter")
    private Collection<UserCharacterMark> userCharacterMarkCollection;

    public GenshinCharacter() {
    }

    public GenshinCharacter(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureHd() {
        return pictureHd;
    }

    public void setPictureHd(String pictureHd) {
        this.pictureHd = pictureHd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNormalAttackName() {
        return normalAttackName;
    }

    public void setNormalAttackName(String normalAttackName) {
        this.normalAttackName = normalAttackName;
    }

    public String getDescriptionNormalAttack() {
        return descriptionNormalAttack;
    }

    public void setDescriptionNormalAttack(String descriptionNormalAttack) {
        this.descriptionNormalAttack = descriptionNormalAttack;
    }

    public String getElementalSkillName() {
        return elementalSkillName;
    }

    public void setElementalSkillName(String elementalSkillName) {
        this.elementalSkillName = elementalSkillName;
    }

    public String getDescriptionElementalSkill() {
        return descriptionElementalSkill;
    }

    public void setDescriptionElementalSkill(String descriptionElementalSkill) {
        this.descriptionElementalSkill = descriptionElementalSkill;
    }

    public String getElementalBurstName() {
        return elementalBurstName;
    }

    public void setElementalBurstName(String elementalBurstName) {
        this.elementalBurstName = elementalBurstName;
    }

    public String getDescriptionElementalBurst() {
        return descriptionElementalBurst;
    }

    public void setDescriptionElementalBurst(String descriptionElementalBurst) {
        this.descriptionElementalBurst = descriptionElementalBurst;
    }

    public Element getIdElement() {
        return idElement;
    }

    public void setIdElement(Element idElement) {
        this.idElement = idElement;
    }

    public WeaponType getIdWeaponType() {
        return idWeaponType;
    }

    public void setIdWeaponType(WeaponType idWeaponType) {
        this.idWeaponType = idWeaponType;
    }

    @XmlTransient
    public Collection<UserCharacterMark> getUserCharacterMarkCollection() {
        return userCharacterMarkCollection;
    }

    public void setUserCharacterMarkCollection(Collection<UserCharacterMark> userCharacterMarkCollection) {
        this.userCharacterMarkCollection = userCharacterMarkCollection;
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
        if (!(object instanceof GenshinCharacter)) {
            return false;
        }
        GenshinCharacter other = (GenshinCharacter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.GenshinCharacter[ id=" + id + " ]";
    }

}
