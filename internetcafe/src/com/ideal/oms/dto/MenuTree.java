package com.ideal.oms.dto;

import java.io.Serializable;

public class MenuTree implements Serializable {
	private Long id;
	private Long pId;
	private String name;
	private Boolean open;
	private Boolean checked;

	public MenuTree() {
	}

	public MenuTree(Long id) {
		this.id = id;
	}

	public MenuTree(Long id, Long pId, String name, Boolean open,
			Boolean checked) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.checked = checked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
