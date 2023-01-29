package my.stu.sync.service;

import my.stu.sync.mapper.ProductMapper;
import my.stu.sync.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductMapper mapper;



    @Override
    public ProductVO selectQuantity(int id) {
        ProductVO productVO = mapper.selectProductQuantity(1);
        return productVO;
    }

    @Override
    public synchronized void syncDecreaseQuantity(int id) {
        ProductVO productVO = mapper.selectProductQuantity(1);
        productVO.decreaseQuantity(1);
        mapper.updateQuantity(productVO);
    }

    @Override
    public void decreaseQuantity(int id) {
        ProductVO productVO = mapper.selectProductQuantity(1);
        productVO.decreaseQuantity(1);
        mapper.updateQuantity(productVO);
    }

    @Override
    public int insertProduct(ProductVO productVO) {
        int i = mapper.insertProduct(productVO);
        return i;
    }

    @Override
    public int deleteAll() {
        return mapper.deleteAll();
    }
}
