package com.facens.event.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.facens.event.dto.AdminDTO;


@Entity
@Table(name="TB_ADMIN")
public class Admin extends BaseUser implements Serializable{

    private static final long serialVersionUID = 1L;

    private String phoneNumber;

    public Admin() {
    }

    public Admin(AdminDTO adminDTO) {
        super(adminDTO.getName(), adminDTO.getEmail());
        this.phoneNumber = adminDTO.getPhoneNumber();
	}

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseUser other = (BaseUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
