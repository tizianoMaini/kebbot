package hello.service;

import hello.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public List<User> listUser() {
        CriteriaQuery<User> c = em.getCriteriaBuilder().createQuery(User.class);
        c.from(User.class);
        return em.createQuery(c).getResultList();
    }

    @Transactional
    public void addUser(User user) {
        em.merge(user);
    }

}
