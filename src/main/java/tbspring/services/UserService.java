package tbspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbspring.entities.UserEntity;
import tbspring.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepository;

    public UserEntity getUser(long id) {
        UserEntity user = userRepository.findById(id).get();
        if (user == null) {
            throw new NullPointerException("Пользователь не найден");
        } else return user;
    }

    public List<UserEntity> getAllUsers() {
        Iterable<UserEntity> users = userRepository.findAll();
        List<UserEntity> result = new ArrayList<>();
        for (UserEntity ue : users) {
            result.add(ue);
        }
        return result;
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public void deleteUser(UserEntity user) {
        userRepository.delete(user);
    }

}
