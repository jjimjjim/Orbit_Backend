package com.study.app.domains.supplies;

public class SupplyRentalDTO {
	private int rental_seq;
	private String supply_name;
	private String category;
	private String supply_code;
	private String dept_name;
	private String name;
	private String rental_date;
	private String return_date;
	
	public SupplyRentalDTO() {}
	public SupplyRentalDTO(int rental_seq, String supply_name, String category, String supply_code, String dept_name,
			String name, String rental_date, String return_date) {
		super();
		this.rental_seq = rental_seq;
		this.supply_name = supply_name;
		this.category = category;
		this.supply_code = supply_code;
		this.dept_name = dept_name;
		this.name = name;
		this.rental_date = rental_date;
		this.return_date = return_date;
	}
	public int getRental_seq() {
		return rental_seq;
	}
	public void setRental_seq(int rental_seq) {
		this.rental_seq = rental_seq;
	}
	public String getSupply_name() {
		return supply_name;
	}
	public void setSupply_name(String supply_name) {
		this.supply_name = supply_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSupply_code() {
		return supply_code;
	}
	public void setSupply_code(String supply_code) {
		this.supply_code = supply_code;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRental_date() {
		return rental_date;
	}
	public void setRental_date(String rental_date) {
		this.rental_date = rental_date;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	
	
}
