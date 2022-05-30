package com.telegram.folobot.controller;

import com.telegram.folobot.domain.FoloUser;
import com.telegram.folobot.constants.ControllerCommandsEnum;
import com.telegram.folobot.repos.FoloUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final FoloUserRepo foloUserRepo;

    @GetMapping
    public String user(Map<String, Object> model) {
        model.put("folousers", foloUserRepo.findAll());
        return "user";
    }

    /**
     * Post-запрос на выполнение команды с основного экрана
     *
     * @param userid ID пользователя
     * @param tag    Переопределеннои имя
     * @param action Команда
     * @param model  Map с переменными
     * @return Имя экрана
     */
    @PostMapping
    public String onAction(
            @RequestParam String userid,
            @RequestParam(required = false) String tag,
            @RequestParam String action,
            Map<String, Object> model
    ) {
        if (!userid.isEmpty()) {
            Optional<FoloUser> foloUser = foloUserRepo.findById(Long.parseLong(userid));
            switch (ControllerCommandsEnum.valueOf(action)) {
                case add:
                    if (foloUser.isEmpty()) {
                        foloUserRepo.save(new FoloUser(Long.parseLong(userid), tag));
                    }
                    break;
                case update:
                    if (foloUser.isPresent()) {
                        foloUser.get().setTag(tag);
                        foloUserRepo.save(foloUser.get());
                    }
                    break;
                case delete:
                    foloUser.ifPresent(foloUserRepo::delete);
                    break;
            }
        }
        return user(model);
    }
}
