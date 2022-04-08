package ru.hh.school.validator;

import javax.inject.Singleton;
import javax.validation.ValidationException;

@Singleton
public class Validator {

    public boolean validatePagePerPageEmployers(int page, int perPage) {
        if ((page + 1) * perPage > 5000) throw new ValidationException("(page + 1) * perPage should be <= 5000");
        return true;
    }

    public boolean validatePagePerPageVacancies(int page, int perPage) {
        if ((page + 1) * perPage > 2000) throw new ValidationException("(page + 1) * perPage should be <= 2000");
        return true;
    }
}
