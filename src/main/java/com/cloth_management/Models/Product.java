package com.cloth_management.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

	private int prod_id;
	private String prod_title;
	private String prod_description;
	private String prod_colour;
	private int cat_id;
	private int brand_id;
	private int price;
	private String size;
	private int quantity;
	private String image_path;


	public Product(int prod_id, String prod_title, String prod_description, String prod_colour, int cat_id, int brand_id, int price, String size, int quantity, String image_path) {
		this.prod_id = prod_id;
		this.prod_title = prod_title;
		this.prod_description = prod_description;
		this.prod_colour = prod_colour;
		this.cat_id = cat_id;
		this.brand_id = brand_id;
		this.price = price;
		this.size = size;
		this.quantity = quantity;
		this.image_path = image_path;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_title() {
		return prod_title;
	}

	public void setProd_title(String prod_title) {
		this.prod_title = prod_title;
	}

	public String getProd_description() {
		return prod_description;
	}

	public void setProd_description(String prod_description) {
		this.prod_description = prod_description;
	}

	public String getProd_colour() {
		return prod_colour;
	}

	public void setProd_colour(String prod_colour) {
		this.prod_colour = prod_colour;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
}