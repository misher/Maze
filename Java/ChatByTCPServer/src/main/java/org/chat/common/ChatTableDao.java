package org.chat.common;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * Created by A.V.Tsaplin on 03.08.2016.
 */

@Repository
public class ChatTableDao implements IChatTableDao {

    @Autowired
    private Session session;

    public ChatTableDao(Session session) {
        this.session = session;
    }

    @Override
    public void save(ChatTable chatTable) {
        session.beginTransaction();
        session.save(chatTable);
        session.getTransaction().commit();
    }
}






//    @Override
//    @Transactional
//    public void update(ChatTable chatTable) {
//        sessionFactory.getCurrentSession().update(chatTable);
//    }
//
//    @Override
//    @Transactional
//    public void delete(ChatTable chatTable) {
//        sessionFactory.getCurrentSession().delete(chatTable);
//    }
//
//    @Override
//    @Transactional
//    public ChatTable get(int id) {
//        String sqlQuery = "from " + ChatTable.class.getName() +  " where stockCode = ?" + id;
//        Query query = sessionFactory.getCurrentSession().createQuery(sqlQuery);
//
//        @SuppressWarnings("unchecked")
//        List<ChatTable> listUser = (List<ChatTable>) query.list();
//
//        if (listUser != null && !listUser.isEmpty()) {
//            return listUser.get(0);
//        }
//
//        return null;
//    }
//}
