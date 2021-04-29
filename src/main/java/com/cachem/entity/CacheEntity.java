package com.cachem.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="t01_cache")
public class CacheEntity {
	
    @Id
    @Column(name="ckey")
    private String id;
     
    @Column(name="dataval")
    private String dataval;

    @Column(name="createtime")
    private LocalDateTime createtime;
    
    @Column(name="datavalbin")
    private byte[] datavalbin;
    
    @Column(name = "datavaljson", columnDefinition = "json")
    private String datavaljson;
    
	public String getDatavaljson() {
		return datavaljson;
	}

	public void setDatavaljson(String datavaljson) {
		this.datavaljson = datavaljson;
	}

	public byte[] getDatavalbin() {
		return datavalbin;
	}

	public void setDatavalbin(byte[] datavalbin) {
		this.datavalbin = datavalbin;
	}

	public LocalDateTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataval() {
		return dataval;
	}

	public void setDataval(String dataval) {
		this.dataval = dataval;
	}

	@Override
	public String toString() {
		return "CacheEntity [id=" + id + ", dataval=" + dataval + ", createtime=" + createtime + ", datavalbin="
				+ Arrays.toString(datavalbin) + ", datavaljson=" + datavaljson + "]";
	}

	public CacheEntity(String id, String dataval, LocalDateTime createtime, byte[] datavalbin, String datavaljson) {
		super();
		this.id = id;
		this.dataval = dataval;
		this.createtime = createtime;
		this.datavalbin = datavalbin;
		this.datavaljson = datavaljson;
	}

	public CacheEntity() {
		
	}
}
