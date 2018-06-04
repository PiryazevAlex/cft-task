package ru.cft.task.moneyservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.task.moneyservice.entity.RequestInfo;

import java.util.Optional;

/**
 * Репозиторий для {@link RequestInfo}
 */
public interface RequestInfoRepository extends CrudRepository<RequestInfo, String> {

    Optional<RequestInfo> findOneById(String id);

}
