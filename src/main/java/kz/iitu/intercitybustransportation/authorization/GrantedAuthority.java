package kz.iitu.intercitybustransportation.authorization;

import java.io.Serializable;

public interface GrantedAuthority extends Serializable {

    String getAuthority();
}