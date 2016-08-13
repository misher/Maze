package org.multiTable;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.V.Tsaplin on 13.08.2016.
 */
public class CheckUserForActionHibernate {

    public CheckUserForActionHibernate() {
    }

    public boolean checkUserForActionHibernate(FieldToSearchEnum fieldToSearchEnum, String value, String actionToCheck) {
        String searchBy = fieldToSearchEnum.getSearchString();
        Session session = new HibernateUtil().getSessionFactory().openSession();
        List<Integer> rolesIdList = new ArrayList<>();
        List<String> userActions = new ArrayList<>();
        List<UsersRoles> listUsersRoles = session.createQuery(" select ur from UsersRoles ur inner join ur.user where ur.user." + searchBy + " = '" + value + "'").list();
        for (int i = 0; i < listUsersRoles.size(); i++) {
            List<RolesActions> listRolesActions = session.createQuery(" select ra from RolesActions ra where ra.roleid = '" + listUsersRoles.get(i).getRoleId() + "'").list();
            for (int j = 0; j < listRolesActions.size(); j++) {
                List<Actions> listActions = session.createQuery(" select acts from Actions acts where acts.id = '" + listRolesActions.get(j).getActionid() + "'").list();
                userActions.add(listActions.get(0).getAction());
            }
        }
        if (userActions.contains(actionToCheck)) {
            return true;
        } else {
            return false;
        }
    }

}
