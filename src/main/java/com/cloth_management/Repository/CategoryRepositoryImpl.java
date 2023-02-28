package com.cloth_management.Repository;

import com.cloth_management.Models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
@Service
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public int AddCategory(Category category) {
        try{
     return jdbcTemplate.update("INSERT INTO categories (cat_name) VALUES (?)",
             new Object[] {category.getCat_name()});
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int UpdateCategory(Category category, int cat_id) {
        try{
       return jdbcTemplate.update("UPDATE categories SET cat_name=? WHERE cat_id=?",
               new Object[]{category.getCat_name(),cat_id});
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int DeleteCategory(int cat_id) {
        try {
            return jdbcTemplate.update("DELETE FROM categories where cat_id=?", cat_id);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Category> ListCategories() {
    return jdbcTemplate.query("SELECT * FROM categories",new BeanPropertyRowMapper<Category>(Category.class));

    }

    @Override
    public Category GetCategory(int cat_id) {
      return jdbcTemplate.queryForObject("SELECT * FROM categories where cat_id=?",
              new BeanPropertyRowMapper<Category>(Category.class),cat_id);
    }
}
