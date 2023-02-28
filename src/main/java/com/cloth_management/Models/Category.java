package com.cloth_management.Models;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Category {
	
    private int cat_id;
    private String cat_name;
    
    public Category(int cat_id, String cat_name) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
	}
	
    
    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
//    @Override
//    public String toString() {
//        return "categories{" +
//                "cat_name='" + cat_name + '\'' +
//                '}';
//    }


}
