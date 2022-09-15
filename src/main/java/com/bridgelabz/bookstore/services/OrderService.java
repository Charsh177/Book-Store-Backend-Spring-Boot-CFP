package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exceptions.BookException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenUtility tokenUtility;

    public Order createOrder(OrderDTO orderDTO, String token) {
        Optional<Book> book = bookRepository.findById(orderDTO.getBookId());
        Optional<UserData> user = userRepository.findById(orderDTO.getUserId());
        Integer userId = tokenUtility.decodeToken(token);
        if (book.isPresent() && user.isPresent()) {
            if (orderDTO.getQuantity() < book.get().getQuantity()) {
                System.out.println("The book price is :::: "+book.get().getPrice());
                Order newOrder = new Order(book.get().getPrice(),orderDTO.getQuantity(), orderDTO.getAddress(),
                        book.get(), user.get(), orderDTO.isCancel(),book.get().getBookName());
                orderRepository.save(newOrder);
                book.get().setQuantity(book.get().getQuantity() - orderDTO.getQuantity());
                newOrder.setPrice(newOrder.getPrice() * orderDTO.getQuantity());
                bookRepository.save(book.get());
                System.out.println("New Order is :::: " + newOrder);
                return newOrder;
            } else {
                throw new BookException("Requested quantity is not available");
            }
        } else {
            throw new BookException("Book or User doesn't exists..!!!");
        }
    }

    public List<Order> getAllOrderRecords() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    public Order getOrderRecord(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new BookException("Order Record doesn't exists..!!!");
        }
    }

    public Order updateOrderRecord(Integer id, OrderDTO dto) {
        Optional<Order> order = orderRepository.findById(id);
        Optional<Book> book = bookRepository.findById(dto.getBookId());
        Optional<UserData> user = userRepository.findById(dto.getUserId());
        if (order.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                if (dto.getQuantity() < book.get().getQuantity()) {
                    Order newOrder = new Order(id, dto.getQuantity(), dto.getAddress(), book.get(), user.get(), dto.isCancel(),book.get().getBookName());
                    orderRepository.save(newOrder);
                    return newOrder;
                } else {
                    throw new BookException("Requested quantity is not available");
                }
            } else {
                throw new BookException("Book or User doesn't exists..!!!");
            }
        } else {
            throw new BookException("Order Record doesn't exists..!!!");
        }
    }

    public Order deleteOrderRecord(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            return order.get();
        } else {
            throw new BookException("Order Record doesn't exists..!!!");
        }
    }
}