package com.ideal.oms.framework.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AutoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public AutoModel() {
	}

	public AutoModel(Long id) {
		this.setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null || other.getClass() != this.getClass())
			return false;
		Object otherId = ((AutoModel) other).getId();
		if (id == null)
			return false;
		if (otherId == null)
			return false;
		return id.equals(otherId);
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}
}
