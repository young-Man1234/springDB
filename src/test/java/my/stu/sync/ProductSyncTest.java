package my.stu.sync;

import my.stu.sync.mapper.ProductMapper;
import my.stu.sync.service.ProductService;
import my.stu.sync.vo.ProductVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class ProductSyncTest {

    @Autowired
    ProductMapper mapper;

    @Autowired
    ProductService service;

    @BeforeEach
    void init(){
        ProductVO data = new ProductVO(1, "product", 1000, 100);
        service.insertProduct(data);
    }

    @AfterEach
    void destroy(){
        service.deleteAll();
    }

    @Test
    void decreaseTest(){
        service.decreaseQuantity(1);
        ProductVO productVO = mapper.selectProductQuantity(1);
        System.out.println(productVO);
    }

    @Test
    void syncTest() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i = 0; i < threadCount; i++){
                executorService.submit(()->{
                    try {
                    //테스트할 요청
                    service.syncDecreaseQuantity(1);
                    }finally {
                        countDownLatch.countDown();
                    }
                });
        }
        countDownLatch.await();

        ProductVO productVO = service.selectQuantity(1);
        Assertions.assertThat(productVO.getQuantity()).isEqualTo(0);
    }

    @Test
    void notSyncTest() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i = 0; i < threadCount; i++){
            executorService.submit(()->{
                try {
                    //테스트할 요청
                    service.decreaseQuantity(1);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        ProductVO productVO = service.selectQuantity(1);
        Assertions.assertThat(productVO.getQuantity()).isNotEqualTo(0);
    }
}
