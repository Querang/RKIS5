package ru.sfu.querang.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sfu.querang.models.Car;
import ru.sfu.querang.repositories.CarRepository;

/**
 * Класс сервис для обработки операций c автомобилями.
 * Он предоставляет методы CRUD-операция и фильтрации автомобилей по цене.
 */
@Service
@Transactional(readOnly = true)
public class CarService {

    /**
     * Внедрение зависимости репозитория в сервис
     */
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Метод ищет и возвращает список всех автомобилей в базе данных.
     *
     * @return Список всех автомобилей.
     */
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    /**
     * Метод поиска автомобилей по id.
     *
     * @param id  id автомобиля.
     * @return автомобиль, если найден, иначе null.
     */
    public Car findOne(int id) {
        return carRepository.findById(id).orElse(null);
    }

    /**
     * Метод сохраняет новый автомобиль в базе данных.
     *
     * @param car  автомобиль для сохранения.
     */
    @Transactional
    public void save(Car car) {
        carRepository.save(car);
    }

    /**
     * Метод обновляет автомобиль в базе данных по id.
     *
     * @param id   Обновляемый id.
     * @param car  Обновленный автомобиль.
     */
    @Transactional
    public void update(int id, Car car) {
        car.setId(id);
        carRepository.save(car);
    }

    /**
     * Метод удаляет автомобиль из базы данных по id.
     *
     * @param id  id удаляемого автомобиля.
     */
    @Transactional
    public void delete(int id) {carRepository.deleteById(id);
    }

    /**
     * Метод фильтрации автомобилей по цене
     *
     * @param maxPrice  Максимальная цена, по которой фильтруются автомобили.
     * @return Список удовлетворяющий условию автомобилей.
     */
    public List<Car> filterByPrice(Float maxPrice) {
        return carRepository.findByPriceLessThanEqual(maxPrice);
    }
}
