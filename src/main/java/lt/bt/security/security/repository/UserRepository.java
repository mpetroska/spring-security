package lt.bt.security.security.repository;

import lt.bt.security.security.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String usernmae);
}
