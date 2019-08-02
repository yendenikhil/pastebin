package dev.ny.pastebin.bin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BinRepository extends PagingAndSortingRepository<Bin, Long> {

    List<Bin> findByTitleIgnoreCaseContaining(@Param("title") String title, Pageable pageable);
}
