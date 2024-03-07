package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.exceptions.orderExceptions.GetOrderException;
import com.RestaurantManagementSystem.exceptions.orderExceptions.UpdateOrderException;
import com.RestaurantManagementSystem.exceptions.userExceptions.InvalidUserException;
import com.RestaurantManagementSystem.repositories.UserRepository;
import com.RestaurantManagementSystem.services.DishService;
import com.RestaurantManagementSystem.services.OrderService;
import com.RestaurantManagementSystem.utils.MapUtils;
import com.RestaurantManagementSystem.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@Slf4j  // TODO: dont forget to delete.
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepository;
    private final DishService dishService;

    @GetMapping("/order/menu")
    public String getOrders(Model model, Principal principal) {
        model.addAttribute("orders", orderService.getOrders(principal));
        model.addAttribute("user", userRepository.findByPrincipal(principal));
        model.addAttribute("TimeUtils", TimeUtils.builder().build());
        return "user/order_menu";
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable Long id, Model model, Principal principal) {
        try {
            OrderDTO orderDTO = orderService.getOrderById(id, principal);
            model.addAttribute("dishes", dishService.getDishes(null, true));
            model.addAttribute("user", userRepository.findByPrincipal(principal));
            model.addAttribute("order", orderDTO);
            model.addAttribute("TimeUtils", TimeUtils.builder().build());
        } catch (GetOrderException e) {
            model.addAttribute("error", e.getMessage());  // TODO: make error in front.
        } catch (Exception e) {
            model.addAttribute("error", "Возникла непредвиденная ошибка либо доступ запрещен. :)");
        }
        return "user/order";
    }

    @PostMapping("/order/create")
    public String createOrder(@RequestParam Map<String, String> params, Principal principal) {
        //  ENG NOTE: it would be possible to use React to make input for convenience and optimizations, but so far so(:
        //  RU ЗАМЕТКА: - можно было бы с помощью React сделать ввод для удобства и оптимизаций, но пока что так(:
        Map<Long, Long> dishCounts = MapUtils.FromStringStringMapToLongLongMap(params);
        if (dishCounts.values().stream().anyMatch(count -> count > 0)) {
            orderService.createOrder(dishCounts, principal);
        }
        return "redirect:/order/menu";  // TODO: better to redirect to order/{id}.
    }

    @PostMapping("/order/update")
    public String updateOrder(OrderDTO orderDTO, @RequestParam Map<String, String> params, Model model, Principal principal) {
        try {
            Map<Long, Long> dishCounts = MapUtils.FromStringStringMapToLongLongMap(params);
            orderService.updateOder(orderDTO, dishCounts, principal);
        } catch (InvalidUserException | UpdateOrderException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Неизвестная ошибка. Пожалуйста, обратитесь к администратору.");
        }
        return "redirect:/order/";
    }
}
