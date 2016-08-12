package org.multiTable;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * Created by A.V.Tsaplin on 12.08.2016.
 */


@Entity
@Table(name = "t_roles_actions", catalog = "multitablebase")
public class RolesActions {

    private int id;
    private int roleid;
    private int actionid;

    public RolesActions() {
    }

    public RolesActions(int id, int roleid, int actionid) {
        this.id = id;
        this.roleid = roleid;
        this.actionid = actionid;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", length = 3, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "roleid", length = 25, nullable = false)
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Column(name = "actionid", length = 25, nullable = false)
    public int getActionid() {
        return actionid;
    }

    public void setActionid(int actionid) {
        this.actionid = actionid;
    }
}
