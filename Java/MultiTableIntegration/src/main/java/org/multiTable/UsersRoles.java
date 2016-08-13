package org.multiTable;

import javax.persistence.*;
import javax.persistence.criteria.Order;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * Created by A.V.Tsaplin on 12.08.2016.
 */

@Entity
@Table(name = "t_users_roles", catalog = "multitablebase")
public class UsersRoles {

    private int id;
    private Users user;
    private int roleId;

    public UsersRoles() {
    }

    public UsersRoles(int id, Users user, int roleId) {
        this.id = id;
        this.user = user;
        this.roleId = roleId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

    @Column(name = "roleid", length = 25, nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}