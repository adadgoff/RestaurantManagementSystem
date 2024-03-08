package com.RestaurantManagementSystem.repositories;

import com.RestaurantManagementSystem.models.OrderModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public List<OrderModel> findAllByUserEmailOrderByStartTime(String user_email) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends OrderModel> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends OrderModel> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<OrderModel> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public OrderModel getOne(Long aLong) {
        return null;
    }

    @Override
    public OrderModel getById(Long aLong) {
        return null;
    }

    @Override
    public OrderModel getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends OrderModel> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends OrderModel> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends OrderModel> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends OrderModel> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends OrderModel> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends OrderModel> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends OrderModel, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends OrderModel> S save(S entity) {
        return null;
    }

    @Override
    public <S extends OrderModel> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<OrderModel> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<OrderModel> findAll() {
        return null;
    }

    @Override
    public List<OrderModel> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(OrderModel entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends OrderModel> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<OrderModel> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<OrderModel> findAll(Pageable pageable) {
        return null;
    }
}
