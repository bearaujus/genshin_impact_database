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
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Element;
import model.GenshinCharacter;
import model.WeaponType;
import utill.Common;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Stateless
public class CharacterSB implements Serializable {

    @PersistenceContext(unitName = "1773031_genshinPU")
    private EntityManager em;

    public void insert(GenshinCharacter v) {
        em.persist(v);
    }

    public void update(GenshinCharacter v) {
        em.merge(v);
    }

    public void delete(GenshinCharacter v) {
        em.remove(em.find(GenshinCharacter.class, v.getId()));
    }

    public List<GenshinCharacter> getAllData() {
        return (List<GenshinCharacter>) em.createNamedQuery("GenshinCharacter.findAll").getResultList();
    }

    public List<GenshinCharacter> getAllDataFiltered(HashMap<Integer, Boolean> fe, HashMap<Integer, Boolean> fwt, String fb, List<Integer> ouc) {
        String fElement = Common.convertFilterPair(fe),
                fWeaponType = Common.convertFilterPair(fwt);
        List<Element> le = new ArrayList<>();
        List<WeaponType> lwt = new ArrayList<>();
        for (Map.Entry<Integer, Boolean> i : fe.entrySet()) {
            if (i.getValue()) {
                le.add(new Element(i.getKey()));
            }
        }
        for (Map.Entry<Integer, Boolean> i : fwt.entrySet()) {
            if (i.getValue()) {
                lwt.add(new WeaponType(i.getKey()));
            }
        }
        if ("".equals(fElement) && "".equals(fWeaponType) && Common.FILTER_ALL.equals(fb)) {
            return getAllData();
        }
        Query q;
        System.out.println(ouc);
        if (Common.FILTER_ALL.equals(fb)) {
            if (!"".equals(fElement) && !"".equals(fWeaponType)) {
                q = em.createQuery("SELECT u FROM GenshinCharacter u WHERE u.idElement IN :fElement AND u.idWeaponType IN :fWeaponType");
                q.setParameter("fElement", le);
                q.setParameter("fWeaponType", lwt);
            } else if (!"".equals(fElement)) {
                q = em.createQuery("SELECT u FROM GenshinCharacter u WHERE u.idElement IN :fElement");
                q.setParameter("fElement", le);
            } else {
                q = em.createQuery("SELECT u FROM GenshinCharacter u WHERE u.idWeaponType IN :fWeaponType");
                q.setParameter("fWeaponType", lwt);
            }
        } else {
            String partrialQuery = " AND u.id NOT IN :fouc";
            if (!"".equals(fElement) && !"".equals(fWeaponType) && !ouc.isEmpty()) {
                q = em.createQuery("SELECT u FROM GenshinCharacter u WHERE u.idElement IN :fElement AND u.idWeaponType IN :fWeaponType" + partrialQuery);
                q.setParameter("fElement", le);
                q.setParameter("fWeaponType", lwt);
            } else if (!"".equals(fElement) && !ouc.isEmpty()) {
                q = em.createQuery("SELECT u FROM GenshinCharacter u WHERE u.idElement IN :fElement" + partrialQuery);
                q.setParameter("fElement", le);
            } else if (!"".equals(fWeaponType) && !ouc.isEmpty()) {
                q = em.createQuery("SELECT u FROM GenshinCharacter u WHERE u.idWeaponType IN :fWeaponType" + partrialQuery);
                q.setParameter("fWeaponType", lwt);
            } else if (!ouc.isEmpty()) {
                q = em.createQuery("SELECT u FROM GenshinCharacter u WHERE u.id NOT IN :fouc");
            } else {
                return getAllData();
            }
            q.setParameter("fouc", ouc);
        }
        return q.getResultList();
    }

}
