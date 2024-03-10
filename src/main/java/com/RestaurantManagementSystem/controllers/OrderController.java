package com.RestaurantManagementSystem.controllers;

import com.RestaurantManagementSystem.dto.OrderDTO;
import com.RestaurantManagementSystem.repositories.UserRepository;
import com.RestaurantManagementSystem.services.DishService;
import com.RestaurantManagementSystem.services.OrderService;
import com.RestaurantManagementSystem.utils.MapUtils;
import com.RestaurantManagementSystem.utils.OrderUtils;
import com.RestaurantManagementSystem.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepository;
    private final DishService dishService;
    private final OrderUtils orderUtils;

    @GetMapping("/order/menu")
    public String getOrders(Model model, Principal principal) {
        model.addAttribute("orders", orderService.getOrders(principal));
        model.addAttribute("user", userRepository.findByPrincipal(principal));
        model.addAttribute("TimeUtils", TimeUtils.builder().build());
        return "user/order_menu";
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable Long id, Model model, Principal principal) {
        OrderDTO orderDTO = orderService.getOrderById(id);

        if (!orderUtils.hasPrincipalRights(orderDTO, principal)) {
            // TODO NOTE: maybe use spring security, but need to read and watch a lot.
            return "redirect:/access_denied";
        }

        model.addAttribute("dishes", dishService.getDishes(null, true));
        model.addAttribute("user", orderDTO.getUser());
        model.addAttribute("order", orderDTO);
        model.addAttribute("TimeUtils", TimeUtils.builder().build());
        return "user/order";
    }

    @PostMapping("/order/create")
    public String createOrder(@RequestParam Map<String, String> params, Principal principal) throws InterruptedException {
        // TODO NOTE: it would be possible to use React to make input for convenience and optimizations, but so far so(:
        Map<Long, Long> dishCounts = MapUtils.FromStringStringMapToLongLongMap(params, List.of("_csrf"));
        orderService.createOrder(dishCounts, principal);
        return "redirect:/order/menu";
    }

    @PostMapping("/order/update/{id}")
    public String updateOrder(@PathVariable Long id, @RequestParam Map<String, String> params, Principal principal) {
        OrderDTO orderDTO = orderService.getOrderById(id);

        if (!orderUtils.hasPrincipalRights(orderDTO, principal)) {
            return "redirect:/access_denied";
        }

        Map<Long, Long> dishCounts = MapUtils.FromStringStringMapToLongLongMap(params, List.of("_csrf", "orderId"));
        orderService.updateOder(orderDTO, dishCounts);
        return "redirect:/order/" + orderDTO.getId();
    }

    @PostMapping("/order/cancel/{id}")
    public String cancelOrder(@PathVariable Long id, Principal principal) {
        OrderDTO orderDTO = orderService.getOrderById(id);

        if (!orderUtils.hasPrincipalRights(orderDTO, principal)) {
            return "redirect:/access_denied";
        }

        orderService.cancelOrder(orderDTO);
        return "";
    }
}
