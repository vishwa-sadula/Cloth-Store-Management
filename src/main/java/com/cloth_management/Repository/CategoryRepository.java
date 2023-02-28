package com.cloth_management.Repository;

import com.cloth_management.Models.Category;

import java.util.List;

public interface CategoryRepository {
    public int AddCategory(Category category);
    public int DeleteCategory(int cat_id);
    public int UpdateCategory(Category category, int cat_id);

    public List<Category> ListCategories();
    public Category GetCategory(int cat_id);
}
