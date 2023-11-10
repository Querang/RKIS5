package ru.sfu.querang.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sfu.querang.models.Car;

/**
 * Интерфейс репозиторя для работы с автомобилями, предоставляющий методы для выполнения операций с данными в базе данных.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    /**
     * Метод, ищущий всю посуду меньше или равную указанной цене
     *
     * @param price максимальная цена для прохождения фильтрации
     * @return список автомобилей, прощедщих по цене
     */
    List<Car> findByPriceLessThanEqual(Float price);
}

