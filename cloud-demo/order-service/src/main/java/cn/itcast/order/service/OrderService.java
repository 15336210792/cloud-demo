package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //利用restTemplate发怂http请求，查用户
        //需要参数url 和 user类
        String url = "http://localhost:8081/user/" + order.getUserId();
        //发送请求，远端调用
        User user = restTemplate.getForObject(url, User.class);
        //封装user 到order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
