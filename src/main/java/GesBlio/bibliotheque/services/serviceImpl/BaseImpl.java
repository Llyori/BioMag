//package GesBlio.bibliotheque.services.serviceImpl;
//
//import GesBlio.bibliotheque.repositories.BaseRepository;
//import GesBlio.bibliotheque.services.BaseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BaseImpl<I> implements BaseService<I> {
//    //@Autowired
//    private BaseRepository<I> baseRepository;
//
//    public BaseImpl(BaseRepository<I> baseRepository) {
//        this.baseRepository = baseRepository;
//    }
//
//    @Override
//    public List<I> list() {
//        return baseRepository.findAll();
//    }
//
//    @Override
//    public I add(I i) {
//        return baseRepository.save(i);
//    }
//
//    @Override
//    public I Update(I i, Long id) {
//        I o = baseRepository.findById(id).get();
//        o = i;
//        return baseRepository.save(o);
//    }
//
//    @Override
//    public I findById(Long id) {
//        return baseRepository.findById(id).get();
//    }
//
//    @Override
//    public I findByEmail(String email) {
//        return baseRepository.findClientByEmail(email);
//    }
//
//    @Override
//    public I findByPhoneNumber(String phone) {
//        return baseRepository.findByPhoneNumber(phone);
//    }
//
//    @Override
//    public void delete(Long id) {
//        baseRepository.deleteById(id);
//    }
//}
