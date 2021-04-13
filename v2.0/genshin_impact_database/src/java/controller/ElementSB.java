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
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Element;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Stateless
public class ElementSB implements Serializable {

    @PersistenceContext(unitName = "1773031_genshinPU")
    private EntityManager em;

    public void insert(Element v) {
        em.persist(v);
    }

    public void update(Element v) {
        em.merge(v);
    }

    public void delete(Element v) {
        em.remove(em.find(Element.class, v.getId()));
    }

    public List<Element> getAllData() {
        return (List<Element>) em.createNamedQuery("Element.findAll").getResultList();
    }

    public HashMap getSelectOneMenuModel() {
        HashMap<String, Integer> o = new HashMap<>();
        getAllData().forEach((e) -> {
            o.put(e.getName(), e.getId());
        });
        return o;
    }

    public HashMap getDataFilterModel() {
        HashMap<Integer, Boolean> o = new HashMap<>();
        getAllData().forEach((e) -> {
            o.put(e.getId(), false);
        });
        return o;
    }

    public HashMap getChartDataPair() {
        HashMap<String, Integer> o = new HashMap<>();
        getAllData().forEach((e) -> {
            o.put(e.getName(), 0);
        });
        return o;
    }
}
