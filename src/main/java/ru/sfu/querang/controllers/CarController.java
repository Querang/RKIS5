package ru.sfu.querang.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sfu.querang.models.Car;
import ru.sfu.querang.services.CarService;

/**
 * Класс контроллер, отвечающий за обработку HTTP-запросов.
 * Обеспечивает взаимодействие с сервисом и обработку CRUD запросов
 */
@Controller
@RequestMapping("/cars")
public class CarController {

    /**
     * Внедрение зависимости сервисат автомобилей в контроллер
     */
    private final CarService carService;

    /**
     * Конструктор контроллера
     */
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Обрабатывает GET-запросы по пути "/cars".
     * Фильтрует, если указана максимальная цена, иначе возвращает список всех автомобилей
     *
     * @param price  Максимальная цена/цена для фильтрации.
     * @param model  Модель для передачи данных в представление.
     * @return Представление "cars/index".
     */
    @GetMapping()
    public String index(@RequestParam(name = "price", required = false) Float price, Model model) {
        if (price != null) {
            model.addAttribute("cars", carService.filterByPrice(price));
        } else {
            model.addAttribute("cars", carService.findAll());
        }
        return "cars/index";
    }

    /**
     * Обрабатывает GET-запросы "/cars/{id}" для просмотра автомобилей по id.
     *
     * @param id     id автомобиля.
     * @param model  Модель для передачи данных в представление.
     * @return Представление "cars/show".
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carService.findOne(id));
        return "cars/show";
    }

    /**
     * Обрабатывает GET-запросы "/cars/{id}/edit" для редактирования автомобилей.
     *
     * @param id     id редактируемого автомобилия.
     * @param model  Модель для передачи данных в представление.
     * @return Представление "cars/edit".
     */
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carService.findOne(id));
        return "cars/edit";
    }

    /**
     * Обрабатывает GET-запросы /cars/new" для создания нового автомобиляф.
     *
     * @param car  автомобиль, передаваемый в представление.
     * @return Представление "cars/new".
     */
    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") Car car) {
        return "cars/new";
    }

    /**
     * Обрабатывает POST-запросы "/cars" для создания нового автомобиля.
     * Проводит валидацию автомобиля и сохраняет в сервисе, если данные корректны.
     *
     * @param car          автомобиль для создания.
     * @param bindingResult   Результат валидации автомобиля.
     * @return Представление "cars/new" в случае ошибоки, иначе "/cars".
     */
    @PostMapping()
    public String create(
            @ModelAttribute("car") @Valid Car car,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "cars/new";
        }
        carService.save(car);
        return "redirect:/cars";
    }

    /**
     * Обрабатывает PATCH-запросы "/cars/{id}" для обновления автомобилей.
     * Проводит валидацию автомобиля и обновляет её, если данные верны.
     *
     * @param car          Автомобиль для обновления.
     * @param bindingResult   Результат валидации автомобиля.
     * @param id              id обновляемого автомобиля.
     * @return Представление "cars/edit" в случае ошибки, иначе "/cars".
     */
    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("car") @Valid Car car,
            BindingResult bindingResult,
            @PathVariable("id") int id
    ) {
        if (bindingResult.hasErrors()) {
            return "/cars/edit";
        }
        carService.update(id, car);
        return "redirect:/cars";
    }

    /**
     * Обрабатывает DELETE-запросы "/cars/{id}" для удаления удаления автомобиля по id.
     *
     * @param id  id удаляемого автомобиля.
     * @return Перенаправляет на "/cars".
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        carService.delete(id);
        return "redirect:/cars";
    }
}
