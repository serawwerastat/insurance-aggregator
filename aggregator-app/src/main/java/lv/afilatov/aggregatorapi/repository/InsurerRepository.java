package lv.afilatov.aggregatorapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.afilatov.aggregatorapi.domain.model.InsurerModel;

public interface InsurerRepository extends CrudRepository<InsurerModel, Integer> {

    List<InsurerModel> findAll();

    @Query(value = "SELECT * FROM insurers WHERE is_active = true", nativeQuery = true)
    List<InsurerModel> findAllActive();
}
