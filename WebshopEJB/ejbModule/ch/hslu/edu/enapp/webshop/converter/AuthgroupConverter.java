package ch.hslu.edu.enapp.webshop.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.hslu.edu.enapp.webshop.common.dto.AuthgroupDTO;
import ch.hslu.edu.enapp.webshop.entity.Authgroup;

public class AuthgroupConverter implements Serializable {

    private static final long serialVersionUID = 1L;

    public Authgroup convertToEntity(AuthgroupDTO dto) {
        Authgroup authgroup = new Authgroup();

        authgroup.setIdauthgroup(dto.getIdauthgroup());
        authgroup.setGroupname(dto.getGroupname());

        return authgroup;
    }

    public AuthgroupDTO convertToDto(Authgroup authgroup) {
        AuthgroupDTO dto = new AuthgroupDTO();

        dto.setIdauthgroup(authgroup.getIdauthgroup());
        dto.setGroupname(authgroup.getGroupname());

        return dto;
    }

    public List<AuthgroupDTO> convertListToDto(List<Authgroup> entityList) {
        List<AuthgroupDTO> dtoList = new ArrayList<>();

        for (Authgroup authgroup : entityList) {
            AuthgroupDTO dto = convertToDto(authgroup);
            dtoList.add(dto);
        }

        return dtoList;
    }

}
