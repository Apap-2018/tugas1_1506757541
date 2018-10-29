package com.apap.sipeg.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apap.sipeg.model.JabatanModel;
import com.apap.sipeg.model.PegawaiModel;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawaiModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pegawai_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PegawaiModel pegawai;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jabatan_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private JabatanModel jabatan;*/

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	/*public void setPegawai(PegawaiModel pegawai) {
		this.pegawai = pegawai;
	}

	public PegawaiModel getPegawai() {
		return pegawai;
	}

	public void setJabatan(JabatanModel jabatan) {
		this.jabatan = jabatan;
	}

	public JabatanModel getJabatan() {
		return jabatan;
	}*/
}