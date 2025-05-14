package pheng.com.springfirstclass.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pheng.com.springfirstclass.model.User;

@Repository
public interface UserRepositoryPaginate extends PagingAndSortingRepository<User, Integer> {

}
