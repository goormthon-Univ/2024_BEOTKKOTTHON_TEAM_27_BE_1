package com.example.back1.store.controller;

import com.example.back1.global.BaseResponse;
import com.example.back1.store.controller.dto.request.StoreCreateRequest;
import com.example.back1.store.domain.Store;
import com.example.back1.store.service.StoreService;
import com.example.back1.store.service.dto.response.StoreCreateResponse;
import com.example.back1.store.service.dto.response.StoreInformation;
import com.example.back1.user.domain.User;
import com.example.back1.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;

    @PostMapping("")
    public BaseResponse createStore(@RequestBody @Valid StoreCreateRequest request) {
        User user = userService.findById(request.userId());
        Store store = storeService.enrollStore(Store.createStore(user, request.name(), request.address()));

        return new BaseResponse(
                Boolean.TRUE,
                "200",
                "OK",
                new StoreCreateResponse(user.getId(), store.getId())
        );
    }

    @GetMapping("/{userId}/{storeId}")
    public BaseResponse getStore(@PathVariable ("userId") Long userId, @PathVariable ("storeId") Long storeId) {
        Store store = storeService.findById(storeId);

        return new BaseResponse(
                Boolean.TRUE,
                "200",
                "OK",
                new StoreInformation(userId, storeId, store.getName(), store.getAddress())
        );
    }
}
