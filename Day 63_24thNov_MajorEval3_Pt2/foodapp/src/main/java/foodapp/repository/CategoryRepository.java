package foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import foodapp.entity.Categories;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, String> {

    List<Categories> findByCategoryIdAndIsDeleted(String categoryId, String isDelete);

}
