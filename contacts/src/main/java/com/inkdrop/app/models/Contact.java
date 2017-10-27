package com.inkdrop.app.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact implements Serializable {

	private static final long serialVersionUID = 7998626098964616778L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String phone;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
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
		Contact other = (Contact) obj;
		if (id == null) {
			if (other.id != null)
			return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
