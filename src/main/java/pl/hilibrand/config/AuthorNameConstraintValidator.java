package pl.hilibrand.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

import pl.hilibrand.annotation.ValidAuthorName;

public class AuthorNameConstraintValidator implements ConstraintValidator<ValidAuthorName, String> {
    private final static String REQUIRED_CHAR_IN_AUTHOR_NAME = "a";
    private final static String SPLIT_CHAR_SPACE = " ";

    @Override
    public void initialize(ValidAuthorName authorName) {
    }

    @Override
    public boolean isValid(String author, ConstraintValidatorContext context) {
        var splitAuthorName = author.toLowerCase().split(SPLIT_CHAR_SPACE);
        return Arrays.stream(splitAuthorName).anyMatch(name -> name.startsWith(REQUIRED_CHAR_IN_AUTHOR_NAME));
    }
}
