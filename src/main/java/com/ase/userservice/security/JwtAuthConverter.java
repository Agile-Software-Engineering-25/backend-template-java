// based on this tutorial xdd: https://www.javacodegeeks.com/2025/07/spring-boot-keycloak-role-based-authorization.html

package com.ase.userservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
 
public class JwtAuthConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection convert(Jwt jwt) {
        var roles = jwt.getClaimAsStringList("groups");
        for (String role : roles) {
            System.out.println("Role from JWT: " + role);
        }
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toList());
    }
}