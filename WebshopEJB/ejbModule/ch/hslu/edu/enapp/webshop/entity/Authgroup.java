package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the AUTHGROUP database table.
 *
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAuthgroupByGroupname", query = "SELECT a FROM Authgroup a WHERE a.groupname = :groupname"),
        @NamedQuery(name = "getAuthgroupByIdauthgroup", query = "SELECT a FROM Authgroup a WHERE a.idauthgroup = :idauthgroup"),
        @NamedQuery(name = "getAuthgroup", query = "SELECT a FROM Authgroup a") })
public class Authgroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idauthgroup;

    private String groupname;

    public int getIdauthgroup() {
        return this.idauthgroup;
    }

    public void setIdauthgroup(int idauthgroup) {
        this.idauthgroup = idauthgroup;
    }

    public String getGroupname() {
        return this.groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

}