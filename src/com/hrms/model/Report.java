package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="tblReport")
@XmlRootElement
public class Report implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long reportNumber;
	 
	@Column(name="First_name")
	private String firstName;
	 	 
	@Column(name="Last_name")
	private String lastName;
	 
	@Column(name="ID_number")
	private String identityNumber;
	 	 
	@Column(name="Title")
	private String title;
	
	@Column(name="Report_title")
	private String reportTitle;
	 	 
	@Column(name="Description")
	private String description;

	public Report(String firstName, String lastName, String identityNumber,
			String title, String reportTitle, String description) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.identityNumber = identityNumber;
		this.title = title;
		this.reportTitle = reportTitle;
		this.description = description;
	}

	public Report() {
		super();
	}

	public Long getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(Long reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
}
