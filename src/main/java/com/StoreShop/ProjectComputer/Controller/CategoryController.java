package com.StoreShop.ProjectComputer.Controller;

import com.StoreShop.ProjectComputer.DTO.Category.RequestCategory;
import com.StoreShop.ProjectComputer.Model.CategoryModel;
import com.StoreShop.ProjectComputer.Response.SuccessResponse;
import com.StoreShop.ProjectComputer.Service.CategoryService;
import com.StoreShop.ProjectComputer.Util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<SuccessResponse> list(){
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, categoryService.getList())
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, categoryService.getOne(id))
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<SuccessResponse> filterList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
            ){
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, categoryService.filterList(page, size, direction, name))
        );
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> save(@Valid @RequestBody RequestCategory requestCategory){
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.CREATED, categoryService.create(requestCategory))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@Valid @RequestBody RequestCategory requestCategory, @PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, categoryService.update(requestCategory, id))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponseUtil.success(HttpStatus.OK, categoryService.delete(id))
        );
    }

}
