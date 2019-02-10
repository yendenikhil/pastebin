package dev.ny.pastebin.pastebincore.repo;

import dev.ny.pastebin.pastebincore.bean.BinEntity;
import dev.ny.pastebin.pastebincore.bean.BinLiteEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BinRepository extends PagingAndSortingRepository<BinEntity, Long> {
    List<BinLiteEntity> findByTitleIgnoreCaseContaining(@Param("title") String title);

    List<BinLiteEntity> findByTitleIgnoreCaseContaining(@Param("title") String title, Pageable pageable);
}
