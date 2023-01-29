package my.stu.sync.mapper;

import my.stu.sync.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    public ProductVO selectProductQuantity(int productId);

    public int decreaseQuantity(int productId);

    public int updateQuantity(ProductVO productVO);

    public int insertProduct(ProductVO productVO);

    public int deleteAll();

}
