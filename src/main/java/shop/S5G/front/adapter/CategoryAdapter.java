package shop.S5G.front.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.S5G.front.dto.MessageDto;
import shop.S5G.front.dto.category.CategoryRequestDto;
import shop.S5G.front.dto.category.CategoryResponseDto;

import java.util.List;

@FeignClient(value = "category", url = "${gateway.url}")
public interface CategoryAdapter {

    //카테고리 등록
    @PostMapping("/api/shop/category")
    ResponseEntity<MessageDto> addsCategory(@RequestBody CategoryRequestDto categoryRequestDto);

    //카테고리 목록 조회
    @GetMapping("/category")
    ResponseEntity<List<CategoryResponseDto>> getAllCategories();
}