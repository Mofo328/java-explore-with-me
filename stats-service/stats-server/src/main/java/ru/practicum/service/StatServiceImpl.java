package ru.practicum.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.StatDto;
import ru.practicum.StatIntoDto;
import ru.practicum.StatMapper;
import ru.practicum.StatOutDto;
import ru.practicum.StatRepository;
import ru.practicum.exception.ValidationException;
import ru.practicum.model.Statistic;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatServiceImpl implements StatService {

    StatRepository statRepository;


    @Override
    @Transactional
    public StatDto create(StatIntoDto dto) {
        log.info("Пришел запрос на создание статистики {}", dto);
        Statistic statistic = statRepository.save(StatMapper.toStat(dto));
        log.info("Сохранили следующие данные в базу {}", statistic);
        return StatMapper.toStatDto(statistic);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatOutDto> get(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {

        if (start.isAfter(end)) {
            throw new ValidationException("Дата начала и дата окончания не могут быть равны или противоречить друг другу");
        }
        if (unique) {
            if (uris != null) {
                return statRepository.findAllWithUniqueIpWithUris(uris, start, end);
            }
            return statRepository.findAllWithUniqueIpWithoutUris(start, end);
        } else {
            if (uris != null) {
                return statRepository.findAllWithUris(uris, start, end);
            }
            return statRepository.findAllWithoutUris(start, end);
        }
    }

}
