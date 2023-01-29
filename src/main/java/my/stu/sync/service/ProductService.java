package my.stu.sync.service;

import my.stu.sync.vo.ProductVO;

public interface ProductService {

    public ProductVO selectQuantity(int id);
    public void syncDecreaseQuantity(int id);
    public void decreaseQuantity(int id);
    public int insertProduct(ProductVO productVO);
    public int deleteAll();
}
