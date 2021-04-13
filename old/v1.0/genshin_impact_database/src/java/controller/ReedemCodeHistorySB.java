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
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Code;
import model.Dbuser;
import model.ReedemCodeHistory;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Stateless
public class ReedemCodeHistorySB implements Serializable {

    @PersistenceContext(unitName = "1773031_genshinPU")
    private EntityManager em;

    public void insert(ReedemCodeHistory v) {
        em.persist(v);
    }

    public void update(ReedemCodeHistory v) {
        em.merge(v);
    }

    public void delete(ReedemCodeHistory v) {
        em.remove(em.find(ReedemCodeHistory.class, v.getId()));
    }

    public String getListUserReedemedCodeData(Code c) {
        Query q = em.createQuery("SELECT u FROM ReedemCodeHistory u WHERE u.idCode.id = :idCode");
        q.setParameter("idCode", c.getId());
        List<ReedemCodeHistory> d = q.getResultList();
        StringBuilder sb = new StringBuilder();
        sb.append("VC-").append(c.getCode()).append("\nTotal Redeemed User : ").append(d.size()).append("\n=================================\n");
        for (ReedemCodeHistory i : d) {
            sb.append("[UID-").append(i.getIdUser().getId()).append("] ").append(i.getIdUser().getUsername()).append("\n");
        }
        String o = sb.toString();
        if (!d.isEmpty()) {
            return o.substring(0, o.length() - 1);
        } else {
            return o;
        }
    }

    public List<ReedemCodeHistory> getUserDataCode(Dbuser u) {
        Query q = em.createQuery("SELECT u FROM ReedemCodeHistory u WHERE u.idUser.id = :idUser");
        q.setParameter("idUser", u.getId());
        List<ReedemCodeHistory> output = q.getResultList();
        Collections.reverse(output);
        return output;
    }

    public List<Integer> getUserDataCodeMapped(Dbuser u) {
        List<Integer> o = new ArrayList<>();
        for (ReedemCodeHistory i : getUserDataCode(u)) {
            o.add(i.getIdCode().getId());
        }
        return o;
    }

    public List<ReedemCodeHistory> getAllData() {
        return (List<ReedemCodeHistory>) em.createNamedQuery("ReedemCodeHistory.findAll").getResultList();
    }
}
