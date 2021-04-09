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
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Dbuser;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Stateless
public class UserSB implements Serializable {

    @PersistenceContext(unitName = "1773031_genshinPU")
    private EntityManager em;

    public static Dbuser loggedUser;
    public List<Dbuser> modelUser;

    public boolean login(String username, String pass) {
        modelUser = getAllData();
        for (Dbuser x : modelUser) {
            if (x.getUsername().equals(username) && x.getPassword().equals(pass)) {
                loggedUser = x;
                return true;
            }
        }
        loggedUser = null;
        return false;
    }

    public void logout() {
        loggedUser = null;
    }

    public boolean isAlreadyUserLogged() {
        return loggedUser != null;
    }

    public void insert(Dbuser v) {
        em.persist(v);
    }

    public void update(Dbuser v) {
        em.merge(v);
    }

    public void delete(Dbuser v) {
        em.remove(em.find(Dbuser.class, v.getId()));
    }

    public List<Dbuser> getAllData() {
        return (List<Dbuser>) em.createNamedQuery("Dbuser.findAll").getResultList();
    }

    public List<Dbuser> getAllDataAdmin() {
        Query q = em.createNamedQuery("Dbuser.findByIsadmin");
        q.setParameter("isadmin", 1);
        return (List<Dbuser>) q.getResultList();
    }

    public List<Dbuser> getAllDataMember() {
        Query q = em.createNamedQuery("Dbuser.findByIsadmin");
        q.setParameter("isadmin", 0);
        return (List<Dbuser>) q.getResultList();
    }

    public Dbuser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Dbuser loggedUser) {
        UserSB.loggedUser = loggedUser;
    }

    public Dbuser getUserbyID(Integer id) {
        modelUser = getAllData();
        for (Dbuser u : modelUser) {
            if (Objects.equals(u.getId(), id)) {
                return u;
            }
        }
        return null;
    }

    public Boolean isUserAlreadyExist(Dbuser user) {
        modelUser = getAllData();
        return modelUser.stream().anyMatch((u) -> (Objects.equals(u.getUsername(), user.getUsername())));
    }

    public boolean Login(Dbuser user) {
        modelUser = getAllData();
        for (Dbuser u : modelUser) {
            if (Objects.equals(u.getUsername(), user.getUsername()) && Objects.equals(u.getPassword(), user.getPassword())) {
                loggedUser = u;
                return true;
            }
        }
        loggedUser = null;
        return false;
    }
}
