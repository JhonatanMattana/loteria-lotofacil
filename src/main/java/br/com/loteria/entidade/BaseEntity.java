package br.com.loteria.entidade;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;

import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDate dateCreate;
	
	private LocalDate lastUpdate;
	
	@Version
	private Integer version;
	
	@Transient
	public boolean isNew() {
		return getVersion() == null;
	}
	
	@PrePersist
	public void prePersist() {
		if (isNew()) {
			this.version = 0;
			this.dateCreate = LocalDate.now();
			this.lastUpdate = LocalDate.now();
		}
	}
	
	@PreUpdate
	private void preUpdate() {
		this.version++;
		this.lastUpdate = LocalDate.now();
	}
	
}
