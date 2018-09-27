package lt.bt.security.security.validation;

import lt.bt.security.security.entity.User;
import lt.bt.security.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqUsernameValidator implements ConstraintValidator<lt.bt.security.security.validation.UniqUsername, String> {

    @Autowired
    private UserRepository repository;
    @Override
    public void initialize(lt.bt.security.security.validation.UniqUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        User user = repository.findByUsername(value);

        if (user != null) {
            return false;
        }
        return true;
    }
}
