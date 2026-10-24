package com.StoreShop.ProjectComputer.Service;

import com.StoreShop.ProjectComputer.CustomException.Exception;
import com.StoreShop.ProjectComputer.DTO.Category.RequestCategory;
import com.StoreShop.ProjectComputer.DTO.Category.ResponseCategory;
import com.StoreShop.ProjectComputer.Model.CategoryModel;
import com.StoreShop.ProjectComputer.Repository.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public List<CategoryModel> getList() {
        return  categoryRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, CategoryModel.class))
                .toList();
    }

    public ResponseCategory getOne(Long id) {
        return categoryRepository.findById(id).map(src -> modelMapper.map(src, ResponseCategory.class))
                .orElseThrow(() -> new Exception.CustomException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    public Page<ResponseCategory> filterList(int page, int size, Sort.Direction direction, String name){
        Sort sort = Sort.by(direction, "id").and(Sort.by(direction, "name"));
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return categoryRepository.findAll((root, query, cb)->{
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasText(name)){
                String keyword = "%" + name.toLowerCase() + "%";
                predicates.add( cb.like(cb.lower(root.get("name")), keyword));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(src -> modelMapper.map(src, ResponseCategory.class));
    }

    public ResponseCategory create(RequestCategory requestCategory) {
        CategoryModel categoryModel = categoryRepository.save(modelMapper.map(requestCategory, CategoryModel.class));
        return modelMapper.map(categoryModel, ResponseCategory.class);
    }

    public ResponseCategory delete(Long id) {
        CategoryModel category = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception.CustomException(HttpStatus.NOT_FOUND, "Category not found"));
        categoryRepository.delete(category);
        return modelMapper.map(category, ResponseCategory.class);
    }

    public ResponseCategory update(@Valid RequestCategory requestCategory, Long id) {
        CategoryModel category = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception.CustomException(HttpStatus.NOT_FOUND, "Category not found"));
        category.setName(requestCategory.getName());
        category.setDescription(requestCategory.getDescription());
        category.setActive(requestCategory.getActive());
        categoryRepository.save(category);
        return modelMapper.map(category, ResponseCategory.class);
    }
}
