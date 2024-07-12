package Generic;

import java.util.List;
import java.util.Optional;

public interface IBankRepository<T> {
    List<T> getAll(String filePath);
    List<T> writeAll(List<T> list, String filePath);
    Optional<T> findById(String id);
    T addT(T obj);
    T insertT(String filePath ,T obj);
    T updateT(String filePath ,T obj);
}
