package com.cloth_management.Repository;

import com.cloth_management.Models.Brand;

import java.util.List;

public interface BrandRepository {

    public int AddBrand(Brand brand);
    public int DeleteBrand(int brand_id);
    public int UpdateBrand(Brand brand, int brand_id);

    public List<Brand> ListBrand();
    public Brand GetBrand(int brand_id);




}
