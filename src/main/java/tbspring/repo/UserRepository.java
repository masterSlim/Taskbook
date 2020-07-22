package tbspring.repo;

import org.springframework.data.repository.CrudRepository;
import tbspring.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
}

