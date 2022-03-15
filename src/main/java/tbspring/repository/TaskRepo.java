package tbspring.repository;

import org.springframework.data.repository.CrudRepository;
import tbspring.entities.TaskEntity;

import java.util.Collection;

public interface TaskRepo extends CrudRepository<TaskEntity, Long> {
    Collection<TaskEntity> getAllByExecutorId(Long id);
}
