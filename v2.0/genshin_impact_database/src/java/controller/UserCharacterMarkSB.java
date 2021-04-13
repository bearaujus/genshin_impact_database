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
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Dbuser;
import model.GenshinCharacter;
import model.UserCharacterMark;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Stateless
public class UserCharacterMarkSB implements Serializable {

    @PersistenceContext(unitName = "1773031_genshinPU")
    private EntityManager em;

    public void insert(UserCharacterMark v) {
        em.persist(v);
    }

    public void update(UserCharacterMark v) {
        em.merge(v);
    }

    public void delete(UserCharacterMark v) {
        em.remove(em.find(UserCharacterMark.class, v.getId()));
    }

    public List<UserCharacterMark> getAllData() {
        return (List<UserCharacterMark>) em.createNamedQuery("UserCharacterMark.findAll").getResultList();
    }

    public UserCharacterMark getData(GenshinCharacter c, Dbuser u) {
        em.flush();
        em.clear();
        Query q = em.createQuery("SELECT u FROM UserCharacterMark u WHERE u.idCharacter.id = :idCharacter AND u.idUser.id = :idUser");
        q.setParameter("idCharacter", c.getId());
        q.setParameter("idUser", u.getId());
        List<UserCharacterMark> model = q.getResultList();
        if (model.isEmpty()) {
            UserCharacterMark ucm = new UserCharacterMark();
            ucm.setIdCharacter(new GenshinCharacter(c.getId()));
            ucm.setIdUser(new Dbuser(u.getId()));
            ucm.setIsown(false);
            insert(ucm);
            return getData(c, u);
        }
        return model.get(0);
    }

    public List<UserCharacterMark> getOwnedCharacerMark(Dbuser u) {
        Query q = em.createQuery("SELECT u FROM UserCharacterMark u WHERE u.idUser.id = :idUser AND u.isown = :isOwn");
        q.setParameter("idUser", u.getId());
        q.setParameter("isOwn", 1);
        return q.getResultList();
    }

//    public List<UserCharacterMark> getAllDataFiltered(HashMap<Integer, Boolean> fe, HashMap<Integer, Boolean> fwt) {
//        String fElement = Common.convertFilterPair(fe),
//                fWeaponType = Common.convertFilterPair(fwt),
//                queryBase = "SELECT u FROM UserCharacterMark u";
//        if ("".equals(fElement) && "".equals(fWeaponType)) {
//            return 
//        }
//        // SELECT * FROM genshin_character WHERE id_element in (1)
//        
//        Query q = em.createQuery("SELECT u FROM UserCharacterMark u WHERE u.idCharacter.idElement in :fElement AND u.idCharacter.idWeaponType in :fWeaponType");
//        
//        return null;
//    }
}
