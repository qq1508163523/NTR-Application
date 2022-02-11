package com.ntr.service.impl;

import com.ntr.entity.Category;
import com.ntr.service.CategoryService;
import com.ntr.service.GenreService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final GenreService genreService;

    public CategoryServiceImpl(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public Category getCategory() {
        Category category = new Category();
        category.setGenreList(genreService.getAllGenres());
        return category;
    }
}
