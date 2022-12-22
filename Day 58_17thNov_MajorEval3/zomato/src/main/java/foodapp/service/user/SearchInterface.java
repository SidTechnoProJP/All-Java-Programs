package foodapp.service.user;

import foodapp.exception.SessionIdExpiredException;

import java.util.List;

public interface SearchInterface {
    List<?> search(String field) throws SessionIdExpiredException;
}
