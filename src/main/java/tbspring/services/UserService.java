package tbspring.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tbspring.entities.UserEntity;
import tbspring.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserEntity getUser(long id) {
         UserEntity user = userRepository.findById(id).get();
        if (user == null) {
            throw new NullPointerException("Пользователь не найден");
        } else return user;
    }
    public UserEntity getUser(String username){
        UserEntity user =  userRepository.findByUserName(username);
        if (user == null){
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
    public UserEntity saveUser(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public void deleteUser(UserEntity user) {
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findByUserName(username);
    }
}
