package tbspring.repository;

import org.springframework.data.repository.CrudRepository;
import tbspring.entities.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
}

