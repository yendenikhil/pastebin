package dev.ny.pastebin.pastebincore.bin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BinRepository extends PagingAndSortingRepository<Bin, Long> {
    List<BinLite> findByTitleIgnoreCaseContaining(@Param("title") String title);

    List<BinLite> findByTitleIgnoreCaseContaining(@Param("title") String title, Pageable pageable);
}
