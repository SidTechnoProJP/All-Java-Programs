package zomatomodified.zomato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zomatomodified.zomato.entity.Categories;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, String> {

    List<Categories> findByCategoryIdAndIsDeleted(String categoryId, String isDelete);

}
