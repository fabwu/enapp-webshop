package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;

public class AuthgroupDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "AuthgroupDTO [idauthgroup=" + idauthgroup + ", groupname=" + groupname + "]";
    }

}